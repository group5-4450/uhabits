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

package org.isoron.uhabits.activities.habits.show.views;

import android.annotation.*;
import android.content.*;
import android.content.res.*;
import android.util.*;
import android.widget.*;

import org.isoron.uhabits.*;
import org.isoron.uhabits.models.*;
import org.isoron.uhabits.utils.*;

import butterknife.*;

public class NoteCard extends HabitCard
{
    @BindView(R.id.questionLabel)
    TextView questionLabel;


    public NoteCard(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @Override
    protected void refreshData()
    {
        Habit habit = getHabit();
        int color = ColorUtils.getColor(getContext(), habit.getColor());


        questionLabel.setVisibility(VISIBLE);

        questionLabel.setTextColor(color);
        questionLabel.setText("NOTES:");


        TextView newtext = (TextView) findViewById(R.id.textView6);
        newtext.setText(habit.getDescription());



        if (habit.hasReminder()) updateReminderText(habit.getReminder());

        if (habit.getDescription().isEmpty()) questionLabel.setVisibility(GONE);

        invalidate();
    }

    private void init()
    {
        inflate(getContext(), R.layout.show_habit_note, this);
        ButterKnife.bind(this);

        if (isInEditMode()) initEditMode();
    }

    @SuppressLint("SetTextI18n")
    private void initEditMode()
    {
        questionLabel.setTextColor(ColorUtils.getAndroidTestColor(1));
        questionLabel.setText("Have you meditated today?");

    }

    private String toText(Frequency freq)
    {
        Resources resources = getResources();
        Integer num = freq.getNumerator();
        Integer den = freq.getDenominator();

        if (num.equals(den)) return resources.getString(R.string.every_day);

        if (num == 1)
        {
            if (den == 7) return resources.getString(R.string.every_week);
            if (den % 7 == 0)
                return resources.getString(R.string.every_x_weeks, den / 7);
            return resources.getString(R.string.every_x_days, den);
        }

        String times_every = resources.getString(R.string.times_every);
        return String.format("%d %s %d %s", num, times_every, den,
                resources.getString(R.string.days));
    }

    private void updateReminderText(Reminder reminder)
    {

    }
}
