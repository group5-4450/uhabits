package org.isoron.uhabits.activities.notes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.isoron.uhabits.R;
import org.isoron.uhabits.activities.*;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
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

        // Save to the SQLite database here


        // Display Message and close
        Context context = getApplicationContext();
        CharSequence text = "Saved Note Changes!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
        finish();
    }
}
