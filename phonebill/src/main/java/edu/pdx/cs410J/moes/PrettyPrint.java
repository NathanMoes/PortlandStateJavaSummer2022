package edu.pdx.cs410J.moes;
import edu.pdx.cs410J.AppointmentBookDumper;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;

public class PrettyPrint {
    /**
     * this data member contains the phone bill to print
     */
    private final PhoneBill bill;

    /**
     * constructor that sets the phone bill
     * @param phoneBill is the phone bill to init this class to
     */
    public PrettyPrint(PhoneBill phoneBill) {
        this.bill = phoneBill;
    }

    /**
     * This method prints the pretty print format to a file
     * @param file is the file that will be printed to
     */
    public void dump(File file){
        try (
                PrintWriter pw = new PrintWriter(new FileWriter(file));
        ) {
            pw.println(bill.getCustomer());
            if (bill.getPhoneCalls() != null) {
                for (PhoneCall to_add : bill.getPhoneCalls()) {
                    pw.println(to_add.getCaller());
                    pw.println(to_add.getCallee());
                    pw.println(to_add.getCallerNumber());
                    pw.println(to_add.getCalleeNumber());
                    pw.println(to_add.getBeginTimeString());
                    pw.println(to_add.getEndTimeString());
                }
            }
        }
        catch(IOException e){
            e.getMessage();
        }

    }

    /**
     * This method is the one that prints the pretty print to the standard out
     * Aka sys.out etc
     */
    public void dump(){

    }
}
