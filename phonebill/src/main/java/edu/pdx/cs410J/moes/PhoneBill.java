package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Phone Bill class, implements a phone bill with array of phone calls for a customer
 */
public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customer; //customer name
  public ArrayList<PhoneCall> calls; // Array list of phone calls for the Phone Bill

  /**
   * Default constructor, set to null
   */
  PhoneBill(){
    this.customer = "Default customer";
    //calls = new ArrayList<PhoneCall>();
    this.calls = null;
  }

  /**
   * constructor that takes in the customer as argument to set it
   * @param customer is the customer name as a string
   */
  public PhoneBill(String customer) {
    this.customer = customer;
    //this.calls = new ArrayList<PhoneCall>();
    this.calls = null;
  }

  /**
   * gets the customer name from class
   * @return returns the customer name as string
   */
  @Override
  public String getCustomer() {
    return this.customer;
  }

  /**
   * adds a phone call to the phone bill
   * @param call the call to add to the bill
   */
  @Override
  public void addPhoneCall(PhoneCall call) {
    if (this.calls == null){
      this.calls = new ArrayList<PhoneCall>();
    }
    if (call == null)
      return;
    calls.add(call);
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * gets the list of phone calls from the bill
   * @return returns the array list of phone calls
   */
  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.calls;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * This function is made to sort the phone calls in the phone bill acroding to the start time and then acrding to
   * Phone number
   * It returns nothing as the sorting is done within the method and it is also set to the new things within too
   */
  public void sortCalls(){
    long comp1 = 0;
    long comp2 = 0;
    for (int i = 0; i < this.calls.size(); i+= 1){
      for (int j = 0; j < this.calls.size(); j+=1){
        System.out.println("Doing things");
      }
    }
  }
}
