package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AppointmentBookDumper;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class TextDumper implements PhoneBillDumper<PhoneBill> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  @Override
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
          pw.println(to_add.getBeginTimeString());
          pw.println(to_add.getEndTimeString());
        }
      }
      pw.flush();
    }
  }
}
