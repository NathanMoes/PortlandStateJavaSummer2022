package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;

public class PrettyPrinter implements PhoneBillDumper<PhoneBill> {
    /**
     * this data member contains the phone bill to print
     */
    private final PhoneBill bill;

    /**
     * constructor that sets the phone bill
     * @param phoneBill is the phone bill to init this class to
     */
    public PrettyPrinter(PhoneBill phoneBill) {
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
            // pw.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
            if (bill.getPhoneCalls() != null) {
                pw.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
                for (PhoneCall to_add : bill.getPhoneCalls()) {
                    pw.println("Call from " + to_add.getCaller() + " to " + to_add.getCallee() + ", at " +
                            to_add.getCallBegin() + " to " + to_add.getCallEnd() + "." +
                            " Originating from " + to_add.getCallerNumber() + " and contacting " +
                            to_add.getCalleeNumber() + ".");
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
     * This function acts to dump the pretty print to standard output
     * just as the above does for a file
     */
    public void dump(){
            // System.out.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
            if (bill.getPhoneCalls() != null) {
                System.out.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
                for (PhoneCall to_add : bill.getPhoneCalls()) {
                    System.out.println("Call from " + to_add.getCaller() + " to " + to_add.getCallee() + ", at " +
                            to_add.getCallBegin() + " to " + to_add.getCallEnd() + "." +
                            " Originating from " + to_add.getCallerNumber() + " and contacting " +
                            to_add.getCalleeNumber() + ", and lasting " + to_add.getCallTimeMinute() + " minutes");
                }
            }
            else {
                System.out.println("Phone bill of " + bill.getCustomer() + ", with zero calls");
            }
    }

    /**
     * @param phoneBill phone bill to print
     * @throws IOException when stuff goes wrong
     */
    @Override
    public void dump(PhoneBill phoneBill) throws IOException {
        return;
    }

    /**
     * This method is the one that prints the pretty print to the standard out
     * Aka sys.out etc
     */

    // -textFile
    //moes/moes.txt

}
