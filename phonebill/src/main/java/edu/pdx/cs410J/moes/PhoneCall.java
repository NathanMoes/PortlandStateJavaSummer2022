package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {

  public String caller;
  public String callee;
  public String callerNumber;
  public String calleeNumber;
  public String callBegin;
  public String callEnd;

  PhoneCall(){
      this.callEnd = "0:0:0";
      this.callBegin = "0:0:0";
      this.caller = "None";
      this.callee = "None";
      this.callerNumber = "None";
      this.calleeNumber = "None";
  }

  PhoneCall(String inp_caller, String inp_callee, String inp_callerNumber, String inp_calleeNumber, String inp_callBegin, String inp_callEnd){
    this.caller = inp_caller;
    this.callee = inp_callee;
    this.callerNumber = inp_callerNumber;
    this.calleeNumber = inp_calleeNumber;
    this.callBegin = inp_callBegin;
    this.callEnd = inp_callEnd;
  }

  @Override
  public String getCaller() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return this.caller;
  }

  @Override
  public String getCallee() {
    //return "This method is not implemented yet";
    return this.callee;
  }

  @Override
  public String getBeginTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
