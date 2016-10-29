package org.isoron.uhabits.activities.notes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.isoron.uhabits.AppComponent;
import org.isoron.uhabits.HabitsApplication;
import org.isoron.uhabits.R;
import org.isoron.uhabits.activities.*;
import org.isoron.uhabits.commands.Command;
import org.isoron.uhabits.commands.CommandRunner;
import org.isoron.uhabits.models.Habit;
import org.isoron.uhabits.models.HabitList;
import org.isoron.uhabits.*;

import static org.isoron.uhabits.HabitsApplication.getContext;

public class NoteActivity extends AppCompatActivity {


    protected Habit originalHabit;

    protected Habit modifiedHabit;

    protected HabitList habitList;

    protected AppComponent appComponent;

    protected CommandRunner commandRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        Long habitID = extras.getLong("habitId");
        Log.d("tag", "habitId is " + extras);
        Log.d("tag", "habitId is " + habitID);

        TextView newtext = (TextView) findViewById(R.id.textView2);
        newtext.setText("Habit Id is: " + habitID);


        HabitsApplication app =
                (HabitsApplication) getContext().getApplicationContext();

        appComponent = app.getComponent();
        habitList = appComponent.getHabitList();
        commandRunner = appComponent.getCommandRunner();
        originalHabit = habitList.getById(habitID);

        modifiedHabit = originalHabit;
    }

    // Discard the text and go back to the previous screen
    public void discardNote(View view) {

        // Display Message and Close
        Context context = getApplicationContext();
        CharSequence text = "Discarded Note Changes!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
        finish();
    }

    // Save the Note Data to the SQL File
    public void saveNote(View view) {

        TextView newtext = (TextView)findViewById(R.id.editText4);
        modifiedHabit.setNote(newtext.getText().toString());

        // Save to the SQLite database here
        Command command = appComponent.getEditHabitCommandFactory().
                create(habitList, originalHabit, modifiedHabit);
        commandRunner.execute(command, originalHabit.getId());

        // Display Message and close
        Context context = getApplicationContext();
        CharSequence text = "Saved Note Changes!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
        finish();
    }
}
