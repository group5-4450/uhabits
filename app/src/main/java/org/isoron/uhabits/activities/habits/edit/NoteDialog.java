package org.isoron.uhabits.activities.habits.edit;



import org.isoron.uhabits.HabitsApplication;
import org.isoron.uhabits.R;
import org.isoron.uhabits.models.ModelObservable;

import android.app.Activity;
import android.app.Dialog;
import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.*;
import android.widget.ProgressBar;

import com.android.colorpicker.ColorPickerPalette;

import butterknife.ButterKnife;


/**
 * Created by hodiete on 28/10/16.
 */

public class NoteDialog extends BaseDialog {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.note_editor, container, false);

        HabitsApplication app =
                (HabitsApplication) getContext().getApplicationContext();

        appComponent = app.getComponent();
        prefs = appComponent.getPreferences();
        habitList = appComponent.getHabitList();
        commandRunner = appComponent.getCommandRunner();
        modelFactory = appComponent.getModelFactory();

        ButterKnife.bind(this, view);

        helper = new BaseDialogHelper(this, view);
        getDialog().setTitle(getTitle());
        initializeHabits();
        restoreSavedInstance(savedInstanceState);
        helper.populateForm(modifiedHabit);
        return view;
    }

    @Override
    protected int getTitle()
    {
        return R.string.note;
    }

    @Override
    protected void initializeHabits() {

    }

    @Override
    protected void saveHabit() {

    }
}
