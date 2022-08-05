package edu.pdx.cs410j.moes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<PhoneBill> bills = null;
    private PhoneCall call = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchCalculator(View view){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void returnHome(View view){
        setContentView(R.layout.activity_main);
    }

    public void addPhoneCall(View view){
        Toast.makeText(MainActivity.this, "just a invoke test", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_add_phone_call);
    }

    public void postNewCall(View view){
        EditText customer = findViewById(R.id.CustomerName);
        EditText callerNumber = findViewById(R.id.CallerNumber);
        EditText calleeNumber = findViewById(R.id.CalleeNumber);
        EditText beginTime = findViewById(R.id.BeginTime);
        EditText EndTime = findViewById(R.id.EndTime);
        PhoneCall toAdd = new PhoneCall(customer.getText().toString(), callerNumber.getText().toString(),
                calleeNumber.getText().toString(), beginTime.getText().toString(), EndTime.getText().toString());
        if (bills == null){
            bills = new ArrayList<PhoneBill>();
        }
        // search for phone bill
        PhoneBill bill = new PhoneBill(customer.getText().toString());
        bill.addPhoneCall(toAdd);
        bills.add(bill);
        Toast.makeText(MainActivity.this, customer.getText().toString() + " Call submitted", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);
    }

    public void searchAndDisplayBill(View view) {
        // try to make it get input from one input first then hit submit and use that as the customer name
        setContentView(R.layout.searchresult);
        TextView display = findViewById(R.id.PhoneCallPrint);
        String customer = "Taco";
        PhoneBill toPrint = searchCustomer(customer);
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        if (toPrint != null){
            display.append(prettyPrinter.dumpString(toPrint));
        }
        else{
            display.append("No customer found");
        }
        // setContentView(R.layout.activity_main);
    }

    private PhoneBill searchCustomer(String name){
        PhoneBill toReturn = null;
        for (PhoneBill bill : bills){
            if (bill.getCustomer().equalsIgnoreCase(name)){
                toReturn = bill;
                break;
            }
        }
        return toReturn;
    }

}

