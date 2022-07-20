//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class Project3ITT extends InvokeMainTestCase {
    Project3ITT() {
    }

    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return this.invokeMain(Project3.class, args);
    }

    @Test
    void testNoCommandLineArguments() {
        InvokeMainTestCase.MainMethodResult result = this.invokeMain();
        MatcherAssert.assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Missing command line arguments"));
    }

    /**
     * Tests that if there is an unsupported option it is reported
     */
    @Test
    void testInvalidOption(){
        String [] args = {"-fred", "2", "3" , "4" , "5" , "6" , "7"};
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid command line argument(s) for options"));
    }

    /**
     * Tests to check if an error is made if there are too many options passed in
     */
    @Test
    void testTooManyOptions(){
        String [] args = {"-README", "-print", "-print", "-textFile", "-pretty", "adssadq"};
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Too many options arguments given, options include -README, Textfile, and print"));
    }

    /**
     * Tests that the file is a blank one and does not find the file aka result is null, and also prints the new call
     */
    @Test
    void testMainResultIsNullAndToStringPrint(){
        String [] args = {"-print", "-textFile", "empty-phonebill.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
                "10:30", "AM", "11/12/2011", "11:30", "AM"};
        String check_against = "Phone call from Mike to Not given from 11/11/11 to 11/12/11";
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString(check_against));
    }


    /**
     * Tests the we can get a null from result in search for file
     */
    @Test
    void testWeGetNullInResultBranch() {
        String [] args = {"-textFile", "definatrlynotafilethatexists.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
                "10:30", "AM", "11/12/2011", "11:30", "AM"};
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        // MatcherAssert.assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString(check_against));
    }


    /**
     * -textFile moes/moes.txt -pretty moesout.txt Thomas 111-234-2341 123-421-4362 09/28/2000 9:16 pm 09/28/2000 9:20 pm
     * Tests that we get pretty print to print to standard out for the data passsed in
     */
    @Test
    void testWeGetPrettyOut(){
        String [] args = {"-textFile", "moes/moes.txt", "-pretty", "-", "Thomas", "111-234-2341",
                "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
        String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString(check_against));
    }

    /**
     * Testing to see that it prints to a file
     */
    @Test
    void testWeGetPrettyToPrintToFile(){
        String [] args = {"-textFile", "moes/moes.txt", "-pretty", "moesout.txt", "Thomas", "111-234-2341",
                "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
    }

}
