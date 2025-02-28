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
package com.vaadin.tests.components.menubar;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractTestUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * Test to see if tooltips will render in the correct locations near the edges.
 *
 * @author Vaadin Ltd
 */
public class MenuBarTooltipsNearEdge extends AbstractTestUI {
    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.tests.components.AbstractTestUI#setup(com.vaadin.server.
     * VaadinRequest)
     */
    @Override
    protected void setup(VaadinRequest request) {
        VerticalLayout vlayout = new VerticalLayout();
        vlayout.setSizeFull();
        vlayout.addComponent(buildMenu("Menu"));
        vlayout.setComponentAlignment(vlayout.getComponent(0),
                Alignment.BOTTOM_RIGHT);
        setContent(vlayout);

        getTooltipConfiguration().setOpenDelay(0);
        getTooltipConfiguration().setQuickOpenDelay(0);
        getTooltipConfiguration().setCloseTimeout(1000);

    }

    private Component buildMenu(String label) {
        MenuBar menu = new MenuBar();
        MenuItem item = menu.addItem(label, null);

        item.addItem("Item 1", null).setDescription("TOOLTIP 1");
        item.addItem("Item 2", null).setDescription("TOOLTIP 2");
        item.addItem("Item 3", null).setDescription("TOOLTIP 3");
        item.addItem("Item 4", null).setDescription("TOOLTIP 4");

        return menu;
    }

    private Command buildCommand() {
        Command command = new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {

            }
        };
        return command;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.tests.components.AbstractTestUI#getTestDescription()
     */
    @Override
    protected String getTestDescription() {
        return "Menu item tooltips should not abscure other menu items";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.tests.components.AbstractTestUI#getTicketNumber()
     */
    @Override
    protected Integer getTicketNumber() {
        return 12870;
    }
}
