package edu.pdx.cs410J.moes;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

            
}
