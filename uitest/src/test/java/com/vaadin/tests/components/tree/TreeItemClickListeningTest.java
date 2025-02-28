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
package com.vaadin.tests.components.tree;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.testbench.By;
import com.vaadin.tests.tb3.MultiBrowserTest;

public class TreeItemClickListeningTest extends MultiBrowserTest {

    private WebElement getTreeNode(String caption) {
        return getDriver()
                .findElement(By.xpath("//span[text() = '" + caption + "']"));
    }

    @Test
    public void test() throws InterruptedException {
        openTestURL();

        performLeftClick();
        assertEventFired("1. Left Click");

        performRightClick();
        assertEventFired("2. Right Click");
    }

    private void assertEventFired(String text) {
        assertThat(String.format("Couldn't find text '%s' from the log.", text),
                logContainsText(text));
    }

    private void performLeftClick() {
        new Actions(driver).click(getTreeNode("Caption 1")).build().perform();
    }

    private void performRightClick() {
        new Actions(driver).contextClick(getTreeNode("Caption 2")).build()
                .perform();
    }

}
