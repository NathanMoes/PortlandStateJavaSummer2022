//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class Project2ITT extends InvokeMainTestCase {
    Project2ITT() {
    }

    private InvokeMainTestCase.MainMethodResult invokeMain(String... args) {
        return this.invokeMain(Project2.class, args);
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
        String [] args = {"-fred"};
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid command line argument(s) for options"));
    }

    /**
     * Tests to check if an error is made if there are too many options passed in
     */
    @Test
    void testTooManyOptions(){
        String [] args = {"-README", "-print", "-print", "-textFile"};
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Too many options arguments given, options include -README, Textfile, and print"));
    }

    /**
     * Tests that the file is a blank one and does not find the file aka result is null, and also prints the new call
     */
    @Test
    void testMainResultIsNullAndToStringPrint(){
        String [] args = {"-print", "-textFile", "empty-phonebill.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
                "10:30", "11/12/2011", "11:30"};
        String check_against = "Phone call from Mike to None from 11/11/2011 10:30 to 11/12/2011 11:30";
        InvokeMainTestCase.MainMethodResult result = this.invokeMain(args);
        MatcherAssert.assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString(check_against));

    }
}
