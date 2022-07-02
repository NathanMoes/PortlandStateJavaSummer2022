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

}
