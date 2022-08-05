package edu.pdx.cs410j.moes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddPhoneCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_call);
    }

    public void returnToMain(View view){
        Intent data = new Intent();
        data.putExtra("Customer", new PhoneCall());
        setResult(RESULT_OK, data);
    }

}