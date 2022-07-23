package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;

import java.io.*;
import java.util.Map;

public class PrettyPrinter {
  private final Writer writer;

  @VisibleForTesting
  static String formatWordCount(int count )
  {
    return String.format( "Dictionary on server contains %d words", count );
  }

  @VisibleForTesting
  static String formatDictionaryEntry(String word, String definition )
  {
    return String.format("  %s : %s", word, definition);
  }


  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  public void dump(Map<String, String> dictionary) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {

      pw.println(formatWordCount(dictionary.size()));

      for (Map.Entry<String, String> entry : dictionary.entrySet()) {
        String word = entry.getKey();
        String definition = entry.getValue();
        pw.println(formatDictionaryEntry(word, definition));
      }

      pw.flush();
    }

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
          pw.println("Call from " + to_add.getCaller() + " to " + to_add.getCallee() + ", at " +
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
        System.out.println("Call from " + to_add.getCaller() + " to " + to_add.getCallee() + ", at " +
                to_add.getCallBegin() + " to " + to_add.getCallEnd() + "." +
                " Originating from " + to_add.getCallerNumber() + " and contacting " +
                to_add.getCalleeNumber() + ", and lasting " + to_add.getCallTimeMinute() + " minutes");
      }
    }
    else {
      System.out.println("Phone bill of " + bill.getCustomer() + ", with zero calls");
    }
  }
}
