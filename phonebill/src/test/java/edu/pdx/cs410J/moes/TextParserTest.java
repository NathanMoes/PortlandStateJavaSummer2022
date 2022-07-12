package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextParserTest {


  /**
   * Tests is to check if a valid file can be parsed by the class
   * @throws ParserException is an exception thrown when there is a problem parsingthe file
   */
  @Test
  void validTextFileCanBeParsed() throws ParserException {
    InputStream resource = getClass().getResourceAsStream("valid-phonebill.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    PhoneBill bill = parser.parse();
    assertThat(bill.getCustomer(), equalTo("Test Phone Bill"));
  }

  /**
   * Test is to check that if a file is invalid then it does not get parsed
   */
  @Test
  void invalidTextFileThrowsParserException() {
    InputStream resource = getClass().getResourceAsStream("empty-phonebill.txt");
    assertThat(resource, notNullValue());

    TextParser parser = new TextParser(new InputStreamReader(resource));
    assertThrows(ParserException.class, parser::parse);
  }

  /**
  @Test
  void noTextInFileMakesReturnEarly() throws IOException {
    File tempDi = new File("test.txt");
    FileReader fileReader = null;
    PhoneBill catch_it = null;
    try{
      fileReader = new FileReader(tempDi);
    }
    catch (IOException e){
      System.err.println(e.getMessage());
    }
    TextParser parser = new TextParser(fileReader);
    try {
      catch_it = parser.parse();
    }
    catch (ParserException e){
      System.err.println(e.getMessage());
    }
    assertThat(catch_it, is(nullValue()));
  }
  */
}
