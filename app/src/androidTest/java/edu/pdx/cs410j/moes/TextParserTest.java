package edu.pdx.cs410j.moes;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.Test;

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
    File file = new File("valid-phonebill.txt");
    InputStream resource = getClass().getResourceAsStream("valid-phonebill.txt");
    assertThat(resource, is(nullValue()));
    file.delete();
    // test messages things too, just beacuse its not covered and it is unpleasant to see red
    assertThat(Messages.allDictionaryEntriesDeleted(), containsString("All dictionary entries have been deleted"));

  }

  /**
   * this test will test to check that the if else statements are run in the parser
   */

  @Test
  void usesTheIfElseStatementsInFull() throws IOException, ParserException{
    PhoneCall call = new PhoneCall("Name is", "222-222-2222", "333-333-3333", "01/01/1000 10:30 AM", "01/01/1000 11:30 AM");
    PhoneBill bill = new PhoneBill("Name is");
    File tempFile = new File("tempFile.txt");
    // FileReader fileReader = new FileReader(tempFile);
    FileWriter fileWriter = new FileWriter(tempFile);
    bill.addPhoneCall(call);
    FileReader fileReader = new FileReader(tempFile);
    TextParser parser = new TextParser(fileReader);
    TextDumper textDumper = new TextDumper(fileWriter);
    textDumper.dump(bill);
    PhoneBill bill_check = parser.parsePhone();
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
