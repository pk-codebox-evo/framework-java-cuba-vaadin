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
package com.vaadin.tests.components.javascriptcomponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vaadin.tests.tb3.MultiBrowserTest;

public class JavaScriptPreloadingTest extends MultiBrowserTest {

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        return getBrowsersExcludingPhantomJS();
    }

    @Test
    public void scriptsShouldPreloadAndExecuteInCorrectOrder()
            throws InterruptedException {
        openTestURL();

        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            assertEquals("First", alert.getText());
            alert.accept();

            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            assertEquals("Second", alert.getText());
            alert.accept();

        } catch (TimeoutException te) {
            fail("@Javascript widget loading halted.");
        }

    }
}
