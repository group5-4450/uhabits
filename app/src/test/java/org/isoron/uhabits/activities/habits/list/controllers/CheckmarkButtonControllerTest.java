/*
 * Copyright (C) 2016 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.isoron.uhabits.activities.habits.list.controllers;

import org.isoron.uhabits.*;
import org.isoron.uhabits.activities.habits.list.views.*;
import org.isoron.uhabits.models.*;
import org.isoron.uhabits.models.sqlite.records.HabitRecord;
import org.isoron.uhabits.preferences.*;
import org.junit.*;
import org.isoron.uhabits.utils.DateUtils;

import static org.mockito.Mockito.*;

public class CheckmarkButtonControllerTest extends BaseUnitTest
{
    private CheckmarkButtonController controller;

    private CheckmarkButtonController controller1;

    private CheckmarkButtonController controller2;

    private CheckmarkButtonController controller3;

    private CheckmarkButtonController controller4;

    private CheckmarkButtonController controller5;

    private CheckmarkButtonView view;

    private CheckmarkButtonController.Listener listener;

    private Habit habit;

    private long timestamp;

    private Preferences prefs;

    private HabitRecord habitRecord;

    @Override
    @Before
    public void setUp()
    {
        super.setUp();

        timestamp = 0;

        // model 1 for MC/DC test 1
        Habit model_1 = modelFactory.buildHabit();
        model_1.setDayCount(DateUtils.getStartOfToday());
        model_1.setType("Regular");
        model_1.setNumerical(2);
        model_1.setCount(0);
        model_1.setName("Test1");
        model_1.setId(1L);

        Habit model_2 = modelFactory.buildHabit();
        model_2.setDayCount(DateUtils.getStartOfToday());
        model_2.setType("Numeric");
        model_2.setNumerical(1);
        model_2.setCount(0);
        model_2.setName("Test2");
        model_2.setId(2L);

        Habit model_3 = modelFactory.buildHabit();
        model_3.setDayCount(DateUtils.getStartOfToday());
        model_3.setType("Numeric");
        model_3.setNumerical(1);
        model_3.setCount(0);
        model_3.setName("Test3");
        model_3.setId(3L);

        Habit model_4 = modelFactory.buildHabit();
        model_4.setDayCount(DateUtils.getStartOfToday());
        model_4.setType("Numeric");
        model_4.setNumerical(0);
        model_4.setCount(0);
        model_4.setName("Test4");
        model_4.setId(4L);

        Habit model_5 = modelFactory.buildHabit();
        model_5.setDayCount(DateUtils.getStartOfToday());
        model_5.setType("Numeric");
        model_5.setNumerical(2);
        model_5.setCount(0);
        model_5.setName("Test1");
        model_5.setId(1L);

        habit = mock(Habit.class);
        prefs = mock(Preferences.class);

        this.view = mock(CheckmarkButtonView.class);
        this.listener = mock(CheckmarkButtonController.Listener.class);
        this.controller =
            new CheckmarkButtonController(prefs, habit, timestamp);
        controller.setView(view);
        controller.setListener(listener);

        this.controller1 =
                new CheckmarkButtonController(prefs, model_1, timestamp);
        controller1.setView(view);
        controller1.setListener(listener);

        this.controller2 =
                new CheckmarkButtonController(prefs, model_2, timestamp);
        controller2.setView(view);
        controller2.setListener(listener);

        this.controller3 =
                new CheckmarkButtonController(prefs, model_3, timestamp);
        controller3.setView(view);
        controller3.setListener(listener);

        this.controller4 =
                new CheckmarkButtonController(prefs, model_4, timestamp);
        controller4.setView(view);
        controller4.setListener(listener);
    }

    @Test
    public void testPerformToggle_MCDC1()
    {
        timestamp = DateUtils.getStartOfToday();
        controller1.performToggle();
    }

    @Test
    public void testPerformToggle_MCDC2()
    {
        timestamp = org.isoron.uhabits.utils.DateUtils.getStartOfToday();
        controller2.performToggle();
    }

    @Test
    public void testPerformToggle_MCDC3()
    {
        timestamp = 0;
        controller3.performToggle();
    }

    @Test
    public void testPerformToggle_MCDC4()
    {
        timestamp = org.isoron.uhabits.utils.DateUtils.getStartOfToday();
        controller4.performToggle();
    }

    @Test
    public void testPerformToggle_MCDC5()
    {
        timestamp = org.isoron.uhabits.utils.DateUtils.getStartOfToday();
        controller5.performToggle();
    }

    @Test
    public void testOnClick_withShortToggle() throws Exception
    {
        doReturn(true).when(prefs).isShortToggleEnabled();
        controller.onClick();
        verifyToggle();
    }

    @Test
    public void testOnClick_withoutShortToggle() throws Exception
    {
        doReturn(false).when(prefs).isShortToggleEnabled();
        controller.onClick();
        verifyInvalidToggle();
    }

    @Test
    public void testOnLongClick() throws Exception
    {
        controller.onLongClick();
        verifyToggle();
    }

    protected void verifyInvalidToggle()
    {
        verifyZeroInteractions(view);
        verify(listener).onInvalidToggle();
    }

    protected void verifyToggle()
    {
        verify(view).toggle();
        verify(listener).onToggle(habit, timestamp);
    }
}