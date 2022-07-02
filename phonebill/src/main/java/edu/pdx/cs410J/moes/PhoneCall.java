package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {

  public String caller; // Caller name
  public String callee; // Callee name
  public String callerNumber; // Caller Number
  public String calleeNumber; // Callee Number
  public String callBegin; // Call start time/begin time
  public String callEnd; // Call end time/finish time


  /**
  default constructor for the class PhoneCall. Sets the values to their default values.
   */

  PhoneCall(){
      this.callEnd = "0:0"; // default time 0 hour 0 min
      this.callBegin = "0:0"; // default time 0 hour 0 min
      this.caller = "None"; // default caller is none
      this.callee = "None"; // default callee is none
      this.callerNumber = "000-000-0000"; // default number is 000-000-0000
      this.calleeNumber = "000-000-0000"; // default number is 000-000-0000
  }

  /**
   * to read in the data from command line passed in for setting values
   * @param inp_caller caller number from command line
   * @param inp_callee callee number from command line
   * @param inp_callerNumber caller Number from command line
   * @param inp_calleeNumber callee number from command line
   * @param inp_callBegin call being time
   * @param inp_callEnd call end time
   */

  PhoneCall(String inp_caller, String inp_callee, String inp_callerNumber, String inp_calleeNumber, String inp_callBegin, String inp_callEnd){
    this.caller = inp_caller;
    this.callee = inp_callee;
    this.callerNumber = inp_callerNumber;
    this.calleeNumber = inp_calleeNumber;
    this.callBegin = inp_callBegin;
    this.callEnd = inp_callEnd;
  }

  /**
   * Gets the caller's number from class
   * @return returns the callers number as a string
   */
  public String getCallerNumber(){
    return this.callerNumber;
  }

  /**
   * Gets the Callee Number from class
   * @return returns the callee's number as a string
   */
  public String getCalleeNumber(){
    return this.calleeNumber;
  }

  /**
   * gets the caller's name from class
   * @return returns the caller's name as string
   */
  @Override
  public String getCaller() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return this.caller;
  }

  /**
   * gets the callee's name from class
   * @return returns the callee's name as string
   */
  @Override
  public String getCallee() {
    //return "This method is not implemented yet";
    return this.callee;
  }

  /**
   * gets the begin/start time from class
   * @return returns the begin/start time as string
   */
  @Override
  public String getBeginTimeString() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return this.callBegin;
  }

  /**
   * gets the end time from class
   * @return returns the end time as string
   */
  @Override
  public String getEndTimeString() {
    //throw new UnsupportedOperationException("This method is not implemented yet");
    return this.callEnd;
  }
}
