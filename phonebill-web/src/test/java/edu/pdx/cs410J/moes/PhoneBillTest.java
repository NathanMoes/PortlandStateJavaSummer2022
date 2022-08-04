package edu.pdx.cs410J.moes;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class is to test the phone bill class
 */
public class PhoneBillTest {

    /**
     * tests the creation of a PhoneBill with no arguments
     */
    @Test
    void TestCreationNoArgs(){
        PhoneBill testing = new PhoneBill();
        assertThat(testing, is(notNullValue()));
    }

    /**
     * tests the addition of a phone call to the bill
     */
    @Test
    void TestAddPhoneCall(){
        PhoneBill testing = new PhoneBill();
        PhoneCall toAdd = new PhoneCall();
        testing.addPhoneCall(toAdd);
        assertThat(testing.getPhoneCalls(), is(notNullValue()));
    }

    /**
     * Tests the phone call list to make sure it is null from start. Aka no calls
     */
    @Test
    void TestNoCallsIsNull(){
        PhoneBill testing = new PhoneBill();
        assertThat(testing.getPhoneCalls(), is(nullValue()));
    }

    /**
     * Tests to make sure there is no customer when no customer is set
     */
    @Test
    void TestNoCustomer(){
        PhoneBill testing = new PhoneBill();
        assertThat(testing.getCustomer(), containsString("Default"));
    }

    /**
     * Tests if the PhoneBill can be created correctly with a customer name
     */
    @Test
    void TestCustomerInit(){
        PhoneBill testing = new PhoneBill("Tom");
        assertThat(testing.getCustomer(), containsString("Tom"));
    }

    /**
     * Tests if phone call list/array is initialy null aka has no calls if none added yet
     */
    @Test
    void TestCustomerInitCalls(){
        PhoneBill testing = new PhoneBill("Tom");
        assertThat(testing.getPhoneCalls(), is(nullValue()));
    }


    /**
     * tests that the customer name is correct
     */
    @Test
    void TestCustomerNotNull(){
        PhoneBill testing = new PhoneBill("Dave");
        assertThat(testing.getCustomer(), containsString("Dave"));
    }

    /**
     * tests that we get some valid return from calls in range
     */
    @Test
    void testCallsInRange(){
        PhoneBill bill = new PhoneBill("Dave");
        bill.addPhoneCall(new PhoneCall("Dave", "Also this", "222-211-2222",
                "333-333-3333", "01/01/1000 10:30 am", "01/01/1000 11:30 am"));
        bill.addPhoneCall(new PhoneCall("Dave", "Also this", "222-211-2222",
                "333-333-3333", "01/01/1000 11:30 am", "01/01/1000 11:40 am"));
        Collection<PhoneCall> calls = bill.getCallsInRange("01/01/1000 10:30 am", "01/01/1000 10:30 am");
        for (PhoneCall call : calls){
            // System.out.println(call.toString());
            assertThat(call.toString(), containsString("Phone call from Dave to Also this from 1/1/00 to 1/1/00"));
        }
        assertThat(calls, is(notNullValue()));
    }

    /**
     * tests that we get null from the request to calls from a blank phonebill
     */
    @Test
    void testNullCallsNoCallsInPhoneBill(){
        PhoneBill bill = new PhoneBill();
        assertThat(bill.getCallsInRange("01/01/2000 10:30 am","01/01/2000 10:33 am"), is(nullValue()));
    }


}
