package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class PhoneBillServlet extends HttpServlet
{
    static final String WORD_PARAMETER = "word";
    static final String DEFINITION_PARAMETER = "definition";
    static final String CUSTOMER_PARAMETER = "customer";
    static final String BEGIN_TIME_PARAMETER = "begin";
    static final String END_TIME_PARAMETER = "end";
    static final String CALLER_NUMBER = "callerNumber";
    static final String CALLEE_NUMBER = "calleeNumber";

    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");


    private final Map<String, String> dictionary = new HashMap<>();

    private final ArrayList<PhoneBill> bills = new ArrayList<>();

    /**
     * This function acts as a quick date format checking tool
     * @param begin string representing the begin date
     * @param end string representing the end date
     * @param response is the http response to write to
     * @return return true if we are good, false otherwise
     * @throws IOException if there is an error writing to the response
     */
    public static boolean quickDateCheck(String begin, String end, HttpServletResponse response) throws IOException{
        PrintWriter writer = new PrintWriter(response.getWriter());
        try{
            dateFormat.parse(begin);
        }
        catch (ParseException e){
            writer.println("Malformed begin date");
            writer.println(e.getMessage());
            writer.println("expected format: " + "dd/MM/yyyy hh:mm aa");
            return false;
        }
        try{
            dateFormat.parse(end);
        }
        catch (ParseException e){
            writer.println("Malformed end date");
            writer.println(e.getMessage());
            writer.println("expected format: " + "dd/MM/yyyy hh:mm aa");
            return false;
        }
        return true;
    }

    /**
     * Check if the number is a valid phone number
     */
    public boolean validate_number(String to_validate, HttpServletResponse response) throws IOException{
        PrintWriter writer = new PrintWriter(response.getWriter());
        int dashes = 0; // should be 2
        int numbers = 0; // should be 10, 3-3-4
        for (int i = 0; i < to_validate.length(); i+=1){
            if (to_validate.charAt(i) == '-')
                dashes += 1;
            else if (Character.isDigit(to_validate.charAt(i))) {
                numbers += 1;
            }
        }
        if (dashes < 2){
            writer.println("too few dashes in phone number");
        } else if (dashes > 2) {
            writer.println("too many dashes");
        }
        if (numbers != 10){
            writer.println("contains too many non number characters");
        }
        return numbers == 10 && dashes == 2;
    }

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        PrintWriter writer = new PrintWriter(response.getWriter());

        response.setContentType( "text/plain" );

        String customer = getParameter( CUSTOMER_PARAMETER, request);
        String begin = getParameter( BEGIN_TIME_PARAMETER, request);
        String end = getParameter( END_TIME_PARAMETER, request);
        if (customer != null){
            if (begin != null) {
                if (end != null) {
                    if (!quickDateCheck(begin, end, response))
                        return;
                    // Then we get all the calls from this time period
                    if (!customer.endsWith("-pretty")) {
                        PhoneBill billCheck = searchCustomer(customer);
                        if (billCheck == null) {
                            writer.println("Error, no valid bill exists under that name");
                            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
                            return;
                            // bill = new PhoneBill(customer);
                        }
                        Collection<PhoneCall> calls = billCheck.getCallsInRange(begin, end);
                        // System.err.println(begin);
                        // System.err.println(end);
                        PhoneBill bill = new PhoneBill(customer);
                        if (calls != null) {
                            for (PhoneCall call : calls) {
                                bill.addPhoneCall(call);
                            }
                        } else {
                            // PrintWriter writer = new PrintWriter(response.getWriter());
                            writer.println("No valid calls in given time range: " + begin + " to " + end + ".");
                            response.setStatus(HttpServletResponse.SC_OK);
                            return;
                        }
                        PrettyPrinter prettyPrinter = new PrettyPrinter(response.getWriter());
                        prettyPrinter.dump(bill);
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                    else{
                        // need to make new bill to print from other wise it adds more
                        customer = customer.replace("-pretty", "");
                        PhoneBill billCheck = searchCustomer(customer);
                        if (billCheck == null)
                        {
                            writer.println("No matching customer");
                            response.setStatus( HttpServletResponse.SC_OK);
                            return;
                        }
                        PhoneBill bill = new PhoneBill(customer);
                        Collection<PhoneCall> calls = billCheck.getCallsInRange(begin, end);
                        if (calls != null) {
                            writer.println("Customer: " + bill.getCustomer());
                            for (PhoneCall call : calls) {
                                bill.addPhoneCall(call);
                                writer.println(call.toString());
                                writer.println(call.getCaller());
                                writer.println(call.getCallee());
                                writer.println(call.getCallerNumber());
                                writer.println(call.getCalleeNumber());
                                writer.println(call.getBeginTimeString());
                                writer.println(call.getEndTimeString());
                            }
                        } else {
                            // PrintWriter writer = new PrintWriter(response.getWriter());
                            writer.println("No valid calls in given time range: " + begin + " to " + end + ".");
                            response.setStatus(HttpServletResponse.SC_OK);
                            return;
                        }
                        // PrettyPrinter prettyPrinter = new PrettyPrinter(response.getWriter());
                        // prettyPrinter.dumpStandard(bill);
                        response.setStatus(HttpServletResponse.SC_OK);
                        return;
                    }
                }
                else {
                    response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
                }
            }
            else {
                // return all the phone calls for a bill coresponding to the customer passed in.
                // to PhoneBill toPrint = SearchCustomer(customer); can do within the bottom
                // writeCustomerCalls(customer, response);
                if (customer.endsWith("-pretty")){
                    String passIn = customer.replace("-pretty", "");
                    prettyPrintIt(passIn, response);
                    response.setStatus( HttpServletResponse.SC_OK);
                    return;
                }
                writeCustomerCalls(customer, response);
                response.setStatus( HttpServletResponse.SC_OK);
            }
            return;
        } else{
            // do something else?!?!?!?!??!?!?!!??!??!?!??!
            // probably send out some error message
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        PrintWriter writer = new PrintWriter(response.getWriter());

        response.setContentType( "text/plain" );

        String customer = getParameter( CUSTOMER_PARAMETER, request);
        String callerNumber = getParameter( CALLER_NUMBER, request);
        String calleeNumber = getParameter( CALLEE_NUMBER, request);
        String begin = getParameter( BEGIN_TIME_PARAMETER, request);
        String end = getParameter( END_TIME_PARAMETER, request);
        // need to process for lack of customer, caller, callee, begin, and end
        if ( customer == null){
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
            return;
        }
        if (callerNumber == null){
            missingRequiredParameter(response, CALLER_NUMBER);
            return;
        }
        if (calleeNumber == null){
            missingRequiredParameter(response, CALLEE_NUMBER);
            return;
        }
        if (begin == null){
            missingRequiredParameter(response, BEGIN_TIME_PARAMETER);
            return;
        }
        if (end == null){
            missingRequiredParameter(response, END_TIME_PARAMETER);
            return;
        }
        if (!quickDateCheck(begin, end, response))
            return;
        if (!validate_number(calleeNumber, response)) {
            writer.println("Malformed callee number");
            return;
        }
        if (!validate_number(callerNumber, response)) {
            writer.println("Malformed caller number");
            return;
        }
        PhoneBill bill = searchCustomer(customer);
        if (bill == null){
            // System.err.println("bill not exist creating one now");
            PhoneBill toAddBill = new PhoneBill(customer);
            toAddBill.addPhoneCall(new PhoneCall(customer, "Not given",
                    callerNumber, calleeNumber, begin, end));
            bills.add(toAddBill);
        }
        else{
            PhoneCall call = new PhoneCall(customer, "Not given",
                    callerNumber, calleeNumber, begin, end);
            bill.addPhoneCall(call);
        }
        response.setStatus( HttpServletResponse.SC_OK);

    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        this.dictionary.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Writes the contents of a customer's phone bill to the HTTP response
     * The text of the message is formatted with TextDumper
     * @param customer is the string representing the customer's name
     * @param response is the http servlet response class instace used
     */
    private void writeCustomerCalls(String customer, HttpServletResponse response) throws IOException {
        PhoneBill bill = searchCustomer(customer);
        if (bill == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else {
            PrintWriter pw = response.getWriter();

            TextDumper dumper = new TextDumper(pw);
            dumper.dump(bill);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Searchers for a customer in the arraylist of phone bills and returns the phone bill coresponding to them
     * @param customer is a string representing the customer's name
     * @return returns the valid customer's phone bill or returns null if failure to find
     */
    private PhoneBill searchCustomer(String customer) {
        for (PhoneBill compare : bills){
            if (compare.getCustomer().equalsIgnoreCase(customer)){
                return compare;
            }
        }
        return null;
    }

    /**
     * Does pretty print for the client
     * @param customer is a string to represent the customer name
     */
    public void prettyPrintIt(String customer, HttpServletResponse response) throws IOException{
        PhoneBill bill = searchCustomer(customer);
        if (bill == null) {
            System.err.println("Unable to find customer: " + customer + ". Customer does not exist in the system");
            return;
        }
        else{
            PrettyPrinter prettyPrinter = new PrettyPrinter(response.getWriter());
            prettyPrinter.dumpStandard(bill);
        }
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

}
