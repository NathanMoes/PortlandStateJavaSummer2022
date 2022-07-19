package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextParser implements PhoneBillParser<PhoneBill> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  // PhoneBill to_return = null;
  @Override
  public PhoneBill parse() throws ParserException {
    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {
      int phone_call_args = 0;
      String customer = br.readLine();
      if (customer == null) {
        System.err.println("NUll customer");
        throw new ParserException("Missing customer");
      }
      PhoneBill to_return = new PhoneBill(customer);
      PhoneCall to_add_in = null;
      String caller_name = null;
      String callee_name = null;
      String caller_num = null;
      String callee_num = null;
      String start_time = null;
      String end_time = null;
      // System.err.println("doing the thing " + customer);
      while (customer != null){
        // System.err.println("doing the thing " + customer);
        customer = br.readLine();
        // System.err.println("doing the thing " + customer);
        // phone_call_args += 1;
        if (phone_call_args % 6 == 5){
          end_time = customer;
          //phone_call_args = 0;
          phone_call_args += 1;
          //System.err.println("end time: " + customer);
          if (caller_name == null || callee_name == null || caller_num == null || callee_num == null || start_time
          == null || end_time == null){
            return to_return;
         }
          to_add_in = new PhoneCall(caller_name, callee_name, caller_num, callee_num, start_time, end_time);
          if (to_add_in.callEndTime == null || to_add_in.callBeginTime == null || to_add_in.calleeNumber == null ||
                  to_add_in.callerNumber == null || to_add_in.caller == null || to_add_in.callee == null)
          {
            System.err.println("Malformed file");
            // System.err.println(to_add_in.ca);
            return to_return;
          }
          to_return.addPhoneCall(to_add_in);
          // System.err.println("added in phone call");
        }
        else {
          if (phone_call_args % 6 == 0) {
            caller_name = customer;
            phone_call_args += 1;
            //phone_call_args = 0;
            //System.err.println("caller name: " + customer);
          }
          else {
            if (phone_call_args % 6 == 1) {
              callee_name = customer;
              phone_call_args += 1;
              //System.err.println("callee name: " + customer);
            }
            else {
              if (phone_call_args % 6 == 2) {
                caller_num = customer;
                phone_call_args += 1;
                //System.err.println("caller number: " + customer);
              }
              else {
                if (phone_call_args % 6 == 3) {
                  callee_num = customer;
                  phone_call_args += 1;
                  //System.err.println("callee number: " + customer);
                }
                else {
                  if (phone_call_args % 6 == 4) {
                    start_time = customer;
                    phone_call_args += 1;
                    //System.err.println("start time: " + customer);
                  }
                }
              }
            }
          }
        }
      }
      // System.err.println("created phone bill");
      return to_return;
    } catch (IOException e) {
      System.err.println("failed to set buffered reader");
      throw new ParserException("While parsing phone bill text", e);
    }
  }
}
