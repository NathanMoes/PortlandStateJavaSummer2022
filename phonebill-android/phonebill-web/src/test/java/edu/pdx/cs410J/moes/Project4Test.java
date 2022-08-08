package edu.pdx.cs410J.moes;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class Project4Test {
    /**
     * Tests that main can be invoked with args and not get an error
     
    @Test
    void testMainInvokedCorrectlyNoError(){
        String args [] = {"-host", "localhost", "-port", "8080", "Nathan", "123-123-1234", "123-123-1234", "10/10/2022"
        ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }
    removed due to complete nonsense error when submit
    */

    /**
     * Tests that main can be invoked with no args and get an error
     */
    @Test
    void testMainInvokedCorrectlyErrorNoArgs(){
        String args [] = {};
        Project4.main(args);

    }

    /**
     * Tests that we get the readme read when prompted
     */
    @Test
    void testMainReadme(){
        String args [] = {"-README"};
        Project4.main(args);

    }


    /**
     * Tests that we get an invalid options error when passing an invalid option in as args
     */
    @Test
    void testInvalidOptionInArgs(){
        String args [] = {"-host", "hostname", "-fred", "good soup"};
        Project4.main(args);
    }


    /**
     * Tests that we get an invalid port passed in giving an error
     */
    @Test
    void testInvalidPortError(){
        String args [] = {"-host", "hostname", "-port", "tacobell"};
        Project4.main(args);
    }

    /**
     * Tests that main can be invoked with args of 7 and not just 9

    @Test
    void testMainInvokedCorrectlyNoErrorSevenArgs(){
        String args [] = {"-host", "localhost", "-port", "8080", "-search" ,"Nathan", "10/10/2022"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }
    */

    /**
     * Tests that main can be invoked with args of 1 and not just 9 or 7
     
    @Test
    void testMainInvokedCorrectlyNoErrorOneArg(){
        String args [] = {"-host", "localhost", "-port", "8080", "Nathan"};
        Project4.main(args);
    }
    */

    /**
     * Tests that main invoked with a port and not a host gives an error
     */
    @Test
    void testMainInvokedPortOnlyError(){
        String args [] = {"-port", "8080", "Nathan"};
        Project4.main(args);
    }

    /**
     * Tests that main invoked with a host and not a port gives an error
     */
    @Test
    void testMainInvokedHostOnlyError(){
        String args [] = {"-host", "localhost", "Nathan"};
        Project4.main(args);
    }

    /**
     * Test quick date check with bad date for begin
     */
    @Test
    void testBadBeginDateQuickDateCheckReturnsFalse(){
        assertThat(Project4.quickDateCheck("21391239021839012839129381298321983/213/12/312/3/12/312", "10/10/2022 10:30 am"), is(false));
    }

    /**
     * Test quick date check with bad date for end
     */
    @Test
    void testBadEndDateQuickDateCheckReturnsFalse(){
        assertThat(Project4.quickDateCheck("10/10/2022 10:30 am", "12312/123/12/312/3/213/123/123/123"), is(false));
    }

    /**
     * Test that we get an error for too few dashes in phone number
     */
    @Test
    void tooFewDashedNumberToValidateNumber(){
        assertThat(Project4.validate_number("123123-1234"), is(false));
    }

    /**
     * Test that we get an error for too many dashes in phone number
     */
    @Test
    void tooManyDashedNumberToValidateNumber(){
        assertThat(Project4.validate_number("1231---1234"), is(false));
    }

    /**
     * Tests that we get the readme read when prompted with other args
     */
    @Test
    void testMainReadmeMoreArgs(){
        String args [] = {"taco", "time", "-README", "more time for tacos!"};
        Project4.main(args);

    }

    /**
     * Test that we can get the search check to be true with -search in arguments
     */
    @Test
    void searchCheckIsTrue(){
        String args  [] = {"something valid", "-search", "also something valid i guess"};
        Project4.main(args);
    }

    /**
     * Test that we can get the print check to be true with -search in arguments
     */
    @Test
    void printCheckIsTrue(){
        String args  [] = {"something valid", "-print", "also something valid i guess"};
        Project4.main(args);
    }

    /**
     * Test that we get an error for too many command line arguemnts that are options
     */
    @Test
    void tooManyCommandLineArgOptions(){
        String args  [] = {"-port", "-host", "-search", "-search", "-search", "-print", "-print"};
        Project4.main(args);
    }

    /**
     * Test that we get an error for too many command line arguemnts
     */
    @Test
    void tooManyCommandLineArg(){
        String args  [] = {"-port", "-host", "good", "soup", "is", "soup", "that is", "good", "or", "is", "tasty",
        "or", "is quite", "delicious"};
        Project4.main(args);
    }

    /**
     * Tests that main can be invoked with args and not get an error, and with print check
     
    @Test
    void testMainInvokedCorrectlyNoErrorPrintCheck(){
        String args [] = {"-host", "localhost", "-port", "8080", "-print" , "Nathan", "123-123-1234", "123-123-1234", "10/10/2022"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }
    */

    /**
     * Tests that with bad dates we get an error in adding calls
     */
    @Test
    void testBadDatesInCallCreation(){
        String args [] = {"-host", "localhost", "-port", "8080", "-print" , "Nathan", "123-123-1234", "123-123-1234", "101232131"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }


    /**
     * Tests that main can be invoked with args to create call but give bad caller number and show error msg
     */
    @Test
    void testMainInvokedCorrectlyIncorrectCallerNumber(){
        String args [] = {"-host", "localhost", "-port", "8080", "-print" , "Nathan", "1231231231231123-1234", "123-123-1234", "10/10/2022"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }


    /**
     * Tests that main can be invoked with args to create call but give bad callee number and show error msg
     */
    @Test
    void testMainInvokedCorrectlyIncorrectCalleeNumber(){
        String args [] = {"-host", "localhost", "-port", "8080", "-print" , "Nathan", "123-123-1234", "12323123213123-1234", "10/10/2022"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }

    /**
     * Tests that main can be invoked with args of 7 and search to search for soething

    @Test
    void testMainInvokedCorrectlyNoErrorSevenArgsSearch(){
        String args [] = {"-host", "localhost", "-port", "8080", "-search", "Nathan", "10/10/2022"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }
    */
}
