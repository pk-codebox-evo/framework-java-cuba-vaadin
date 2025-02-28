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
package com.vaadin.tests.widgetset.server;

import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractTestUI;
import com.vaadin.tests.widgetset.TestingWidgetSet;

@Widgetset(TestingWidgetSet.NAME)
public class ClientRpcClass extends AbstractTestUI {

    public static String TEST_COMPONENT_ID = "testComponent";

    @Override
    protected void setup(VaadinRequest request) {
        ClientRpcClassComponent component = new ClientRpcClassComponent();
        component.setId(TEST_COMPONENT_ID);
        addComponent(component);

        component.pause();
    }

    @Override
    protected String getTestDescription() {
        return "UI showing dummy component where the wiget type is implementing the RPC interface.";
    }

    @Override
    protected Integer getTicketNumber() {
        return Integer.valueOf(13056);
    }

}
