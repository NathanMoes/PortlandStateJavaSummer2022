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
      // pw.println('\n');
      for (PhoneCall to_add : bill.calls){
        pw.println(to_add.caller);
        //pw.println(',');
        pw.println(to_add.callee);
        //pw.println(',');
        pw.println(to_add.callerNumber);
        //pw.println(',');
        pw.println(to_add.calleeNumber);
        //pw.println(',');
        pw.println(to_add.callBegin);
        //pw.println(',');
        pw.println(to_add.callEnd);
        //pw.println(',');
        //pw.println('\n');
      }
      pw.flush();
    }
  }
}
