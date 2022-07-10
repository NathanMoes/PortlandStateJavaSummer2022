package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.Scanner;

/**
 * The main class for the CS410J Phone Bill Project
 * Note to self, says data and time are seperate arumnets on line. so need to update that
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
   return true;
  }

  static String userDir = System.getProperty("user.dir");

  /**
   * This function is to find the first file that matches the name passed in so that other parts of the program can
   * read and write to it.
   * @param file_name is the name of the file as a string passed in to check against
   * @param file is the file that is passed in on recursive calls and initialy so that there is a starting directory
   */

    static File find(String file_name, File file){
      File[] start = file.listFiles();
      if (start == null){
        System.err.println("file is null");
        return null;
      }
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
    int call_argument_start_point = 0;
    int text_file_name = 0;
    if (args.length < 1) {
      System.err.println("Missing command line arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin date> <begin time> " +
              "<end date> <end time>");
      return;
    }
    for (String to_check : args){
      if (to_check.startsWith("-")){
        call_argument_start_point += 1;
      }
      if (to_check.equalsIgnoreCase("-textFile")){
        text_file_name = call_argument_start_point;
        call_argument_start_point += 1;
      }
    }
    // System.out.println(call_argument_start_point);
    if (call_argument_start_point > 3){
      System.err.println("Too many options arguments given, options include -README, Textfile, and print");
    }
    for (String to_check : args){
      if (to_check.equalsIgnoreCase("-README")){
        try (InputStream read_meF = Project1.class.getResourceAsStream("README.txt"))
        {
          Scanner read_me = new Scanner(read_meF);
          while (read_me.hasNextLine()) {
            String data = read_me.nextLine();
            System.out.println(data);
          }
          // System.out.println(call_argument_start_point);
          return;
        }
        catch (IOException e){
          System.err.println("Could not open readmefile");
        }
      }
    }
    /**
    if (args[0].equals("-README") || args[1].equals("-README")) {
      try (InputStream read_meF = Project1.class.getResourceAsStream("README.txt"))
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
     */
    PhoneBill bill = new PhoneBill(args[call_argument_start_point]);
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    if (args.length < 7){
      System.err.println("Expected at least 7 arguments" + ". Arguments given: " + args.length);
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
      return;
    }
    if (args.length >= 7){
      System.out.println(""); // correct number of arguments not needed to keep in
    }
    PhoneCall test;
    for (String to_check : args)
    {
      if (to_check.equalsIgnoreCase("-textFile")){
        File cwd = new File(userDir);
        File result = Project1.find(args[text_file_name], cwd);
        test = new PhoneCall(args[call_argument_start_point], "None", args[call_argument_start_point+1],
                args[call_argument_start_point+2],
                args[call_argument_start_point+3] + " " + args[call_argument_start_point+4],
                args[call_argument_start_point+5] + " " + args[call_argument_start_point+6]);
        // Need to check if file exits first, if not then creates it.
        if (result == null){
          PhoneBill new_bill = new PhoneBill(args[call_argument_start_point]);

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
          return;
        }
        System.out.println(call_argument_start_point);
        bill.addPhoneCall(test);
        File temp_file = new File(args[text_file_name]);
        Reader read_from = null;
        try{read_from = new FileReader(args[text_file_name]);}
        catch (IOException E){ System.err.println("failed file read from");}
        Writer output_file = null;
        try{
          output_file = new FileWriter(args[text_file_name]);
        }
        catch (IOException e){
          System.err.println("failed to write to file");
        }
        TextDumper to_dump = new TextDumper(output_file);
        to_dump.dump(bill);
        //File cwd = new File(userDir);
        System.out.println(Project1.find("README.md", cwd));
        System.out.println(cwd.getName());
        TextParser to_parse = new TextParser(read_from);
        try {PhoneBill read_from_thing = to_parse.parse();
          System.out.println(read_from_thing.getCustomer() + " test");
        }
        catch (ParserException e) { System.err.println("failed file parse"); }
      }
      /**
      test = new PhoneCall(args[1], "None", args[2], args[3], args[4] + " " + args[5], args[6] + " " + args[7]);
      bill.addPhoneCall(test);
      File temp_file = new File("Testing.txt");
      Reader read_from = null;
      try{read_from = new FileReader("Testing.txt");}
      catch (IOException E){ System.err.println("failed file");}
      Writer output_file = null;
      try{
        output_file = new FileWriter("Testing.txt");
      }
      catch (IOException e){
        System.err.println("failed to write to file");
      }
      TextDumper to_dump = new TextDumper(output_file);
      to_dump.dump(bill);
      File cwd = new File(userDir);
      System.out.println(Project1.find("README.md", cwd));
      System.out.println(cwd.getName());
      TextParser to_parse = new TextParser(read_from);
      try {PhoneBill read_from_thing = to_parse.parse();
      System.out.println(   read_from_thing.getCustomer());
      }
      catch (ParserException e) { System.err.println("failed file parse"); }
      */
      return;
    }
    if (args[0].equalsIgnoreCase("-print") || args[1].equalsIgnoreCase("-print")) {
      test = new PhoneCall(args[1], "None", args[2], args[3], args[4] + " " + args[5], args[6] + " " + args[7]);
      bill.addPhoneCall(test);
      test.toString();
      System.out.println("Caller: " + test.getCaller());
      System.out.println("Callee: " + test.getCallee());
      System.out.println("Caller: " + test.getBeginTimeString());
      System.out.println("Caller: " + test.getEndTimeString());
    }
    else {
      test = new PhoneCall(args[0], "None", args[1], args[2], args[3] + " " + args[4], args[5] + " " + args[6]);
      bill.addPhoneCall(test);
    }
    return;

  }

}