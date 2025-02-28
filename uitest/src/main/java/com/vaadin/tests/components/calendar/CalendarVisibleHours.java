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
package com.vaadin.tests.components.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractTestUI;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Calendar.TimeFormat;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventResizeHandler;
import com.vaadin.ui.components.calendar.event.BasicEvent;

/**
 *
 * @since
 * @author Vaadin Ltd
 *
 *         test for defect: calendar visible hours of day invalid shows invalid
 *         dates(week/day view) (#12521)
 */
@Theme("tests-calendar")
public class CalendarVisibleHours extends AbstractTestUI {

    private static final long serialVersionUID = 1L;
    private Calendar calendar;

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.tests.components.AbstractTestUI#setup(com.vaadin.server.
     * VaadinRequest)
     */
    @Override
    protected void setup(VaadinRequest request) {
        calendar = new Calendar();

        try {

            BasicEvent event = new BasicEvent("EVENT NAME 1", "EVENT TOOLTIP 1",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-05 15:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-07 22:20"));
            event.setStyleName("color1");
            calendar.addEvent(event);

            event = new BasicEvent("EVENT NAME 2", "EVENT TOOLTIP 2",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-05 12:10"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-05 13:20"));
            event.setStyleName("color2");
            calendar.addEvent(event);

            event = new BasicEvent("EVENT NAME 3", "EVENT TOOLTIP 3",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-01 11:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-29 15:20"));
            event.setStyleName("color3");
            calendar.addEvent(event);

            event = new BasicEvent("EVENT NAME 4", "EVENT TOOLTIP 4",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-01 11:30"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse("2013-09-01 15:20"));
            event.setStyleName("color4");
            event.setAllDay(true);
            calendar.addEvent(event);
        } catch (ParseException e1) { // Nothing to do
            e1.printStackTrace();
        }

        try {
            calendar.setStartDate(
                    new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-01"));
            calendar.setEndDate(
                    new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-30"));
        } catch (ParseException e) { // Nothing to do

        }

        calendar.setImmediate(true);

        calendar.setFirstVisibleHourOfDay(8);
        calendar.setLastVisibleHourOfDay(16);

        calendar.setTimeFormat(TimeFormat.Format24H);
        calendar.setHandler((EventResizeHandler) null);
        setEnabled(true);

        addComponent(calendar);
        calendar.setSizeFull();
        setSizeFull();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.tests.components.AbstractTestUI#getTestDescription()
     */
    @Override
    protected String getTestDescription() {
        return "Validate fix of defect 'Calendar visible hours of day invalid shows invalid dates(week/day view)'";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.tests.components.AbstractTestUI#getTicketNumber()
     */
    @Override
    protected Integer getTicketNumber() {
        return 12521;
    }

}
