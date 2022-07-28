package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */
@TestMethodOrder(MethodName.class)
class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");


    @Test
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }

    /**
     * This test is to test that we can send valid command line input to create a new call, and it will not throw error
     */
    @Test
    void testValidInputToAddNewCallFromMain(){
        String [] args = {"-host", "localhost", "-port", "8080", "Nathan", "123-123-1234", "123-123-1234", "10/10/2030",
                "10:30", "AM", "10/10/2030", "2:30", "PM"};
        MainMethodResult result = invokeMain(Project4.class, args);
    }

    /**
     * This is a test to see that we get an error when a port but not a host is provided
     */
    @Test
    void testPortNotHost(){
        String [] args = {"-port", "8080", "Nathan", "123-123-1234", "123-123-1234", "10/10/2030",
                "10:30", "AM", "10/10/2030", "2:30", "PM"};
        MainMethodResult result = invokeMain(Project4.class, args);
    }

    /**
     * Tests that we get an invalid port passed in giving an error
     */
    @Test
    void testInvalidPortError(){
        String args [] = {"-host", "hostname", "-port", "tacobell"};
        MainMethodResult result = invokeMain(Project4.class, args);
    }

    /**
     * This test is to test that we can use valid search query

    @Test
    void testValidSearch(){
        String [] args = {"-host", "localhost", "-port", "8080", "-search" , "Nathan", "10/10/2030",
                "10:30", "AM", "10/10/2030", "2:30", "PM"};
        MainMethodResult result = invokeMain(Project4.class, args);
    }
     */

    /**
     * This test is to test that we can send valid command line input to create a new call, and print it
     */
    @Test
    void testValidInputToAddNewCallFromMainAndPrint(){
        String [] args = {"-host", "localhost", "-port", "8080", "-print", "Nathan", "123-123-1234", "123-123-1234",
                "10/10/2030", "10:30", "AM", "10/10/2030", "2:30", "PM"};
        MainMethodResult result = invokeMain(Project4.class, args);
    }

    /**
     * This test is to test that we can send valid command line input to create a new call, and include the search param
     */
    @Test
    void testValidInputToAddNewCallFromMainAndSearch(){
        String [] args = {"-host", "localhost", "-port", "8080", "-search", "Nathan", "123-123-1234", "123-123-1234",
                "10/10/2030", "10:30", "AM", "10/10/2030", "2:30", "PM"};
        MainMethodResult result = invokeMain(Project4.class, args);
    }

    /**
    @Test
    void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, "-host", HOSTNAME, "-port" , PORT );
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatWordCount(0)));
    }

    @Test
    void test3NoDefinitionsThrowsAppointmentBookRestException() {
        String word = "WORD";
        try {
            invokeMain(Project4.class, HOSTNAME, PORT, word);
            fail("Expected a RestException to be thrown");

        } catch (UncaughtExceptionInMain ex) {
            RestException cause = (RestException) ex.getCause();
            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
        }
    }

    @Test
    void test4AddDefinition() {
        String word = "WORD";
        String definition = "DEFINITION";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, word, definition );
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT, word );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatDictionaryEntry(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatDictionaryEntry(word, definition)));
    }
    */
}