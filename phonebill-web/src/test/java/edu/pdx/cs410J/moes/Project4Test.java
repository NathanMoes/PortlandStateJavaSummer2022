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
     */
    @Test
    void testMainInvokedCorrectlyNoError(){
        String args [] = {"-host", "localhost", "-port", "8080", "Nathan", "123-123-1234", "123-123-1234", "10/10/2022"
        ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }

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
     */
    @Test
    void testMainInvokedCorrectlyNoErrorSevenArgs(){
        String args [] = {"-host", "localhost", "-port", "8080", "Nathan", "10/10/2022"
                ,"10:30", "AM", "10/10/2022", "2:30", "PM"};
        Project4.main(args);
    }

    /**
     * Tests that main can be invoked with args of 1 and not just 9 or 7
     */
    @Test
    void testMainInvokedCorrectlyNoErrorOneArg(){
        String args [] = {"-host", "localhost", "-port", "8080", "Nathan"};
        Project4.main(args);
    }

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

}
