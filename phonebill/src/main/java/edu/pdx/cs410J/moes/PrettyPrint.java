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
            pw.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
            if (bill.getPhoneCalls() != null) {
                pw.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
                for (PhoneCall to_add : bill.getPhoneCalls()) {
                    pw.println("Call from " + to_add.getCaller() + " to " + to_add.getCallee() + ", at " +
                            to_add.getBeginTimeString() + " to " + to_add.getEndTimeString() + "." +
                            " Originating from " + to_add.getCallerNumber() + " and contacting " +
                            to_add.getCalleeNumber());
                }
            }
            else {
                pw.println("Phone bill of " + bill.getCustomer() + ", with zero calls");
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

    /**
     * This method is the one that prints the pretty print to the standard out
     * Aka sys.out etc
     */
    public void dump(){

    }
}
