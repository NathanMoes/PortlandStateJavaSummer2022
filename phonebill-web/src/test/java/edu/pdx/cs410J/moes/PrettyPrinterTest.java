package edu.pdx.cs410J.moes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements the testing for the pretty print class
 */


public class PrettyPrinterTest {
    /**
     * Tests that the class constructor works with null
     */
    @Test
    void testConstructor(@TempDir File file){
        PrettyPrinter prettyPrinter = new PrettyPrinter(null);
    }

    /**
     * Tests that the dump with a phone bill can be called
     */
    @Test
    void testDumpCanCallPhoneBillVersion(){
        try {
            PrettyPrinter prettyPrinter = new PrettyPrinter(new FileWriter(new File("testing pretty.txt")));
            prettyPrinter.dump(new PhoneBill());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test that the File variant of dump can be called
     */
    @Test
    void testWeCanCallFileVersionOfDump(){
        File file = new File("testingprettyprint.txt");
        PhoneBill bill = new PhoneBill();
        PhoneCall call = new PhoneCall();
        bill.addPhoneCall(call);
        try {
            PrettyPrinter prettyPrinter = new PrettyPrinter(new FileWriter(file));
            prettyPrinter.dump(bill);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Test that we can call the version of dump with no arguments
     */
    @Test
    void testCanCallNoArgsOfTheDump(){
        PhoneBill bill = new PhoneBill();
        PhoneCall call = new PhoneCall();
        bill.addPhoneCall(call);
        try {
            PrettyPrinter prettyPrinter = new PrettyPrinter(new FileWriter(new File("testingpretty.txt")));
            prettyPrinter.dumpStandard(bill);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
