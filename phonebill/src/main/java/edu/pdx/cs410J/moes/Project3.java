package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.nio.file.*;

/**
 * The main class for the CS410J Phone Bill Project
 * Note to self, need to make it so we can read and add in from a file with path aka moes/moes.txt etc
 */
public class Project3 {
  // -textFile valid-phonebill.txt -print Mike 342-234-2341 123-421-4362 11/11/2011 10:30 11/12/2011 11:30
  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
   return true;
  }

  // static String userDir = System.getProperty("user.dir");

  /**
   * This function is to find the first file that matches the name passed in so that other parts of the program can
   * read and write to it.
   * @param file_name is the name of the file as a string passed in to check against
   * @param file is the file that is passed in on recursive calls and initialy so that there is a starting directory
   */

    static File find(String file_name, File file){
      File[] start = file.listFiles();
      // if ( start == null || start.length < 1){
      if ( start == null){
        System.err.println("file is null");
        return null;
      }
      // if (file.getName().equals(file_name))
      if (file.getName().equals(file_name))
        return file;
      else{
        for (File check : start){
          if (check.isDirectory()){
            find(file_name, check);
          } else if (file_name.equalsIgnoreCase(check.getName())) {
            return check;
          }
        }
      }
      return null;
    }


  /**
   * This function gets the file name from a path, makes things easier for naming new files if they don't exist
   * @param input is the path of the file as a string
   * @return returns a string representing the file's name
   */
  static String getFileNameOnlyNotPath(String input){
      // String to_return = "";
      Path path = Paths.get(input);
      // Path fileName = path.getFileName();
      return (path.getFileName()).toString();
      // return to_return;
    }


  /**
   * This method acts to get the time difference in minutes between the start and end of a call
   * 60000 is the number of miliseconds in a minute
   */
  static boolean checkCallTimeNotZero(Date callBeginTime, Date callEndTime){
    long startTime = callBeginTime.getTime();
    long endTime = callEndTime.getTime();
    long result =  ((endTime - startTime) / 60000);
    return (result > 0);
  }

  /**
   * This is the main function for project/program 1, it takes command line arguments and creates external class objects
   * Namely phone call and phone class objects
   * @param args is the arguments pass in from the command line
   *             Does not return anything
   */


  public static void main(String[] args) {
    String userDir = System.getProperty("user.dir");
    System.getProperty("root");
    // System.out.println(userDir);
    int call_argument_start_point = 0;
    int text_file_name = 0;
    int pretty_file_name = 0;
    int numberOfOptions = 0;
    boolean to_print_check = false;
    if (args.length < 1) {
      System.err.println("Missing command line arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin date> <begin time> " +
              "<end date> <end time>");
      return;
    }
    // for (String to_check : args){
    String to_check = "";
    int index = 0;
    for (to_check = args[index]; index < args.length -1; to_check = args[index]){
      index += 1;
      if (to_check.startsWith("-")){
        numberOfOptions += 1;
        if (!to_check.equalsIgnoreCase("-print") && !to_check.equalsIgnoreCase("-README") &&
                !to_check.equalsIgnoreCase("-textFile") && !to_check.equalsIgnoreCase("-pretty")){
          System.err.println("Invalid command line argument(s) for options");
          return;
        }
      }
      if (to_check.equalsIgnoreCase("-textFile")){
        text_file_name = call_argument_start_point + 1;
        call_argument_start_point += 2;
      }
      if (to_check.equalsIgnoreCase("-print")){
        to_print_check = true;
        call_argument_start_point += 1;
      }
      if (to_check.equalsIgnoreCase("-pretty")){
        pretty_file_name = call_argument_start_point + 1;
        call_argument_start_point += 2;
      }
    }
    if ((args.length - call_argument_start_point) > 7){
      System.err.println("too many command line arguments");
      return;
    }
    if (numberOfOptions > 4){  // was 3
      System.err.println("Too many options arguments given, options include -README, Textfile, and print");
      System.err.println("Given: " + call_argument_start_point);
      return;
    }
    for (index = 0, to_check = args[index]; index < args.length; to_check = args[index], index += 1){
      if (to_check.equalsIgnoreCase("-README")){
        try (InputStream read_meF = Project3.class.getResourceAsStream("README.txt"))
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
      }
    }
    PhoneBill bill = null;
    if (args.length < 7){
      System.err.println("Expected at least 7 arguments" + ". Arguments given: " + args.length);
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
      return;
    }
    PhoneCall test;
    index = 0;
    for (to_check = args[index]; index < args.length - 1; to_check = args[index]){
      index += 1;
      if (to_check.equalsIgnoreCase("-textFile")){
        // System.out.println("text file called");
        Path path = Paths.get(args[text_file_name]);
        File result = null;
        if (!Files.exists(path)) {
          File cwd = new File(userDir);
          result = Project3.find(getFileNameOnlyNotPath(args[text_file_name]), cwd);
        }
        else {
          result = new File(args[text_file_name]);
        }
        Date validateStart = null;
        Date validateEnd = null;
        try {
          validateStart = new Date(args[call_argument_start_point + 3] + " " + args[call_argument_start_point + 4]);
          validateEnd = new Date(args[call_argument_start_point + 5] + " " + args[call_argument_start_point + 6]);
        }
        catch (IllegalArgumentException e){
          System.err.println(e.getMessage());
          System.err.println("Incorrect formating for the date passed in");
          return;
        }
        if (!Project3.checkCallTimeNotZero(validateStart, validateEnd))
        {
          System.err.println("The Time for the call was invalid. Meaning it has a zero or negative call time");
          return;
        }
        test = new PhoneCall(args[call_argument_start_point], "Not given", args[call_argument_start_point+1],
                args[call_argument_start_point+2],
                args[call_argument_start_point+3] + " " + args[call_argument_start_point+4],
                args[call_argument_start_point+5] + " " + args[call_argument_start_point+6]);
        if (result == null) {
          bill = new PhoneBill(args[call_argument_start_point]);
          bill.addPhoneCall(test);
          File temp_file = new File(getFileNameOnlyNotPath(args[text_file_name]));

          Writer output_file = null;
          try{
            output_file = new FileWriter(temp_file);
            // output_file = new FileWriter(temp_file);
          }
          catch (IOException e){
            System.err.println(e.getMessage());
            System.err.println("failed to write to file");
          }

          TextDumper to_dump = new TextDumper(output_file);
          to_dump.dump(bill);
        }
        else {
          FileReader read_from = null;
          try {
            read_from = new FileReader(result);
          } catch (IOException e) {
            System.err.println("failed file read from");
          }
          TextParser to_parse = new TextParser(read_from);
          if (result.length() > 0) {
            try {
              bill = to_parse.parse();
            } catch (ParserException e) {
              System.err.println("failed file parse from existing file");
            }
          }
          if (bill != null) {
            if (!bill.getCustomer().equals(args[call_argument_start_point])) {
              System.err.println("Name of customer file does not match the name provided");
              return;
            }
            bill.addPhoneCall(test);
            Writer output_file = null;
            result.delete();
            // result = new File(args[text_file_name]);
            result = new File(getFileNameOnlyNotPath(args[text_file_name]));
            try {
              output_file = new FileWriter(result);
            } catch (IOException e) {
              System.err.println("failed to write to file");
            }
            TextDumper to_dump = new TextDumper(output_file);
            to_dump.dump(bill);
            // return;
          }
          else {
            bill = new PhoneBill(args[call_argument_start_point]);
            bill.addPhoneCall(test);
            Writer output_file = null;
            result.delete();
            // result = new File(args[text_file_name]);
            // getFileNameOnlyNotPath(args[text_file_name])
            result = new File(getFileNameOnlyNotPath(args[text_file_name]));
            try {
              output_file = new FileWriter(result);
            } catch (IOException e) {
              System.err.println("failed to write to file");
            }
            TextDumper to_dump = new TextDumper(output_file);
            to_dump.dump(bill);
          }
        }
      }
      if (to_check.equalsIgnoreCase("-print")){
        // System.out.println("print called");
        bill = new PhoneBill(args[call_argument_start_point]);
        Date validateStart = null;
        Date validateEnd = null;
        try {
          validateStart = new Date(args[call_argument_start_point + 3] + " " + args[call_argument_start_point + 4]);
          validateEnd = new Date(args[call_argument_start_point + 5] + " " + args[call_argument_start_point + 6]);
        }
        catch (IllegalArgumentException e){
          System.err.println(e.getMessage());
          System.err.println("Incorrect formating for the date passed in");
          return;
        }
        if (!Project3.checkCallTimeNotZero(validateStart, validateEnd))
        {
          System.err.println("The Time for the call was invalid. Meaning it has a zero or negative call time");
          return;
        }
        PhoneCall call = new PhoneCall(args[call_argument_start_point], "Not given", args[call_argument_start_point+1],
                args[call_argument_start_point+2],
                args[call_argument_start_point+3] + " " + args[call_argument_start_point+4],
                args[call_argument_start_point+5] + " " + args[call_argument_start_point+6]);
        bill.addPhoneCall(call);
        System.out.println(call.toString());
        System.out.println(call.getCaller());
        System.out.println(call.getCallee());
        System.out.println(call.getCallerNumber());
        System.out.println(call.getCalleeNumber());
        System.out.println(call.getBeginTimeString());
        System.out.println(call.getEndTimeString());
      }
      if (to_check.equalsIgnoreCase("-pretty"))
      {
        if (bill == null)
          bill = new PhoneBill(args[call_argument_start_point]);
        System.out.println("pretty called");
        Path path = Paths.get(args[pretty_file_name]);
        File result = null;
        if (!Files.exists(path)) {
          System.err.println("not path");
          System.out.println(args[pretty_file_name]);
          File cwd = new File(userDir);
          result = Project3.find(getFileNameOnlyNotPath(args[pretty_file_name]), cwd);
        }
        else {
          System.out.println(args[pretty_file_name]);
          result = new File(args[pretty_file_name]);
        }
        Date validateStart = null;
        Date validateEnd = null;
        try {
          validateStart = new Date(args[call_argument_start_point + 3] + " " + args[call_argument_start_point + 4]);
          validateEnd = new Date(args[call_argument_start_point + 5] + " " + args[call_argument_start_point + 6]);
        }
        catch (IllegalArgumentException e){
          System.err.println(e.getMessage());
          System.err.println("Incorrect formating for the date passed in");
          return;
        }
        if (!Project3.checkCallTimeNotZero(validateStart, validateEnd))
        {
          System.err.println("The Time for the call was invalid. Meaning it has a zero or negative call time");
          return;
        }
        test = new PhoneCall(args[call_argument_start_point], "Not given", args[call_argument_start_point+1],
                args[call_argument_start_point+2],
                args[call_argument_start_point+3] + " " + args[call_argument_start_point+4],
                args[call_argument_start_point+5] + " " + args[call_argument_start_point+6]);
        if (result == null) {
          System.err.println("sda");
          result = new File(args[pretty_file_name]);
          // return;
        }
        Collections.sort(bill.calls);
        PrettyPrinter prettyPrinter = new PrettyPrinter(bill);
        prettyPrinter.dump();
        if (to_check.contains("y"))
          System.out.println("contains correct");
        prettyPrinter.dump(result);
        // Collections.sort(bill.calls);
        }
      }
    }
}

/**
 * Need to add in a check for minutes in the addition of new calls to the bill // ADDED
 * Need to make it so the phone bill is sorted first acroding to start time then acording to phone number (if equal)
 */

