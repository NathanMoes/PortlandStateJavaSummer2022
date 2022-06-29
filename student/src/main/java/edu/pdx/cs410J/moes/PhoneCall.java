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

    PhoneCall(String inp_caller, String inp_callee, String inp_callerNumber, String inp_calleeNumber, String inp_callBegin, String inp_callEnd){
        this.caller = inp_caller;
        this.callee = inp_callee;
        this.callerNumber = inp_callerNumber;
        this.calleeNumber = inp_calleeNumber;
        this.callBegin = inp_callBegin;
        this.callEnd = inp_callEnd;
    }

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
