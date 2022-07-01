package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customer;
  public ArrayList<PhoneCall> calls;

  public PhoneBill(String customer) {
    this.customer = customer;
  }

  @Override
  public String getCustomer() {
    return this.customer;
  }

  @Override
  public void addPhoneCall(PhoneCall call) {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
