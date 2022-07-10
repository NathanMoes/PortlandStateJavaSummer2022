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
      while (customer != null){
        customer = br.readLine();
        phone_call_args += 1;
        if (phone_call_args % 6 == 5){
          phone_call_args = 0;
          end_time = customer;
          if (caller_name == null || callee_name == null || caller_num == null || callee_num == null || start_time
          == null || end_time == null){
            return to_return;
          }
          to_return.addPhoneCall(new PhoneCall(caller_name, callee_name, caller_num, callee_num, start_time, end_time));
        }
        else {
          if (phone_call_args % 6 == 0) {
            caller_name = customer;
          }
          else {
            if (phone_call_args % 6 == 1) {
              callee_name = customer;
            }
            else {
              if (phone_call_args % 6 == 2) {
                caller_num = customer;
              }
              else {
                if (phone_call_args % 6 == 3) {
                  callee_num = customer;
                }
                else {
                  if (phone_call_args % 6 == 4) {
                    start_time = customer;
                  }
                }
              }
            }
          }
        }
      }
      return to_return;

    } catch (IOException e) {
      throw new ParserException("While parsing phone bill text", e);
    }
  }
}
