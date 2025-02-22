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
package com.vaadin.tests.components.table;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.vaadin.tests.tb3.MultiBrowserTest;

public class TableScrollingWithSQLContainerTest extends MultiBrowserTest {

    @Test
    public void verifySQLContainerIndexOfIDNotCalled() {
        openTestURL();

        vaadinElement("/VVerticalLayout[0]/VButton[0]").click();

        Assert.assertTrue("SQLContainer indexOfId was called", driver
                .findElements(By.className("v-errorindicator")).isEmpty());
    }
}
