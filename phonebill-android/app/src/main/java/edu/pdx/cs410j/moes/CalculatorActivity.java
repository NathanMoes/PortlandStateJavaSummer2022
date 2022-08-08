package edu.pdx.cs410j.moes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CalculatorActivity extends AppCompatActivity {

    /**
     * This is the on create method for the activity for this class
     * @param savedInstanceState is the saved instance data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator);
    }
}