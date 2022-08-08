package edu.pdx.cs410j.moes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayText extends AppCompatActivity {

    /**
     * this is the function for the oncreate method invoked at the creation of the activity
     * As this activity only does one thing, it just does that thing here.
     * @param savedInstanceState is the saved instance data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);
        TextView textView = findViewById(R.id.DisplayTextMain);
        Intent intent = getIntent();
        String toDisplay = intent.getExtras().getString("toDisplay");
        textView.setText(toDisplay);
    }

}