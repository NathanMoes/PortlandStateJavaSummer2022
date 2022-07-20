package edu.pdx.cs410J.moes;

import org.junit.jupiter.api.Test;

import java.awt.color.ICC_ColorSpace;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out your program.
 */
public class PhoneCallTest {

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   * validates the end time default value of 0:0
   */
  @Test
  void forProject1ItIsOkayIfGetEndTimeIsZero() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getEndTimeString(), is(notNullValue()));
  }

  /**
   * This test tests to validate if the default time of 0:0 is init correctly
   */
  @Test
  void forProject1ItIsOkayIfGetBeginTimeIsZero() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getBeginTimeString(), is(notNullValue()));
  }

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   * Check if phone call has correct default callee name
   */
  @Test
  void initiallyAllPhoneCallsHaveTheSameCallee() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallee(), containsString("None"));
  }

  /**
   * Tests to see that the caller name default value is correct aka is none
   */
  @Test
  void initiallyAllPhoneCallsHaveTheSameCaller() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getCaller(), containsString("None"));
  }

  /**
   * Tests to validate the callee phone number of 000-000-0000 as a default value
   */
  @Test
  void initiallyPhoneCalleeNumberIsZero(){
    PhoneCall call = new PhoneCall();
    assertThat(call.getCalleeNumber(), containsString("000-000-0000"));
  }

  /**
   * Tests if the caller number default is correct/valid
   */
  @Test
  void initiallyPhoneCallerNumberIsZero(){
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallerNumber(), containsString("000-000-0000"));
  }

  /**
   * Tests if its okay to have the begin time string as null
   */
  @Test
  void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getBeginTime(), is(nullValue()));
  }

  /**
   * Tests if its okay to have the end time is valid
   */
  @Test
  void forProject1ItIsOkayIfGetEndTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getEndTime(), is(nullValue()));
  }

  /**
   * Tests that the phone number cannot contain chars
   */
  @Test
  void testForInvalidNumberIsFalseLetters(){
    PhoneCall call = new PhoneCall();
    //assertThat((call.validate_number("abc-def-abdd")), false);
    assertThat(call.validate_number("abc-def-hijk"), is(false));
  }

  /**
   * Tests that too many numbers for a phone number is invalid
   */
  @Test
  void testForInvalidNumberIsFalseNumbers(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validate_number("9999-2232-2123"), is(false));
  }

  /**
   * tests to check if a valid phone number is valid
   */
  @Test
  void testForNumberIsTrue(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validate_number("123-123-1234"), is(true));
  }

  /**
   * tests if a valid date is valid
   */
  @Test
  void testForValidDateCorrectDate(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("07/3/2022 10:30"), is(true));
  }

  /**
   * tests if a invalid date is invalid
   */
  @Test
  void testForValidDateIncorrectDate(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("0/0/2022 10:30"), is(false));
  }

  /**
   * tests that an invalid time is invalid
   */
  @Test
  void testForValidDateIncorrectTime(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("07/21/2021 0:10"), is(false));
  }

  /**
   * tests if a valid time is valid
   */
  @Test
  void testForValidDateCorrectTime(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("07/21/2100 10:30"), is(true));
  }

  /**
   * Tests if a valid name is valid

  @Test
  void testForValidNameCorrect(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validate_name("Taco man"), is(true));
  }
  */
  /**
   * tests if an invalid name is invalid, aka contains numbers

  @Test
  void testForValidNameIncorrect(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validate_name("D4ve 3antos"), is(false));
  }
  */

  /**
   * Tests if the default number is correct
   */
  @Test
  void testForGetCalleeNumber(){
    PhoneCall call = new PhoneCall();
    assertThat(call.getCalleeNumber(), containsString("000-000-0000"));
  }

  /**
   * tests if callee number is set and returned correctly
   */
  @Test
  void testForGetCalleeNumberNonDefault(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCalleeNumber(), containsString("333-333-3333"));
  }

  /**
   * Tests that we can get caller number correctly
   */
  @Test
  void testForGetCallerNumber(){
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallerNumber(), containsString("000-000-0000"));
  }

  /**
   * Tests to make sure that caller number is set and returned correctly
   */
  @Test
  void testForGetCallerNumberNonDefault(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCallerNumber(), containsString("222-222-2222"));
  }

  /**
   * Tests to make sure that defualt constuctor works with default values
   */
  @Test
  void testDefaultValues(){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallee(), containsString("None"));
    assertThat(call.getCaller(), containsString("None"));
    assertThat(call.getCallerNumber(), containsString("000-000-0000"));
    assertThat(call.getCalleeNumber(), containsString("000-000-0000"));
    assertThat(call.getCallBegin(), is(dateFormat.format(new Date())));
    assertThat(call.getCallEnd(), is(dateFormat.format(new Date())));
    // assertThat(call.getBeginTimeString(), containsString("00/00/0000 0:0"));
    // assertThat(call.getEndTimeString(), containsString("00/00/0000 0:0"));
  }

  /**
   * Tests that if there is too few dashed we get too few dashes error
   */
  @Test
  void testTooFewDashes(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "2221222-2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCallerNumber(), is(nullValue()));
  }

  /**
   * Tests that if there is too few dashed we get too few dashes error but for the callee
   */
  @Test
  void testTooFewDashesCallee(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "3331333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCalleeNumber(), is(nullValue()));
  }

  /**
   * Tests that if there is too few numbers we get an error and null
   */
  @Test
  void testTooFewNumbers(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "aaa-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCalleeNumber(), is(nullValue()));
  }

  /**
   * Tests that if there is too few numbers we get an error and null, but for the caller
   */
  @Test
  void testTooFewNumbersCaller(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "bbb-222-2222", "123-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCallerNumber(), is(nullValue()));
  }

  /**
   * Tests for malfromed minute in args
   */
  @Test
  void TestInvalidMinute(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:3a", "01/01/1000 11:30");
    assertThat(call.getBeginTimeString(), is(nullValue()));
  }

  /**
   * Tests for malfromed minute in args
   */
  @Test
  void TestInvalidMinuteEnd(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:3a");
    assertThat(call.getEndTimeString(), is(nullValue()));
  }

  /**
   * tests if a invalid date is invalid 2
   */
  @Test
  void testForValidDateIncorrectDate2(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("100/0/2022 10:30"), is(false));
  }

  /**
   * tests if a invalid date is invalid 3
   */
  @Test
  void testForValidDateIncorrectDate3(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("10/222/2020 10:30"), is(false));
  }

  /**
   * tests if we get invalid name given for caller

  @Test
  void testInvalidCallerName(){
    PhoneCall call = new PhoneCall("21312312", "Also this", "222-222-2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCaller(), is(nullValue()));
  }
  */

  /**
   * tests if we get invalid name given for callee
   */
  @Test
  void testInvalidCalleeName(){
    PhoneCall call = new PhoneCall("bob robert", "123213", "222-222-2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCallee(), containsString("123213"));
  }

  /**
   * Tests that if there is too many dashed we get too few dashes error
   */
  @Test
  void testTooManyDashes(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222----2222", "333-333-3333", "01/01/1000 10:30", "01/01/1000 11:30");
    assertThat(call.getCallerNumber(), is(nullValue()));
  }


  /**
   * Tests that a valid date is returned as true/valid
   */
  @Test
  void testJavaDateValidIsValid(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-211-2222", "333-333-3333", "01/01/1000 10:30 am", "01/01/1000 11:30 am");
    assertThat(call.validateJavaDate("01/02/2022 9:16 pm"), is(true));
    assertThat(call.getCallBegin(), containsString("01/01/1000 10:30 AM"));
  }

  /**
   * Tests that an invalid date is returned as false/invalid
   */
  @Test
  void testJavaDateInvalidIsInvalid(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validateJavaDate("01/02/2022 9.23 fm"), is(false));
  }

  /**
   * This test is to check that the system gets a valid date from the get end time string function
   */
  @Test
  void testGetBeginTimeNewDate(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-211-2222", "333-333-3333", "01/01/1000 10:30 am", "01/01/1000 11:30 am");
    assertThat(call.getEndTimeString(), containsString("1/1/00"));
  }

  /**
   * This will test the compare to method, to make sure that it returns a correct value for the comparison of two phone calls
   */
  @Test
  void testComparePhoneCall(){
    PhoneCall call = new PhoneCall("Name is", "Also this", "222-211-2222", "333-333-3333", "01/01/1000 10:30 am", "01/01/1000 11:30 am");
    PhoneCall call1 = new PhoneCall("Name is", "Also this", "222-211-2222", "333-333-3333", "01/01/1000 10:10 am", "01/01/1000 11:30 am");
    assertThat(call.compareTo(call1), is(-1200000));
  }

}
