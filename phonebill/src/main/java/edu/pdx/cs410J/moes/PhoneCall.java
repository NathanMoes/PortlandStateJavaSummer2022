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
   * Checks if the date and time is valid
   * returns true if valid and false else
   */
  public boolean vali_date(String to_validate){
    boolean month = false;
    boolean day = false;
    boolean year = false;
    boolean hour = false;
    boolean min = false;
    int check = 0;
    for (int i = 0; i < to_validate.length(); i+=1){
      if (Character.isDigit(to_validate.charAt(i))){
        check += 1;
      } else if ((to_validate.charAt(i) == '/') || (to_validate.charAt(i) == ' ') || (to_validate.charAt(i) == ':')) {
        if ((i == 1 || i == 2) && (check == 1 || check == 2)){
          if (check == 1){
            month = to_validate.charAt(i - 1) != '0';
          }
          else {
            month = true;
          }
          check = 0;
        }
        if ((i == 3 || i == 4 || i == 5) && (month) && (check == 1 || check == 2)){
          if (check == 1){
            day = to_validate.charAt(i - 1) != '0';
          }
          else {
            day = true;
          }
          check = 0;
        }
        if ((i >= 7 && i <= 10) && (month && day) && (check == 4)){
          year = true;
          check = 0;
        }
        if ((i >= 10 && i <= 13) && (month && day && year) && (check == 1 || check == 2)) {
          if (check == 1) {
            hour = to_validate.charAt(i - 1) != '0';
          } else {
            hour = true;
          }
          check = 0;
        }
      }
    }
    System.out.println(to_validate.charAt(to_validate.length()-1));
    if (to_validate.charAt(to_validate.length() - 1) == '0'){
      if (to_validate.charAt(to_validate.length() - 2) != ':'){
        min = true;
      }
    }
    //return (month && day && year);
    return month && day && year && hour && min;
  }


  /**
  default constructor for the class PhoneCall. Sets the values to their default values.
   */

  PhoneCall(){
      this.callEnd = "00/00/0000 0:0"; // default time 0 hour 0 min
      this.callBegin = "00/00/0000 0:0"; // default time 0 hour 0 min
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
    if (this.vali_date(inp_callBegin))
      this.callBegin = inp_callBegin;
    else
      System.err.println("Invalid date begin");
    if (this.vali_date(inp_callEnd))
      this.callEnd = inp_callEnd;
    else
      System.err.println("Invalid date begin");
    this.caller = inp_caller;
    this.callee = inp_callee;
    this.callerNumber = inp_callerNumber;
    this.calleeNumber = inp_calleeNumber;
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
