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

import android.content.SharedPreferences;
import android.support.annotation.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.auto.factory.*;

import org.isoron.uhabits.AppComponent;
import org.isoron.uhabits.R;
import org.isoron.uhabits.activities.BaseActivity;
import org.isoron.uhabits.activities.habits.list.views.*;
import org.isoron.uhabits.commands.Command;
import org.isoron.uhabits.models.*;
import org.isoron.uhabits.models.sqlite.records.HabitRecord;
import org.isoron.uhabits.preferences.*;
import org.isoron.uhabits.utils.DateUtils;

import static android.content.Context.MODE_PRIVATE;

@AutoFactory
public class CheckmarkButtonController
{
    @Nullable
    private CheckmarkButtonView view;

    @Nullable
    private Listener listener;

    @NonNull
    private final Preferences prefs;

    @NonNull
    private Habit habit;

    private HabitRecord habitRecord;

    private long timestamp;

    public CheckmarkButtonController(@Provided @NonNull Preferences prefs,
                                     @NonNull Habit habit,
                                     long timestamp)
    {
        this.habit = habit;
        this.timestamp = timestamp;
        this.prefs = prefs;
    }

    public void onClick()
    {
        if (prefs.isShortToggleEnabled()) performToggle();
        else performInvalidToggle();
    }

    public boolean onLongClick()
    {
        performToggle();
        return true;
    }

    public void performInvalidToggle()
    {
        if (listener != null) listener.onInvalidToggle();
    }

    public void performToggle()
    {
        // Gets the record of the habit using the habit's id
        try
        {
            habitRecord = HabitRecord.get(habit.getId());
        } catch(Exception e)
        {

        }

        //Log.d("Frequency", "" + habitRecord.numerical);

        if (habit.getDayCount() != DateUtils.getStartOfToday()){
            habit.setDayCount(DateUtils.getStartOfToday());
            habit.setCount(0);

        }
        // WE CHANGED THIS!
        if ((!habit.getType().equals("Regular")) && (habit.getNumerical() != 0) && (DateUtils.getStartOfToday() == timestamp)){

            if (habit.getCount() < habit.getNumerical() - 1){
                habit.setCount(habit.getCount() + 1);
                //Log.d("count", "increased");
                //Log.d("count", "" + habit.getCount());

                listener.onNumericalToggle(habit);
            }
            else {
                //Log.d("count", "equals!");
                if (view != null) view.toggle();
                if (listener != null) listener.onToggle(habit, timestamp);
            }
        }
        else {
            //Log.d("normal", "normal");
            if (view != null) view.toggle();
            if (listener != null) listener.onToggle(habit, timestamp);
        }

        try
        {
            habitRecord.copyFrom(habit);
            habitRecord.save();
        } catch (Exception e)
        {

        }

    }

    public void setListener(@Nullable Listener listener)
    {
        this.listener = listener;
    }

    public void setView(@Nullable CheckmarkButtonView view)
    {
        this.view = view;
    }

    public interface Listener
    {
        /**
         * Called when the user's attempt to perform a toggle is rejected.
         */
        void onInvalidToggle();

        void onNumericalToggle(Habit habit);


        void onToggle(@NonNull Habit habit, long timestamp);
    }
}
