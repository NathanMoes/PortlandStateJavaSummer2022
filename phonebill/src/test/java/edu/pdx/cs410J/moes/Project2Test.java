package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can capture data
 * written to {@link System#out} and the like.
 */
class Project2Test {

  /**
   * Tests if the readme can be read as a resource
   * @throws IOException if there is a failure to open readme text as a resource
   */
  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project2.class.getResourceAsStream("README.txt")
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
    String [] args = {"-print" , "Nathan Moes" , "971-655-7829" , "971-521-1458" , "09/26/2000" , "10:30" ,
            "09/26/2000", "11:30"};
    Project2.main(args);
  }

  /**
   * Test that valid input with readme opens readme
   */
  @Test
  void testValidInputReadme(){
    String [] args = {"-README" , "Nathan Moes" , "971-655-7829" , "971-521-1458" , "09/26/2000" , "10:30" ,
            "09/26/2000", "11:30"};
    Project2.main(args);
  }

  /**
   * tests that the program will output error if too many aruments
   */
  @Test
  void testTooManyArgs(){
    String [] args = {"-print" , "Nathan Moes" , "971-655-7829" , "971-521-1458" , "09/26/2000" , "10:30" ,
            "09/26/2000", "11:30", "And another one bits the dust"};
    Project2.main(args);
  }


  /**
   * tests that the program will output error if not enough aruments
   */
  @Test
  void testNotEnoughArgs(){
    String [] args = {"-print" , "Nathan Moes" , "971-655-7829"};
    Project2.main(args);
  }

  /**
   * Tests that the program will run when provided correc targs for adding to a file
   */
  @Test
  void testCorrectTextFile(){
    String [] args = {"-textFile tacos.txt", "Nathan Moes", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30", "11/12/2011", "11:30"};
    Project2.main(args);
  }

  /**
   * Tests that less than 1 arg adds to the error output Missing command line arguments
   */
  @Test
  void testMainMissingArgs(){
    PrintStream stream = new PrintStream(System.err);
    String [] args = {};
    Project2.main(args);
  }

  /**
   * Tests that the file is a blank one and does not find the file aka result is null
   */
  @Test
  void testMainResultIsNull(){
    // -textFile tacos.txt "Nathan Moes" 342-234-2341 123-421-4362 11/11/2011 10:30 11/12/2011 11:30
    String [] args = {"-textFile", "Mike.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30", "11/12/2011", "11:30"};
    Project2.main(args);
  }

  /**
   * Tests that the file is a blank one and does not find the file aka result is null, and also prints the new call
   */
  @Test
  void testMainResultIsNullAndToStringPrint(){
    String [] args = {"-print", "-textFile", "empty-phonebill.txt", "Mike", "342-234-2341", "123-421-4362", "11/11/2011",
            "10:30", "11/12/2011", "11:30"};
    Project2.main(args);
  }

  /**
   * Tests that the find method it retuns null when null is passed as start
   */
  @Test
  void testNullReturnedForNullStartInFind(@TempDir File temp) {
    assertThat(Project2.find("", temp), is(nullValue()));
  }

  /**
   * Tests that the isvalidnumber retunrs true
   */
  @Test
  void testisvalidnumber(){
    assertThat(Project2.isValidPhoneNumber(""), is(true));
  }

  /**
   * Tests that if there is an unsupported option it is reported
   */
  @Test
  void testInvalidOption(){
    String [] args = {"-fred"};
    Project2.main(args);
  }

  /**
   * Tests to check if an error is made if there are too many options passed in
   */
  @Test
  void testTooManyOptions(){
    String [] args = {"-README", "-print", "-print", "-textFile"};
    Project2.main(args);
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

}
