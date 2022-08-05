package edu.pdx.cs410j.moes;

// import com.google.common.annotations.VisibleForTesting;

import java.io.*;
import java.util.Map;

public class PrettyPrinter {
  private final Writer writer;


  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  public PrettyPrinter(){
    this.writer = null;
  }


  /**
   * This function acts to pretty print a phone bill to a file
   *
   */

  public void dump(PhoneBill bill){
    try (
            PrintWriter pw = new PrintWriter(writer);
    ) {
      // pw.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
      if (bill.getPhoneCalls() != null) {
        pw.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
        for (PhoneCall to_add : bill.getPhoneCalls()) {
          pw.println("Call from " + to_add.getCaller() + ", at " +
                  to_add.getCallBegin() + " to " + to_add.getCallEnd() + "." +
                  " Originating from " + to_add.getCallerNumber() + " and contacting " +
                  to_add.getCalleeNumber() + ".");
        }
      }
      else {
        pw.println("Phone bill of " + bill.getCustomer() + ", with zero calls");
      }
    }
  }


  /**
   * This function acts to dump the pretty print to standard output
   * just as the above does for a file
   */
  public void dumpStandard(PhoneBill bill){
    if (bill.getPhoneCalls() != null) {
      System.out.println("Phone bill of " + bill.getCustomer() + ", with calls: ");
      for (PhoneCall to_add : bill.getPhoneCalls()) {
        System.out.println("Call from " + to_add.getCaller() + ", at " +
                to_add.getCallBegin() + " to " + to_add.getCallEnd() + "." +
                " Originating from " + to_add.getCallerNumber() + " and contacting " +
                to_add.getCalleeNumber() + ", and lasting " + to_add.getCallTimeMinute() + " minutes");
      }
    }
    else {
      System.out.println("Phone bill of " + bill.getCustomer() + ", with zero calls");
    }
  }

  /**
   * This function does a dump to a string
   * just as the above but for a string
   */
  public String dumpString(PhoneBill bill){
    StringBuilder toReturn = new StringBuilder();
    if (bill.getPhoneCalls() != null){
      toReturn.append("Phone bill of ").append(bill.getCustomer()).append(", with calls: ");
    }
    for (PhoneCall to_add : bill.getPhoneCalls()){
      toReturn.append("Call from ").append(to_add.getCaller()).append(", at ").append(to_add.getCallBegin()).append(" to ").append(to_add.getCallEnd()).append(".").append(" Originating from ").append(to_add.getCallerNumber()).append(" and contacting ").append(to_add.getCalleeNumber()).append(", and lasting ").append(to_add.getCallTimeMinute()).append(" minutes");
    }
    return toReturn.toString();
  }
}
