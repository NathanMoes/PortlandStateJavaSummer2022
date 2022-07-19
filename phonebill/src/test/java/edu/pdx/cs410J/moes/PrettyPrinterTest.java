package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        PrettyPrinter prettyPrinter = new PrettyPrinter(null);
        try {
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
        PrettyPrinter prettyPrinter = new PrettyPrinter(bill);
        prettyPrinter.dump(file);
    }

    /**
     * Test that we can call the version of dump with no arguments
     */
    @Test
    void testCanCallNoArgsOfTheDump(){
        PhoneBill bill = new PhoneBill();
        PhoneCall call = new PhoneCall();
        bill.addPhoneCall(call);
        PrettyPrinter prettyPrinter = new PrettyPrinter(bill);
        prettyPrinter.dump();
    }
}
