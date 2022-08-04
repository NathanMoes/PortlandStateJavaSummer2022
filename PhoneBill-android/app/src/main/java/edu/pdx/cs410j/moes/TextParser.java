package edu.pdx.cs410j.moes;

import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// need to fully replace and remove the

public class TextParser {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  /**
   * parses a map of string to string
   * @return a map of string to string
   * @throws ParserException if there is an error parsing
   */
  public Map<String, String> parse() throws ParserException {
    Pattern pattern = Pattern.compile("(.*) : (.*)");

    Map<String, String> map = new HashMap<>();

    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      for (String line = br.readLine(); line != null; line = br.readLine()) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
          throw new ParserException("Unexpected text: " + line);
        }

        String word = matcher.group(1);
        String definition = matcher.group(2);

        map.put(word, definition);
      }

    } catch (IOException e) {
      throw new ParserException("While parsing dictionary", e);
    }

    return map;
  }

  /**
   * This parses a reader for a phone bill object creation. Creating a phone bill object in the process
   * @return returns a phone bill object created by the parsing
   * @throws ParserException which is an exception related to parsing
   */
  public PhoneBill parsePhone() throws ParserException {
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
      String caller_num = null;
      String callee_num = null;
      String start_time = null;
      String end_time = null;
      while (customer != null){
        customer = br.readLine();
        if (phone_call_args % 5 == 4){
          end_time = customer;
          phone_call_args += 1;
          if (caller_name == null || caller_num == null || callee_num == null || start_time
                  == null || end_time == null){
            return to_return;
          }
          to_add_in = new PhoneCall(caller_name, caller_num, callee_num, start_time, end_time);
          if (to_add_in.callEndTime == null || to_add_in.callBeginTime == null || to_add_in.calleeNumber == null ||
                  to_add_in.callerNumber == null || to_add_in.caller == null)
          {
            System.err.println("Malformed file");
            return to_return;
          }
          to_return.addPhoneCall(to_add_in);
        }
        else {
          if (phone_call_args % 5 == 0) {
            caller_name = customer;
            phone_call_args += 1;
          }
          else {
              if (phone_call_args % 5 == 2) {
                caller_num = customer;
                phone_call_args += 1;
              }
              else {
                if (phone_call_args % 5 == 3) {
                  callee_num = customer;
                  phone_call_args += 1;
                }
                else {
                  if (phone_call_args % 5 == 4) {
                    start_time = customer;
                    phone_call_args += 1;
                  }
                }
              }
          }
        }
      }
      return to_return;
    } catch (IOException e) {
      System.err.println("failed to set buffered reader");
      throw new ParserException("While parsing phone bill text", e);
    }
  }


}
