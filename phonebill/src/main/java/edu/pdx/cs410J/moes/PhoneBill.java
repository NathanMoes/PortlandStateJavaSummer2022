package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customer; //customer name
  public ArrayList<PhoneCall> calls;


  public PhoneBill(String customer) {
    this.customer = customer;
    this.calls = new ArrayList<PhoneCall>();
  }

  @Override
  public String getCustomer() {
    return this.customer;
  }

  @Override
  public void addPhoneCall(PhoneCall call) {
    calls.add(call);
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.calls;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
