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
package com.vaadin.tests.push;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class ReconnectStreamingTest extends ReconnectTest {

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {

        // PhantomJS doesn't seem to detect disconnection on
        // Long-Polling/Streaming:
        // https://github.com/ariya/phantomjs/issues/11938
        return getBrowsersExcludingPhantomJS();
    }

    @Override
    protected Class<?> getUIClass() {
        return BasicPushStreaming.class;
    }

}
