package edu.pdx.cs410J.moes;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class TextDumper {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  public void dump(Map<String, String> dictionary) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ){
      for (Map.Entry<String, String> entry : dictionary.entrySet()) {
        pw.println(entry.getKey() + " : " + entry.getValue());
      }

      pw.flush();
    }
  }

  /**
   * This dumps a phone bill to a file set by the writer passed in for the clas
   * @param bill the phone bill to dump
   */
  public void dump(PhoneBill bill) {
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ) {
      pw.println(bill.getCustomer());
      if (bill.getPhoneCalls() != null) {
        for (PhoneCall to_add : bill.getPhoneCalls()) {
          pw.println(to_add.getCaller());
          pw.println(to_add.getCallee());
          pw.println(to_add.getCallerNumber());
          pw.println(to_add.getCalleeNumber());
          pw.println(to_add.getCallBegin());
          pw.println(to_add.getCallEnd());
        }
      }
      pw.flush();
    }
  }
}
