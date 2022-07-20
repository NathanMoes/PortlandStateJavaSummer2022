package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can capture data
 * written to {@link System#out} and the like.
 */
class Project3Test {

  /**
   * Tests if the readme can be read as a resource
   * @throws IOException if there is a failure to open readme text as a resource
   */
  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project3.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }

  /**
   * Tests that valid input with print statement works as expected aka no errors and exits successfuly
   */
  @Test
  void testValidInputPrint(){
    String [] args = {"-print" , "Nathan Moes" , "971-655-7829" , "971-521-1458" , "09/26/2000" , "10:30" , "AM" ,
            "09/26/2000", "11:30", "AM"};
    Project3.main(args);
  }

  /**
   * Test that valid input with readme opens readme
   */
  @Test
  void testValidInputReadme(){
    String [] args = {"-README" , "Nathan Moes" , "971-655-7829" , "971-521-1458" , "09/26/2000" , "10:30" ,
            "09/26/2000", "11:30"};
    Project3.main(args);
  }

  /**
   * tests that the program will output error if too many aruments
   */
  @Test
  void testTooManyArgs(){
    String [] args = {"-print" , "Nathan Moes" , "971-655-7829" , "971-521-1458" , "09/26/2000" , "10:30" , "AM",
            "09/26/2000", "11:30", "AM", "And another one bits the dust"};
    Project3.main(args);
  }


  /**
   * tests that the program will output error if not enough aruments
   */
  @Test
  void testNotEnoughArgs(){
    String [] args = {"-print" , "Nathan Moes" , "971-655-7829"};
    Project3.main(args);
  }

  /**
   * Tests that the program will run when provided correc targs for adding to a file
   */
  @Test
  void testCorrectTextFile(){
    String [] args = {"-textFile tacos.txt", "Nathan Moes", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30" , "AM", "11/12/2011", "11:30" , "AM"};
    Project3.main(args);
  }

  /**
   * Tests that less than 1 arg adds to the error output Missing command line arguments
   */
  @Test
  void testMainMissingArgs(){
    PrintStream stream = new PrintStream(System.err);
    String [] args = {};
    Project3.main(args);
  }

  /**
   * Tests that the file is a blank one and does not find the file aka result is null
   */
  @Test
  void testMainResultIsNull(){
    // -textFile tacos.txt "Nathan Moes" 342-234-2341 123-421-4362 11/11/2011 10:30 11/12/2011 11:30
    String [] args = {"-textFile", "Mike.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30", "AM", "11/12/2011", "11:30",  "AM"};
    Project3.main(args);
  }

  /**
   * Tests that the file is a blank one and does not find the file aka result is null, and also prints the new call
   */
  @Test
  void testMainResultIsNullAndToStringPrint(){
    String [] args = {"-print", "-textFile", "empty-phonebill.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30", "AM", "11/12/2011", "11:30",  "AM"};
    Project3.main(args);
  }

  /**
   * Tests that the find method it retuns null when null is passed as start
   */
  @Test
  void testNullReturnedForNullStartInFind(@TempDir File temp) {
    assertThat(Project3.find("", temp), is(nullValue()));
  }

  /**
   * Tests that the isvalidnumber retunrs true
   */
  @Test
  void testisvalidnumber(){
    assertThat(Project3.isValidPhoneNumber(""), is(true));
  }

  /**
   * Tests that if there is an unsupported option it is reported
   */
  @Test
  void testInvalidOption(){
    String [] args = {"-fred"};
    Project3.main(args);
  }

  /**
   * Tests to check if an error is made if there are too many options passed in
   */
  @Test
  void testTooManyOptions(){
    String [] args = {"-README", "-print", "-print", "-textFile"};
    Project3.main(args);
  }

  /**
   * Tests for malformed file
   */
  @Test
  void testMalformedFile() throws IOException{
    File file = new File("badfile.txt");
    FileWriter writer = new FileWriter(file);
    writer.write("bad file");
    writer.write("bad file");
    writer.write("bad file");
    writer.write("bad file");
    writer.write("bad file");
    writer.write("bad file");
    writer.write("bad file");
    //TextDumper dumper = new TextDumper(writer);
    FileReader reader = new FileReader(file);
    TextParser parser = new TextParser(reader);
  }

  /**
   * Tests for a null returned from th file function
   */
  @Test
  void testNullFileReturn(){
    File tempDir = new File("tempasdsa");
    tempDir.delete();
    assertThat(Project3.find(null, tempDir), is(nullValue()));
    //Project2.find(null, temp);
  }

  /**
   * Tests that it will print with text args too
   */
  @Test
  void testPrintWithTextArgs(){
    String [] args = {"-textFile", "valid-phonebill.txt", "-print" , "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30", "AM", "11/12/2011", "11:30",  "AM"};
    Project3.main(args);
  }

  /**
   * Test that the call of end dates and begin dates that are incorrect returns false
   */
  @Test
  void testIncorrectCallTimes(){
    long begin = 10000;
    long end = 1000;
    assertThat(Project3.checkCallTimeNotZero(new Date(begin), new Date(end)), is(false));
  }

  /**
   * Testing to see that it can call the function to print to file
   */
  @Test
  void testWeGetPrettyToPrintToFile(){
    String [] args = {"-textFile", "moes/moes.txt", "-pretty", "moesout.txt", "Thomas", "111-234-2341",
            "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
    Project3.main(args);
  }

  /**
   * Tests to see that it can call to print to standard out
   */
  @Test
  void testWeGetPrettyOut(){
    String [] args = {"-textFile", "moes/moes.txt", "-pretty", "-", "Thomas", "111-234-2341",
            "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
    String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
    Project3.main(args);
  }

  /**
   * Tests to see that it can call to print file that should not exist before hand
   */
  @Test
  void testWeGetPrettyOutFileNotExist(){
    File file = new File("thisFileDoesNotExistNorShouldIt.txt");
    String [] args = {"-textFile", "moes/moes.txt", "-pretty", "thisFileDoesNotExistNorShouldIt.txt", "Thomas", "111-234-2341",
            "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
    String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
    Project3.main(args);
    file.delete();
  }

  /**
   * Tests to see that it can call to print file while invalid date
   */
  @Test
  void testWeGetPrettyOutFileNotExistInvalidDate(){
    File file = new File("thisFileDoesNotExistNorShouldIt.txt");
    String [] args = {"-pretty", "thisFileDoesNotExistNorShouldIt.txt", "Thomas", "111-234-2341",
            "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:10", "pm"};
    String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
    Project3.main(args);
    file.delete();
  }


  /**
   * Tests to see that it can call to print file while invalid date, as above but it has illformed date
   */
  @Test
  void testWeGetPrettyOutFileNotExistIllDate(){
    File file = new File("thisFileDoesNotExistNorShouldIt.txt");
    String [] args = {"-pretty", "thisFileDoesNotExistNorShouldIt.txt", "Thomas", "111-234-2341",
            "123-421-4362", "09/285411/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
    String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
    Project3.main(args);
    file.delete();
  }

  /**
   * Tests to see if print with invaid dates does bad thing
   */
  @Test
  void testBadThingPrintBadDate(){
    File file = new File("thisFileDoesNotExistNorShouldIt.txt");
    String [] args = {"-print", "Thomas", "111-234-2341",
            "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:10", "pm"};
    String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
    Project3.main(args);
    file.delete();
  }

  /**
   * Tests to see if print with fine dates prints
   */
  @Test
  void testGoodPrint(){
    File file = new File("thisFileDoesNotExistNorShouldIt.txt");
    String [] args = {"-print", "Thomas", "111-234-2341",
            "123-421-4362", "09/28/2000", "9:16", "pm", "09/28/2000", "9:20", "pm"};
    String check_against = "Call from Thomas to Not given, at 09/28/2000 09:16 PM to 09/28/2000 09:20 PM. Originating from 111-234-2341 and contacting 123-421-4362, and lasting 4 minutes";
    Project3.main(args);
    file.delete();
  }


}
