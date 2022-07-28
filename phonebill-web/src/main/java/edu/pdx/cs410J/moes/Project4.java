package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.Date;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 * NEED TO DO:
 *  Print new call - done
 *  Search for calls - done
 *  Tests
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";
    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

    /**
     * This function acts as a quick method to validate the dates passed in
     * @param begin string of the begin date
     * @param end string of the end date
     * @return return true if there is no problem, return false if there is
     */
    public static boolean quickDateCheck(String begin, String end){
        try{
            dateFormat.parse(begin);
        }
        catch (ParseException e){
            System.err.println("Malformed begin date");
            System.err.println(e.getMessage());
            return false;
        }
        try{
            dateFormat.parse(end);
        }
        catch (ParseException e){
            System.err.println("Malformed end date");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Check if the number is a valid phone number
     * @param to_validate is the string representation of the number to validate
     * @return returns true if its a valid number, false otherwise
     */
    static public boolean validate_number(String to_validate){
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
            System.err.println("too few dashes in phone number");
        } else if (dashes > 2) {
            System.err.println("too many dashes");
        }
        if (numbers != 10){
            System.err.println("contains too many non number characters, or too many numbers");
        }
        return numbers == 10 && dashes == 2;
    }

    /**
     * This is the main function
     * @param args are the command line arguments
     */
    public static void main(String... args){
        if (args.length < 1){
            System.err.println(MISSING_ARGS);
            // System.err.println(args.length);
            // usage("");
            // error("");
            return;
        }
        String to_check = "";
        int index = 0;
        int numberOfOptions = 0;
        int portIndex = 0;
        int hostIndex = 0;
        int numberOfArguments = 0;
        int argumentsStartPoint = 0;
        boolean portCheck = false;
        boolean hostCheck = false;
        boolean searchCheck = false;
        boolean printCheck = false;
        if (args[0].equalsIgnoreCase("-README")) {
            try (InputStream read_meF = Project4.class.getResourceAsStream("README.txt"))
            {
                Scanner read_me = new Scanner(read_meF);
                while (read_me.hasNextLine()) {
                    String data = read_me.nextLine();
                    System.out.println(data);
                }
                return;
            }
            catch (IOException e){
                System.err.println("Could not open readmefile");
            }
            return;
        }
        for (to_check = args[index]; index < args.length -1; to_check = args[index]) {
            index += 1;
            // options are -host hostname, -port port, -search, -print, and -README.
            if (to_check.startsWith("-")){
                numberOfOptions += 1;
                if (!to_check.equalsIgnoreCase("-host") && !to_check.equalsIgnoreCase("-port") &&
                !to_check.equalsIgnoreCase("-search") && !to_check.equalsIgnoreCase("-print") &&
                !to_check.equalsIgnoreCase("-README")){
                    System.err.println("Invalid command line argument(s) for options");
                    return;
                }
                else{
                    argumentsStartPoint += 1;
                }
            }
            if (to_check.equalsIgnoreCase("-host")){
                hostCheck = true;
                hostIndex = index;
                argumentsStartPoint += 1;
            } else if (to_check.equalsIgnoreCase("-port")) {
                portCheck = true;
                portIndex = index;
                argumentsStartPoint += 1;
            } else if (to_check.equalsIgnoreCase("-README")) {
                try (InputStream read_meF = Project4.class.getResourceAsStream("README.txt"))
                {
                    Scanner read_me = new Scanner(read_meF);
                    while (read_me.hasNextLine()) {
                        String data = read_me.nextLine();
                        System.out.println(data);
                    }
                    return;
                }
                catch (IOException e){
                    System.err.println("Could not open readmefile");
                }
                return;
            } else if (to_check.equalsIgnoreCase("-search")){
                searchCheck = true;
            } else if (to_check.equalsIgnoreCase("-print")){
                printCheck = true;
            }
        }
        if (!(portCheck && hostCheck)){
            // System.err.println("Failed to give a port and a host, port and host are needed for any command line use");
            if (!hostCheck)
                System.err.println("Failed to give a host, both a host and port are needed for use");
            if(!portCheck)
                System.err.println("Failed to give a port, both a host and port are needed for use");
            return;
        }
        numberOfArguments = (args.length - argumentsStartPoint);
        if (numberOfOptions > 5){
            System.err.println("Too many command line arguments given. Options include -host hostname, -port port," +
                    "-search, -print, and -README");
            System.err.println("Given: " + numberOfOptions + " options");
            return;
        }
        if (numberOfArguments > 9){
            System.err.println("Too many command line arguments given for the phonebill/phonecall");
            return;
        }
        String portString = args[portIndex];
        int port;
        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }
        String hostName = args[hostIndex];
        PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
        String message = "";
        try {
            if (numberOfArguments == 9){
                if (searchCheck){
                    System.err.println("Please enter the name and dates for a search and not with the phone numbers.");
                    return;
                }
                if (printCheck){
                    PhoneCall call = new PhoneCall(args[argumentsStartPoint], "Not given", args[argumentsStartPoint+1],
                            args[argumentsStartPoint+2], args[argumentsStartPoint+3] + " " + args[argumentsStartPoint+4] +
                            " " + args[argumentsStartPoint+5], args[argumentsStartPoint+6] + " "
                            + args[argumentsStartPoint+7] + " " + args[argumentsStartPoint+8]);
                    System.out.println(call.getCaller());
                    System.out.println(call.getCallee());
                    System.out.println(call.getCallerNumber());
                    System.out.println(call.getCalleeNumber());
                    System.out.println(call.getBeginTimeString());
                    System.out.println(call.getEndTimeString());
                }
                String beginCheck = args[argumentsStartPoint+3] + " " + args[argumentsStartPoint+4] +
                        " " + args[argumentsStartPoint+5];
                String endCheck = args[argumentsStartPoint+6] + " "
                        + args[argumentsStartPoint+7] + " " + args[argumentsStartPoint+8];
                if (!quickDateCheck(beginCheck, endCheck)) {
                    System.err.println("BAD DATES");
                    return;
                }
                if (!validate_number(args[argumentsStartPoint+1])){
                    System.err.println("malformed caller number");
                    System.err.println("expected format is 123-123-1234 aka nnn-nnn-nnnn");
                    return;
                }
                if (!validate_number(args[argumentsStartPoint+2])){
                    System.err.println("malformed callee number");
                    System.err.println("expected format is 123-123-1234 aka nnn-nnn-nnnn");
                    return;
                }
                client.createNewCallInBill(args[argumentsStartPoint], args[argumentsStartPoint+1],
                        args[argumentsStartPoint+2], args[argumentsStartPoint+3] + " " + args[argumentsStartPoint+4] +
                                " " + args[argumentsStartPoint+5], args[argumentsStartPoint+6] + " "
                                + args[argumentsStartPoint+7] + " " + args[argumentsStartPoint+8]);
                return;
            } else if (numberOfArguments == 7){
                if (!searchCheck) {
                    System.err.println("Need to use option -search to search for calls");
                    return;
                }
                String beginCheck = args[argumentsStartPoint+1] + " " +
                        args[argumentsStartPoint+2] + " " + args[argumentsStartPoint+3];
                String endCheck = args[argumentsStartPoint+4] + " " + args[argumentsStartPoint+5] + " " +
                        args[argumentsStartPoint+6];
                if (!quickDateCheck(beginCheck, endCheck))
                    return;
                message = client.returnAllCallsCustomerTimeRange(args[argumentsStartPoint] + "-pretty", args[argumentsStartPoint+1] + " " +
                                args[argumentsStartPoint+2] + " " + args[argumentsStartPoint+3],
                        args[argumentsStartPoint+4] + " " + args[argumentsStartPoint+5] + " " + args[argumentsStartPoint+6]);
                System.out.println(message);
                return;
            } else if (numberOfArguments == 1){
                client.prettyPrintIt(args[argumentsStartPoint]);
                return;
                // not correct we need to pp it and return to user.
            }
            //  client.returnAllCallsCustomer(args[argumentsStartPoint]);
        } catch (IOException ex) {
            error("While contacting server: " + ex);
            return;
        }
        System.out.println(message);

    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getHttpStatusCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getHttpStatusCode(), response.getContent()));
        }
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 -host  hostname -port portname [options] <args>");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  options      -search, -print, -README");
        err.println("  args         customer, caller number, callee number, begin, end");
        err.println();
        err.println("This simple program posts Phone bills and their calls");
        err.println("to the server.");
        err.println("If no host is specified, then there will be an error if combined with a port. If just arguments" +
                "it can then just print the call");
        err.println("You can also search calls in a range, and all calls for a customer");
        err.println();
    }
}