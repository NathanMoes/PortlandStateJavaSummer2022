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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    /**
     * Things To complete
     *
     *
     */
    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa",
            Locale.getDefault());

    public ArrayList<PhoneBill> bills = null;
    private PhoneCall call = null;

    /**
     * This is the on create method for this activity. It initialises things for it.
     * @param savedInstanceState is the saved state for the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            this.readFromFile();
        }
        catch (ParseException | IOException e ){
            // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, DisplayText.class);
            intent.putExtra("toDisplay", e.getMessage() + "Failed file read");
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
    }

    /**
     * This is the onResume function
     */
    @Override
    protected void onResume(){
        super.onResume();
        File dataFile = getDataFile();
        dataFile.delete();
    }

    /**
     * This is just a temporary thing for testing. Ignore it
     * @param view is a view widget
     */
    public void launchCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            this.writeToFile();
        }
        catch (IOException e){
            Toast.makeText(this, e.getMessage() + ": failed to write data", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This is a function to return to the main view. Aka the home view with the option buttons
     * @param view is a view widget
     */
    public void returnHome(View view){
        setContentView(R.layout.activity_main);
    }

    /**
     * This function acts to get information from the user to add in a new phone call
     * @param view is a view widget
     */
    public void addPhoneCall(View view){
        // Toast.makeText(MainActivity.this, "just a invoke test", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_add_phone_call);
    }

    /**
     * This function acts to add in the phone call once input is given from the above function call
     * @param view is a view widget
     */
    public void postNewCall(View view) {
        EditText customer = findViewById(R.id.CustomerName);
        EditText callerNumber = findViewById(R.id.CallerNumber);
        EditText calleeNumber = findViewById(R.id.CalleeNumber);
        EditText beginTime = findViewById(R.id.BeginTime);
        EditText EndTime = findViewById(R.id.EndTime);
        String customerName = customer.getText().toString();
        String callerNumberString = callerNumber.getText().toString();
        String calleeNumberString = calleeNumber.getText().toString();
        String beginTimeString = beginTime.getText().toString();
        String endTimeString = EndTime.getText().toString();
        PhoneBill bill = null;
        if (quickDateCheck(beginTimeString, endTimeString) ) {
            if (validate_number(calleeNumberString) && validate_number(callerNumberString)) {
                PhoneCall newCall = new PhoneCall(customerName, callerNumberString,
                        calleeNumberString, beginTimeString, endTimeString);
                if (bills == null) {
                    bills = new ArrayList<PhoneBill>();
                }
                // search for phone bill
                bill = searchCustomer(customerName);
                if (bill == null)
                    bill = new PhoneBill(customerName);
                bill.addPhoneCall(newCall);
                bills.add(bill);
                Toast.makeText(MainActivity.this, customer.getText().toString() + " Call submitted", Toast.LENGTH_LONG).show();
            }
            else{
                // Toast.makeText(MainActivity.this, "Malformed phone number used", Toast.LENGTH_LONG).show();
            }
        }
        else{
            // Toast.makeText(MainActivity.this, "Malformed phone number used", Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
    }

    /**
     * This function acts to display the phone bill of a customer in pretty print
     * @param view this is a view widget
     */
    public void searchAndDisplayBill(View view) {
        EditText customerInput = findViewById(R.id.EditTextCustomer);
        // setContentView(R.layout.promptinput);
        String customer = customerInput.getText().toString();
        PhoneBill toPrint = searchCustomer(customer);
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        if (toPrint != null){
            Intent intent = new Intent(this, DisplayText.class);
            intent.putExtra("toDisplay", prettyPrinter.dumpString(toPrint));
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "Customer not found: " + customer, Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
    }

    /**
     * This function acts to get input from the user in a view for the display of calls in a time
     * range
     * @param view is a view widget
     */
    public void printInRangeOf(View view){
        setContentView(R.layout.promptinput);
    }

    /**
     * This function acts on the data gathered in the above function to display the given calls
     * in a time range as passed in from call args
     * @param view is a view widget
     */
    public void printInRangeHelper(View view){
        EditText customer = findViewById(R.id.CustomerNameSearch);
        EditText beginTime = findViewById(R.id.BeginTimeSearch);
        EditText endTime = findViewById(R.id.EndTimeSearch);
        String customerName = customer.getText().toString();
        String beginTimeString = beginTime.getText().toString();
        String endTimeString = endTime.getText().toString();
        PhoneBill bill = searchCustomer(customerName);
        if (!quickDateCheck(beginTimeString, endTimeString))
            Toast.makeText(this, "Invalid date/time", Toast.LENGTH_LONG).show();
        PhoneBill toPrint = new PhoneBill(customerName);
        if (bill == null)
            Toast.makeText(this, "Customer name not found", Toast.LENGTH_LONG).show();
        else {
            Collection<PhoneCall> calls = bill.getCallsInRange(beginTimeString, endTimeString);
            for (PhoneCall call : calls){
                toPrint.addPhoneCall(call);
            }
            PrettyPrinter prettyPrinter = new PrettyPrinter();
            Intent intent = new Intent(this, DisplayText.class);
            intent.putExtra("toDisplay", prettyPrinter.dumpString(toPrint));
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
    }

    /**
     * This function acts to search for a phone bill for a given customer
     *
     * @param name is the name of the customer to search for
     * @return returns null if there is no found customer matching name, else it returns the bill
     * object that has the customers name
     */
    private PhoneBill searchCustomer(String name) {
        if (this.bills == null)
            return null;
        PhoneBill toReturn = null;
        for (PhoneBill bill : this.bills){
            if (bill.getCustomer().equalsIgnoreCase(name)){
                toReturn = bill;
                break;
            }
        }
        return toReturn;
    }

    /**
     * This function acts to perform a quick check on the dates passed in and checks if they have
     * imporper formatting
     * @param begin is the begin date/time
     * @param end is the end date/time
     * @return returns true if its a vaild date/time combo, otherwise it returns false
     */
    public boolean quickDateCheck(String begin, String end){
        try{
            dateFormat.parse(begin);
        }
        catch (ParseException e){
            String text =  "Malformed begin date" + e.getMessage();
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            return false;
        }
        try{
            dateFormat.parse(end);
        }
        catch (ParseException e){
            String text =  "Malformed end date" + e.getMessage();
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    /**
     * This function acts to validate the phone number passed in
     * @param to_validate is the phone number to validate
     * @return true if valid, false otherwise
     */
   public boolean validate_number(String to_validate){
        int dashes = 0; // should be 2
        int numbers = 0; // should be 10, 3-3-4
        for (int i = 0; i < to_validate.length(); i+=1){
            if (to_validate.charAt(i) == '-')
                dashes += 1;
            else if (Character.isDigit(to_validate.charAt(i))) {
                numbers += 1;
            }
        }
        if (dashes < 2){
            String text = "too few dashes in phone number";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        } else if (dashes > 2) {
            String text = "too many dashes in phone number";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        if (numbers != 10){
            String text = "contains too many non number characters, or too many numbers";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
        return numbers == 10 && dashes == 2;
    }

    /**
     * Simple function to display a help text, giving information on how to run the app
     * @param view is a view widget
     */
    public void helpDisplay(View view){
        String toDisplay = "For simple functions that only use the customer as input, please enter customer number in " +
                "main screen\n" +
                "For other functions please press button and enter input in all prompts as displayed\n" +
                "Each will give information as to the input to use\n" +
                "For dates the expected format is dd/MM/yyyy hh:mm aa\n" +
                "";
        Intent intent = new Intent(this, DisplayText.class);
        intent.putExtra("toDisplay", toDisplay);
        startActivity(intent);
    }

    /**
     * This function creates a new phone bill from the customer arguments passed in from main
     * text prompt
     * @param view is a view widget
     */
    public void createNewPhoneBillNoCalls(View view){
        EditText customer = findViewById(R.id.EditTextCustomer);
        if (this.bills == null){
            this.bills = new ArrayList<PhoneBill>();
        }
        this.bills.add(new PhoneBill(customer.getText().toString()));
        Toast.makeText(this, "Phone bill created: " + customer.getText().toString(),
                Toast.LENGTH_LONG).show();
    }

    /**
     * This function will act to write data to the file, dumping the data to a text file
     * Thus, allowing for persistence in memory
     */
    public void writeToFile() throws IOException {
        File dataFile = getDataFile();
        TextDumper dumper = new TextDumper(new FileWriter(dataFile));
        if (this.bills == null)
            return;
        dumper.dumpToFile(this.bills);
    }


    /**
     * This function acts to read in the data for phone bills from a text file. Setting the bills
     * argument to the new collection of phone bills
     */
    public void readFromFile() throws IOException, ParseException{
        File dataFile = getDataFile();
        TextParser parser = new TextParser(new FileReader(dataFile));
        this.bills = parser.parsePhoneFromFile();
    }


    /**
     * This function will act to retrieve the data file that stores the apps phone bill data
     */
    private File getDataFile(){
        File file = new File(this.getFilesDir(), "CS410JDATA.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                Intent intent = new Intent(this, DisplayText.class);
                intent.putExtra("toDisplay", e.getMessage() + "Failed file read");
                startActivity(intent);
            }
        }
        return file;
    }

}

