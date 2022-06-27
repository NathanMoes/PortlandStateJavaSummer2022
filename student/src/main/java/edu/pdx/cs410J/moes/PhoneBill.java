package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import java.util.ArrayList;

import java.util.Collection;

public class PhoneBill extends  AbstractPhoneBill{

    public String CustomerName;
    public ArrayList<PhoneCall> calls;

    @Override
    public String getCustomer() {
        return null;
    }

    @Override
    public void addPhoneCall(AbstractPhoneCall abstractPhoneCall) {

    }

    @Override
    public Collection getPhoneCalls() {
        return null;
    }

}
