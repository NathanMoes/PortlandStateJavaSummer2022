package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.*;

/**
 * The main class for the CS410J Phone Bill Project
 * Note to self, says data and time are seperate arumnets on line. so need to update that
 */
public class Project3 {

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
   * This is the main function for project/program 1, it takes command line arguments and creates external class objects
   * Namely phone call and phone class objects
   * @param args is the arguments pass in from the command line
   *             Does not return anything
   */


  public static void main(String[] args) {
    String userDir = System.getProperty("user.dir");
    int call_argument_start_point = 0;
    int text_file_name = 0;
    boolean to_print_check = false;
    if (args.length < 1) {
      System.err.println("Missing command line arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin date> <begin time> " +
              "<end date> <end time>");
      return;
    }
    for (String to_check : args){
      if (to_check.startsWith("-")){
        call_argument_start_point += 1;
        if (!to_check.equalsIgnoreCase("-print") && !to_check.equalsIgnoreCase("-README") &&
                !to_check.equalsIgnoreCase("-textFile")){
          System.err.println("Invalid command line argument(s) for options");
          return;
        }
      }
      if (to_check.equalsIgnoreCase("-textFile")){
        text_file_name = call_argument_start_point;
        call_argument_start_point += 1;
      }
      if (to_check.equalsIgnoreCase("-print")){
        to_print_check = true;
      }
    }
    if ((args.length - call_argument_start_point) > 7){
      System.err.println("too many command line arguments");
      return;
    }
    if (call_argument_start_point > 3){
      System.err.println("Too many options arguments given, options include -README, Textfile, and print");
      return;
    }
    for (String to_check : args){
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
    for (String to_check : args)
    {
      if (to_check.equalsIgnoreCase("-textFile")){
        Path path = Paths.get("temp.txt");
        if (Files.exists(path))
          System.err.println("IT DO");
        else {
          System.err.println("It donet");
        }
        File cwd = new File(userDir);
        File result = Project3.find(args[text_file_name], cwd);
        test = new PhoneCall(args[call_argument_start_point], "None", args[call_argument_start_point+1],
                args[call_argument_start_point+2],
                args[call_argument_start_point+3] + " " + args[call_argument_start_point+4],
                args[call_argument_start_point+5] + " " + args[call_argument_start_point+6]);
        if (result == null) {
          bill = new PhoneBill(args[call_argument_start_point]);
          bill.addPhoneCall(test);
          File temp_file = new File(args[text_file_name]);

          Writer output_file = null;
          try{
            output_file = new FileWriter(args[text_file_name]);
          }
          catch (IOException e){
            System.err.println("failed to write to file");
          }

          TextDumper to_dump = new TextDumper(output_file);
          to_dump.dump(bill);
          if (to_print_check) {
            System.out.println(test.toString());
            System.out.println("Caller: " + test.getCaller());
            System.out.println("Callee: " + test.getCallee());
            System.out.println("Caller: " + test.getBeginTimeString());
            System.out.println("Caller: " + test.getEndTimeString());
          }
          return;
        }
        FileReader read_from = null;
        try{read_from = new FileReader(result);}
        catch (IOException e){ System.err.println("failed file read from");}
        TextParser to_parse = new TextParser(read_from);
        if (result.length() > 0) {
          try {
            bill = to_parse.parse();
          } catch (ParserException e) {
            System.err.println("failed file parse from existing file");
          }
        }
        if (to_print_check) {
          System.out.println(test.toString());
          System.out.println("Caller: " + test.getCaller());
          System.out.println("Callee: " + test.getCallee());
          System.out.println("Caller: " + test.getBeginTimeString());
          System.out.println("Caller: " + test.getEndTimeString());
        }
        if (bill != null) {
          if (!bill.getCustomer().equals(args[call_argument_start_point])){
            System.err.println("Name of customer file does not match the name provided");
            return;
          }
          bill.addPhoneCall(test);
          Writer output_file = null;
          result.delete();
          result = new File(args[text_file_name]);
          try{
            output_file = new FileWriter(result);
          }
          catch (IOException e){
            System.err.println("failed to write to file");
          }
          TextDumper to_dump = new TextDumper(output_file);
          to_dump.dump(bill);
          return;
        }
        bill = new PhoneBill(args[call_argument_start_point]);
        bill.addPhoneCall(test);
        Writer output_file = null;
        result.delete();
        result = new File(args[text_file_name]);
        try{
          output_file = new FileWriter(result);
        }
        catch (IOException e){
          System.err.println("failed to write to file");
        }
        TextDumper to_dump = new TextDumper(output_file);
        to_dump.dump(bill);
      }
      if (to_check.equalsIgnoreCase("-print")){
        bill = new PhoneBill(args[call_argument_start_point]);
        PhoneCall call = new PhoneCall(args[call_argument_start_point], "None", args[call_argument_start_point+1],
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
      return;
    }
  }

}