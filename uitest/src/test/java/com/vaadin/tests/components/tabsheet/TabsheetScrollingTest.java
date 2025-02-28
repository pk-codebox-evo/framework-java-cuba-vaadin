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
package com.vaadin.tests.components.tabsheet;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.tests.tb3.MultiBrowserTest;

public class TabsheetScrollingTest extends MultiBrowserTest {

    @Test
    public void keyboardScrolling() {
        openTestURL();
        getTab(1).click();
        for (int i = 0; i < 10; i++) {
            sendKey(Keys.ARROW_RIGHT);
        }
        sendKey(Keys.SPACE);
        Assert.assertEquals("Hide this tab (21)", getHideButtonText());
    }

    private WebElement getTab(int index) {
        return getDriver()
                .findElement(By.vaadin("//TabSheet#tab[" + index + "]"));
    }

    private String getHideButtonText() {
        ButtonElement buttonCaption = $(ButtonElement.class).first();
        return buttonCaption.getText();
    }

    private void sendKey(Keys key) {
        new Actions(getDriver()).sendKeys(key).perform();
    }

    private WebElement getTabByCaption(TabSheetElement ts, String caption) {
        WebElement tabBar = ts.findElement(By.className("v-tabsheet-tabs"));
        return tabBar.findElement(By
                .xpath("./tbody/tr/td[./div/div/div[contains(., normalize-space('"
                        + caption + "'))]]"));
    }

    private boolean isTabVisible(TabSheetElement ts, String tabCaption) {
        WebElement tab = getTabByCaption(ts, tabCaption);
        Point location = tab.getLocation();
        return location.getX() > 0 && location.getX() < ts.getSize().getWidth();
    }

    @Test
    public void serverChangeShouldShowTab() {
        openTestURL();
        $(ButtonElement.class).id(TabsheetScrolling.SELECT_LAST).click();
        TabSheetElement tabsheetFixed = $(TabSheetElement.class).first();
        Assert.assertTrue("Select last should scroll last tab into view",
                isTabVisible(tabsheetFixed, "Tab 99"));
        $(ButtonElement.class).id(TabsheetScrolling.SELECT_FIRST).click();
        Assert.assertTrue("Select first should scroll first tab into view",
                isTabVisible(tabsheetFixed, "Tab 1"));

    }
}
