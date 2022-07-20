package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.ParserException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall>{

  public String caller; // Caller name
  public String callee; // Callee name
  public String callerNumber; // Caller Number
  public String calleeNumber; // Callee Number
  // public String callBegin; // Call start time/begin time
  // public String callEnd; // Call end time/finish time
  public Date callBeginTime; // Call begin time as java.util.date
  public Date callEndTime; // Call End time as java.util.date


  /**
   * Checks to see if the java.util.date is a valid date
   */
  public boolean validateJavaDate(String to_validate){
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
    try{
      dateFormat.parse(to_validate);
    }
    catch (ParseException e){
      System.err.println(e.getMessage());
      return false;
    }
    return true;
  }


  /**
   * Checks if the name is a valid name aka does not have numbers in it
   */
  public boolean validate_name(String to_validate){
    boolean is_valid = true;
    for (int i = 0; i < to_validate.length(); i+=1){
      if (!Character.isLetter(to_validate.charAt(i)) && !Character.isWhitespace(to_validate.charAt(i)))
        is_valid = false;
    }
    return true;
  }

  /**
   * Check if the number is a valid phone number
   */
  public boolean validate_number(String to_validate){
    int dashes = 0; // should be 2
    int numbers = 0; // should be 10, 3-3-4
    for (int i = 0; i < to_validate.length(); i+=1){
      if (to_validate.charAt(i) == '-')
        dashes += 1;
      else if (Character.isDigit(to_validate.charAt(i))) {
        numbers += 1;
      }
    }
    if (dashes < 2){
      System.err.println("too few dashes in phone number");
    } else if (dashes > 2) {
      System.err.println("too many dashes");
    }
    if (numbers != 10){
      System.err.println("contains too many non number characters");
    }
    return numbers == 10 && dashes == 2;
  }
  
  

  /**
   * Checks if the date and time is valid
   * returns true if valid and false else
   depricated
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
    if (!month){
      System.err.println("Invalid formed month");
    }
    if (!day){
      System.err.println("Invalid formed day");
    }
    if (!year){
      System.err.println("Invalid formed year");
    }
    if (!hour){
      System.err.println("Invalid formed hour");
    }
    //System.out.println(to_validate.charAt(to_validate.length()-1));
    if (to_validate.charAt(to_validate.length() - 1) == '0'){
      if (to_validate.charAt(to_validate.length() - 2) != ':'){
        min = true;
      }
    }
    if (!min){
      System.err.println("Invalid formed minute");
    }
    return month && day && year && hour && min;
  }

  /**
  default constructor for the class PhoneCall. Sets the values to their default values.
   */

  PhoneCall(){
      // this.callEnd = "00/00/0000 0:0"; // default time 0 hour 0 min
      // this.callBegin = "00/00/0000 0:0"; // default time 0 hour 0 min
      this.callBeginTime = new Date(); // default date for the call begin time
      this.callEndTime = new Date(); // default date for the call end time
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
   *                    DateFormat.getDateInstance(DateFormat.SHORT).format(this.callEndTime)
   */

  PhoneCall(String inp_caller, String inp_callee, String inp_callerNumber, String inp_calleeNumber, String inp_callBegin, String inp_callEnd){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    if (this.validateJavaDate(inp_callBegin))
      this.callBeginTime = new Date(dateFormat.format(new Date(inp_callBegin))); // Throws illegal arument exception?
    else {
      System.err.println("Invalid date begin format");
      this.callBeginTime = null;
    }
    if (this.validateJavaDate(inp_callEnd))
      this.callEndTime = new Date(dateFormat.format(new Date(inp_callEnd)));
    else {
      System.err.println("Invalid date end format");
      this.callEndTime = null;
    }
    if (this.validate_name(inp_caller))
      this.caller = inp_caller;
    else{
      System.err.println("Invalid name given for caller");
      this.caller = null;
    }
    if (this.validate_name(inp_callee))
      this.callee = inp_callee;
    else {
      System.err.println("Invalid name given for callee");
      this.callee = null;
    }
    if (this.validate_number(inp_callerNumber))
      this.callerNumber = inp_callerNumber;
    else {
      System.err.println("Invalid caller phone number");
      this.callerNumber = null;
    }
    if (this.validate_number(inp_calleeNumber))
      this.calleeNumber = inp_calleeNumber;
    else {
      System.err.println("Invalid callee phone number");
      this.calleeNumber = null;
    }
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
    // throw new UnsupportedOperationException("This method is not implemented yet");
    // return this.callBegin;
    if (this.callBeginTime != null)
      return DateFormat.getDateInstance(DateFormat.SHORT).format(this.callBeginTime);
    else
      return null;
  }

  /**
   * gets the end time from class
   * @return returns the end time as string
   */
  @Override
  public String getEndTimeString() {
    // throw new UnsupportedOperationException("This method is not implemented yet");
    // return this.callEnd;
    if (this.callEndTime != null)
      return DateFormat.getDateInstance(DateFormat.SHORT).format(this.callEndTime);
    else
      return null;
  }

  /**
   * gets the begin time for a call but preserves the date and time information
   */
  public String getCallBegin(){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    return dateFormat.format(this.callBeginTime);
  }

  /**
   * gets the end time for a call but preserves the date and time information
   */
  public String getCallEnd(){
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    return dateFormat.format(this.callEndTime);
  }


  /**
   * This method acts to get the time difference in minutes between the start and end of a call
   * 60000 is the number of miliseconds in a minute
   */
  public long getCallTimeMinute(){
    long startTime = this.callBeginTime.getTime();
    long endTime = this.callEndTime.getTime();
    return ((endTime - startTime) / 60000);
  }

  /**
   * @param o the object to be compared.
   * @return returns the value of the comparison just a strcmp does
   * Function just acts to sort via the compare to function
   */
  @Override
  public int compareTo(PhoneCall o) {
    long startTime = this.callBeginTime.getTime();
    long startTimeExternal = o.callBeginTime.getTime();
    if (startTimeExternal - startTime == 0){
      return o.getCallerNumber().compareTo(this.getCallerNumber());
    }
    return  (int) (startTimeExternal - startTime);
  }
}
