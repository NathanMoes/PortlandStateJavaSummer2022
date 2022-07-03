package edu.pdx.cs410J.moes;

import org.junit.jupiter.api.Test;

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
   */

  @Test
  void forProject1ItIsOkayIfGetEndTimeIsZero() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getEndTimeString(), containsString("0:0"));
  }

  @Test
  void forProject1ItIsOkayIfGetBeginTimeIsZero() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getBeginTimeString(), containsString("0:0"));
  }

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  @Test
  void initiallyAllPhoneCallsHaveTheSameCallee() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallee(), containsString("None"));
  }

  @Test
  void initiallyAllPhoneCallsHaveTheSameCaller() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getCaller(), containsString("None"));
  }

  @Test
  void initiallyPhoneCalleeNumberIsZero(){
    PhoneCall call = new PhoneCall();
    assertThat(call.getCalleeNumber(), containsString("000-000-0000"));
  }

  @Test
  void initiallyPhoneCallerNumberIsZero(){
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallerNumber(), containsString("000-000-0000"));
  }

  @Test
  void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getBeginTime(), is(nullValue()));
  }

  @Test
  void forProject1ItIsOkayIfGetEndTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getEndTime(), is(nullValue()));
  }

  @Test
  void testForInvalidNumberIsFalseLetters(){
    PhoneCall call = new PhoneCall();
    //assertThat((call.validate_number("abc-def-abdd")), false);
    assertThat(call.validate_number("abc-def-hijk"), is(false));
  }

  @Test
  void testForInvalidNumberIsFalseNumbers(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validate_number("9999-2232-2123"), is(false));
  }

  @Test
  void testForNumberIsTrue(){
    PhoneCall call = new PhoneCall();
    assertThat(call.validate_number("123-123-1234"), is(true));
  }

  @Test
  void testForValidDateCorrectDate(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("07/3/2022 10:30"), is(true));
  }

  @Test
  void testForValidDateIncorrectDate(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("0/0/2022 10:30"), is(false));
  }

  @Test
  void testForValidDateIncorrectTime(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("07/21/2021 0:10"), is(false));
  }

  @Test
  void testForValidDateCorrectTime(){
    PhoneCall call = new PhoneCall();
    assertThat(call.vali_date("07/21/2100 10:30"), is(true));
  }


}
