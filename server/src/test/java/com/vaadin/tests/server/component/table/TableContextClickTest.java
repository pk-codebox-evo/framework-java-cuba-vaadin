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
package com.vaadin.tests.server.component.table;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ContextClickEvent.ContextClickListener;
import com.vaadin.shared.ui.table.TableConstants.Section;
import com.vaadin.ui.Table;

public class TableContextClickTest extends Table {

    private String error = null;
    private boolean handled = false;

    @Test
    public void testContextClickListenerWithTableEvent() {
        addContextClickListener(new ContextClickListener() {

            @Override
            public void contextClick(ContextClickEvent event) {
                if (!(event instanceof TableContextClickEvent)) {
                    return;
                }

                TableContextClickEvent e = (TableContextClickEvent) event;
                if (e.getSection() != Section.BODY) {
                    error = "Event section was not BODY.";
                }
                handled = true;
            }
        });
        fireEvent(new TableContextClickEvent(this, null, null, null,
                Section.BODY));

        if (error != null) {
            Assert.fail(error);
        } else if (!handled) {
            Assert.fail("Event was not handled by the ContextClickListener");
        }
    }
}
