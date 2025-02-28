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
package com.vaadin.tests.tooltip;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.testbench.elements.SliderElement;
import com.vaadin.tests.tb3.TooltipTest;

/**
 * Test that sliders can have tooltips
 *
 * @author Vaadin Ltd
 */
public class SliderTooltipTest extends TooltipTest {

    @Test
    public void sliderHasTooltip() throws Exception {
        openTestURL();
        WebElement slider = $(SliderElement.class).first();
        checkTooltipNotPresent();
        checkTooltip(slider, "Tooltip");
        clearTooltip();
        checkTooltipNotPresent();
    }
}
