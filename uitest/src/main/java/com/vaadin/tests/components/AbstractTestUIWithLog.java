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
package com.vaadin.tests.components;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.util.Log;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractTestUIWithLog extends AbstractTestUI {

    protected Log log = new Log(getLogSize());

    @Override
    public void init(VaadinRequest request) {
        super.init(request);
        ((VerticalLayout) getContent()).addComponent(log, 0);
    }

    protected void log(String message) {
        log.log(message);
    }

    protected int getLogSize() {
        return 5;
    }

}
