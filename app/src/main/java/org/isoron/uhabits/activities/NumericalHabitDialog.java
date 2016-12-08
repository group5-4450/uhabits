package org.isoron.uhabits.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import org.isoron.uhabits.R;
import org.isoron.uhabits.models.Habit;

/**
 * Created by harrietodiete on 2016-12-02.
 */

@SuppressLint("ValidFragment")
public class NumericalHabitDialog extends AppCompatDialogFragment{
    AlertDialog dialog;
    private EditText numericalValue;
    private Button saveButton;
    private Button discardButton;
    private Switch numericSwitch;
    private String number;
    private Habit habit;
    private int numerical;

    @SuppressLint("ValidFragment")
    public NumericalHabitDialog(Switch numericalSwitch, Habit habit) {
        this.numericSwitch = numericalSwitch;
        this.habit = habit;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.numeric_habit_dialog, null);
        builder.setView(view);
        builder.setTitle("Numerical Habit");

        numericalValue = (EditText) view.findViewById(R.id.numericalValue);

        if (habit.getNumerical() != 0) {
            numericalValue.setText(habit.getNumerical().toString());
        }

        saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            number = numericalValue.getText().toString();
            if(number.isEmpty())
            {
                numericalValue.setError("No numeric value was entered");
            }
            else
            {
                // save numerical value of habit here
                Log.d("In Save Button", "\n" + habit.getType());
                numerical = Integer.parseInt(numericalValue.getText().toString());
                habit.setNumerical(numerical);
                dialog.dismiss();
            }
        });

        discardButton = (Button) view.findViewById(R.id.discardButton);
        discardButton.setOnClickListener(v -> {
            if(numericalValue.getText().toString().isEmpty() && habit.getNumerical() == 0)
            {
                if(numericSwitch.isChecked())
                {
                    dialog.setTitle("yes");
                    numericSwitch.setChecked(false);
                    Log.d("In Discard Button", "\n" + habit.getType());
                }
            }
            dialog.dismiss();
        });

        dialog = builder.create();
        return dialog;
    }

}
