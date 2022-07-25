package edu.pdx.cs410J.moes;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args){
        if (args.length < 1){
            System.err.println(MISSING_ARGS);
            System.err.println(args.length);
            return;
        }
        String to_check = "";
        int index = 0;
        int numberOfOptions = 0;
        int portIndex = 0;
        int hostIndex = 0;
        int numberOfArguments = 0;
        int argumentsStartPoint = 0;
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
                hostIndex = index;
                argumentsStartPoint += 1;
            } else if (to_check.equalsIgnoreCase("-port")) {
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
            }
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
            /**
            System.out.println(args[argumentsStartPoint]);
            client.createNewCallInBill(args[argumentsStartPoint], "123-123-1231", "123-123-1231"
            , "10/10/2022 10:30 AM", "10/10/2022 7:30 PM");
            */
            if (numberOfArguments == 9){
                client.createNewCallInBill(args[argumentsStartPoint], args[argumentsStartPoint+1],
                        args[argumentsStartPoint+2], args[argumentsStartPoint+3] + " " + args[argumentsStartPoint+4] +
                                " " + args[argumentsStartPoint+5], args[argumentsStartPoint+6] + " "
                                + args[argumentsStartPoint+7] + " " + args[argumentsStartPoint+8]);
                return;
            } else if (numberOfArguments == 7){
                client.returnAllCallsCustomerTimeRange(args[argumentsStartPoint] + "-pretty", args[argumentsStartPoint+1] + " " +
                                args[argumentsStartPoint+2] + " " + args[argumentsStartPoint+3],
                        args[argumentsStartPoint+4] + " " + args[argumentsStartPoint+5] + " " + args[argumentsStartPoint+6]);
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
    public static void main(String... args) {
        String hostName = null;
        String portString = null;
        String word = null;
        String definition = null;

        for (String arg : args) {
            if (hostName == null) {
                hostName = arg;

            } else if ( portString == null) {
                portString = arg;

            } else if (word == null) {
                word = arg;

            } else if (definition == null) {
                definition = arg;

            } else {
                usage("Extraneous command line argument: " + arg);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );
        }

        int port;
        try {
            port = Integer.parseInt( portString );
            
        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);

        String message;
        try {
            if (word == null) {
                // Print all word/definition pairs
                Map<String, String> dictionary = client.getAllDictionaryEntries();
                StringWriter sw = new StringWriter();
                PrettyPrinter pretty = new PrettyPrinter(sw);
                pretty.dump(dictionary);
                message = sw.toString();

            } else if (definition == null) {
                // Print all dictionary entries
                message = PrettyPrinter.formatDictionaryEntry(word, client.getDefinition(word));

            } else {
                // Post the word/definition pair
                client.addDictionaryEntry(word, definition);
                message = Messages.definedWordAs(word, definition);
            }

        } catch (IOException | ParserException ex ) {
            error("While contacting server: " + ex);
            return;
        }

        System.out.println(message);
    }
    */

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
        err.println("usage: java Project4 host port [word] [definition]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  word         Word in dictionary");
        err.println("  definition   Definition of word");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no definition is specified, then the word's definition");
        err.println("is printed.");
        err.println("If no word is specified, all dictionary entries are printed");
        err.println();
    }
}