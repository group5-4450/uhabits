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

package org.isoron.uhabits.activities.habits.show;

import android.app.AlertDialog;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.annotation.*;
import android.view.View;

import org.isoron.uhabits.*;
import org.isoron.uhabits.activities.*;
import org.isoron.uhabits.models.*;

/**
 * Activity that allows the user to see more information about a single habit.
 * <p>
 * Shows all the metadata for the habit, in addition to several charts.
 */
public class ShowHabitActivity extends BaseActivity
{
    private HabitList habits;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        HabitsApplication app = (HabitsApplication) getApplicationContext();
        habits = app.getComponent().getHabitList();
        Habit habit = getHabitFromIntent();

        ShowHabitComponent component = DaggerShowHabitComponent
            .builder()
            .appComponent(app.getComponent())
            .showHabitModule(new ShowHabitModule(this, habit))
            .build();

        ShowHabitRootView rootView = component.getRootView();
        rootView.overviewCard.scoreRing.setOnClickListener(RV_listener);

        ShowHabitScreen screen = component.getScreen();

        setScreen(screen);
        screen.setMenu(component.getMenu());
        screen.setController(component.getController());
        rootView.setController(component.getController());

        screen.reattachDialogs();
    }

    View.OnClickListener RV_listener = new View.OnClickListener() {
        public void onClick(View v) {

            AlertDialog.Builder RingView_dialog_builder = new AlertDialog.Builder(ShowHabitActivity.this);
            RingView_dialog_builder.setMessage("How is the score calculated?\n" +
                    "\n" +
                    "To compute the score of a habit, the app uses a statistical method known as exponential smoothing. Basically, it computes a weighted average that takes into consideration every repetition of the habit, from the very first day you started your habit until today. Recent repetitions, however, are considered more important than older ones, and have a larger impact on the score. This method has many nice properties, including:\n" +
                    "\n" +
                    "  1. Every repetition counts, even way back in the past. This is not true for other simpler formulas, such as counting how many times have you performed the habit in the last week/month/year and then dividing by the number of days in that interval.\n" +
                    "  2. If you have a poor habit score, then a few repetitions can bring your score up very quickly. As your score improves, however, the reward for each repetition gets smaller and smaller, so you have to keep at it if you want to see further increases.\n" +
                    "  3. If you have a high score for a very long time and you take a short break, it's quite easy to restore your score back to what it was. If you start taking frequent breaks, however, then your score will suffer.\n" +
                    "\n" +
                    "The precise parameters on the formula were tweaked so that, if you perform a new daily habit perfectly, the score reaches 80% after one month, 96% after two months and 99% after three months. For non-daily habits, it takes longer to reach the same percentages. If your habit is repeated every other day, for example, then it takes two months to reach 80%, and if you habit is weekly, then it takes seven months.");
            RingView_dialog_builder.setCancelable(true);

            RingView_dialog_builder.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = RingView_dialog_builder.create();
            alert11.show();
        }
    };


    @NonNull
    private Habit getHabitFromIntent()
    {
        Uri data = getIntent().getData();
        Habit habit = habits.getById(ContentUris.parseId(data));
        if (habit == null) throw new RuntimeException("habit not found");
        return habit;
    }
}
