/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.client.connectors;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.data.DataSource.RowHandle;
import com.vaadin.client.renderers.Renderer;
import com.vaadin.client.widget.grid.selection.ClickSelectHandler;
import com.vaadin.client.widget.grid.selection.SelectionModel;
import com.vaadin.client.widget.grid.selection.SelectionModel.Single;
import com.vaadin.client.widget.grid.selection.SpaceSelectHandler;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.grid.GridState;
import com.vaadin.shared.ui.grid.selection.SingleSelectionModelServerRpc;
import com.vaadin.shared.ui.grid.selection.SingleSelectionModelState;
import com.vaadin.ui.Grid.SingleSelectionModel;

import elemental.json.JsonObject;

/**
 * Connector for server-side {@link SingleSelectionModel}.
 *
 * @since 7.6
 * @author Vaadin Ltd
 */
@Connect(SingleSelectionModel.class)
public class SingleSelectionModelConnector extends
        AbstractSelectionModelConnector<SelectionModel.Single<JsonObject>> {

    private SpaceSelectHandler<JsonObject> spaceHandler;
    private ClickSelectHandler<JsonObject> clickHandler;
    private Single<JsonObject> selectionModel = createSelectionModel();

    @Override
    protected void extend(ServerConnector target) {
        getGrid().setSelectionModel(selectionModel);
        spaceHandler = createSpaceSelectHandler();
        clickHandler = createClickSelectHandler();
    }

    // Haulmont API
    protected ClickSelectHandler<JsonObject> createClickSelectHandler() {
        return new ClickSelectHandler<JsonObject>(getGrid());
    }

    // Haulmont API
    protected SpaceSelectHandler<JsonObject> createSpaceSelectHandler() {
        return new SpaceSelectHandler<JsonObject>(getGrid());
    }

    @Override
    public SingleSelectionModelState getState() {
        return (SingleSelectionModelState) super.getState();
    }

    @Override
    public void onUnregister() {
        spaceHandler.removeHandler();
        clickHandler.removeHandler();

        super.onUnregister();
    }

    @Override
    protected Single<JsonObject> createSelectionModel() {
        return new SingleSelectionModel();
    }

    @OnStateChange("deselectAllowed")
    void updateDeselectAllowed() {
        selectionModel.setDeselectAllowed(getState().deselectAllowed);
    }

    /**
     * SingleSelectionModel without a selection column renderer.
     */
    public class SingleSelectionModel extends AbstractSelectionModel
            implements SelectionModel.Single<JsonObject> {

        private RowHandle<JsonObject> selectedRow;
        private boolean deselectAllowed;

        @Override
        public Renderer<Boolean> getSelectionColumnRenderer() {
            return null;
        }

        @Override
        public void reset() {
            super.reset();

            // Clean up selected row
            if (selectedRow != null) {
                clearSelectedRow();
            }
        }

        @Override
        public boolean select(JsonObject row) {
            boolean changed = false;

            if (row == null && !isDeselectAllowed()) {
                // Attempting to deselect, even though it's not allowed.
            } else {
                if (selectedRow != null) {
                    // Check if currently re-selected row was deselected from
                    // the server.
                    if (row != null && getRowHandle(row).equals(selectedRow)) {
                        if (selectedRow.getRow()
                                .hasKey(GridState.JSONKEY_SELECTED)) {
                            // Everything is OK, no need to do anything.
                            return false;
                        }
                    }

                    // Remove old selected row
                    clearSelectedRow();
                    changed = true;
                }

                if (row != null) {
                    // Select the new row.
                    setSelectedRow(row);
                    changed = true;
                }
            }

            if (changed) {
                getRpcProxy(SingleSelectionModelServerRpc.class)
                        .select(getRowKey(row));
            }

            return changed;
        }

        private void setSelectedRow(JsonObject row) {
            selectedRow = getRowHandle(row);
            selectedRow.pin();
            selectedRow.getRow().put(GridState.JSONKEY_SELECTED, true);
            selectedRow.updateRow();
        }

        private void clearSelectedRow() {
            selectedRow.getRow().remove(GridState.JSONKEY_SELECTED);
            selectedRow.updateRow();
            selectedRow.unpin();
            selectedRow = null;
        }

        @Override
        public boolean deselect(JsonObject row) {
            if (getRowHandle(row).equals(selectedRow)) {
                select(null);
            }
            return false;
        }

        @Override
        public JsonObject getSelectedRow() {
            throw new UnsupportedOperationException(
                    "This client-side selection model "
                            + getClass().getSimpleName()
                            + " does not know selected row.");
        }

        @Override
        public void setDeselectAllowed(boolean deselectAllowed) {
            this.deselectAllowed = deselectAllowed;
        }

        @Override
        public boolean isDeselectAllowed() {
            return deselectAllowed;
        }
    }
}