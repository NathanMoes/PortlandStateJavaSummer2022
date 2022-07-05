package edu.pdx.cs410J.moes;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static edu.pdx.cs410J.moes.Project1.main;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can capture data
 * written to {@link System#out} and the like.
 */
class Project1Test {
  /**
   * Tests if the readme can be read as a resource
   * @throws IOException if there is a failure to open readme text as a resource
   */
  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project1.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }

  //@Test
  //void testValidInput(){
    //InputStream readme = Project1.class.getResourceAsStream("README.txt");
    //String [] args = {"-print" , "Nathan Moes" , "971-205-0106" , "971-470-9758" , "09/26/2000" , "10:30" ,
    //"09/26/2000", "11:30"};
    //Project1 test = new Project1();
    //assert(main(args), );
    //assertThat();
    // -print "Nathan Moes" 971-205-0106 971-470-9758 09/26/2000 10:30 09/26/2000 11:30
  //}


}
