package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall {

    public String caller;
    public String callee;
    public String callerNumber;
    public String calleeNumber;
    public String callBegin;
    public String callEnd;

    @Override
    public String getCaller() {
        return this.caller;
    }

    @Override
    public String getCallee() {
        return this.callee;
    }

    @Override
    public Date getBeginTime() {
        return super.getBeginTime();
    }

    @Override
    public String getBeginTimeString() {
        return this.callBegin;
    }

    @Override
    public String getEndTimeString() {
        return this.callEnd;
    }

    @Override
    public Date getEndTime() {
        return super.getEndTime();
    }

}
