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
   * this test will test to check that the if else statements are run in the parser
   */

  @Test
  void usesTheIfElseStatementsInFull() throws IOException, ParserException{
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:30 AM", "01/01/1000 11:30 AM");
    PhoneBill bill = new PhoneBill("Name is");
    File tempFile = new File("tempFile.txt");
    // FileReader fileReader = new FileReader(tempFile);
    FileWriter fileWriter = new FileWriter(tempFile);
    bill.addPhoneCall(call);
    FileReader fileReader = new FileReader(tempFile);
    TextParser parser = new TextParser(fileReader);
    TextDumper textDumper = new TextDumper(fileWriter);
    textDumper.dump(bill);
    PhoneBill bill_check = parser.parse();
    assertThat(bill_check.getCustomer(), containsString("Name is"));
  }


  /**
   * this test tests that the exception is thrown for ParserException


  @Test
  void badWriterThrows() throws IOException{
    File temp = new File("tempfile.txt");
    FileReader reader = new FileReader(temp);
    TextParser parser = new TextParser(reader);
    temp.delete();
    try{
      parser.parse();
    }
    catch (ParserException exception){
      assertThat(exception.getClass(), is(ParserException.class));
    }
    // assertThrows(ParserException.class, parser.parse());
  }
  */


}
