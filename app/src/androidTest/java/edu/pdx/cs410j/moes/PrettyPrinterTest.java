package edu.pdx.cs410j.moes;

import org.apache.groovy.json.internal.IO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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


    /**
     * Test to get the word format count
     */
    @Test
    void testWordCount() throws IOException {
        File file = new File("prettyprinttest.txt");
        PrettyPrinter prettyPrinter = new PrettyPrinter(new FileWriter(file));
        assertThat(prettyPrinter.formatWordCount(10), containsString("Dictionary on server contains 10 words"));
        assertThat(prettyPrinter.formatDictionaryEntry("word", "def"), containsString("  word : def"));
        file.delete();
    }


    /**
     * Test to get the dump of text map to print values passed in
     */
    @Test
    void testMapPrintPretty() throws IOException{
        File file = new File("prettyprinttest.txt");
        PrettyPrinter prettyPrinter = new PrettyPrinter(new FileWriter(file));
        prettyPrinter.dump(Map.of("String", "String", "two", "two"));
        file.delete();
    }

    /**
     * Test the dumping of an empty phone bill gives a bill of zero calls
     */
    @Test
    void testBlankBillZeroCallsPretty() throws IOException{
        File file = new File("prettyprinttest.txt");
        PrettyPrinter prettyPrinter = new PrettyPrinter(new FileWriter(file));
        prettyPrinter.dump(new PhoneBill("customer"));
        prettyPrinter.dumpStandard(new PhoneBill("customer"));
        file.delete();
    }
}
