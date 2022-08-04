package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests the text dumper class
 */
public class TextDumperTest {
  /**
   * tests if the class can correclty read in the phone bill thing
   */
  @Test
  void appointmentBookOwnerIsDumpedInTextFormat() {
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(bill);

    String text = sw.toString();
    assertThat(text, containsString(customer));
  }

  /**
   * Tests that the text can be writen by a text dumper
   * @param tempDir creates a temporary directory for testing
   * @throws IOException if fails and IO operation on open file
   * @throws ParserException If the class can not parse the file
   */
  @Test
  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);

    File textFile = new File(tempDir, "apptbook.txt");
    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(bill);

    TextParser parser = new TextParser(new FileReader(textFile));
    PhoneBill read = parser.parsePhone();
    assertThat(read.getCustomer(), equalTo(customer));
  }

  /**
   * Tests that the text dumper can actualy dump something into the file that can be read and entered as a call
   * Such that there is a list of calls
   */

  @Test
  void addsInSomeCalls(@TempDir File tempDir) throws IOException, ParserException {
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:30 AM", "01/01/1000 11:30 AM");
    PhoneBill bill = new PhoneBill("Name is");
    bill.addPhoneCall(call);
    File textFile = new File(tempDir, "apptbook.txt");
    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(bill);
    assertThat(bill.getPhoneCalls(), is(notNullValue()));
  }

}
