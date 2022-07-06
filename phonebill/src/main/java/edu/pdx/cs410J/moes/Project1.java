package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;

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

  /**
   * This is the main function for project/program 1, it takes command line arguments and creates external class objects
   * Namely phone call and phone class objects
   * @param args is the arguments pass in from the command line
   *             Does not return anything
   */

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Missing command line arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin date> <begin time> " +
              "<end date> <end time>");
      return;
    }
    if (args[0].equals("-README")) {
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
      //TextDumper dump_readme = new TextDumper(read_me);
    }
    PhoneBill bill = new PhoneBill();
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    if (args.length < 7){
      System.err.println("Expected at least 7 arguments" + ". Arguments given: " + args.length);
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
      return;
    }
    if (args.length >= 7){
      System.out.println(""); // correct number of arguments not needed to keep in
    }
    if (args[0].equals("-print")) {
      PhoneCall test = new PhoneCall(args[1], "None", args[2], args[3], args[4] + " " + args[5], args[6] + " " + args[7]);
      bill.addPhoneCall(test);
      test.toString();
      System.out.println("Caller: " + test.getCaller());
      System.out.println("Callee: " + test.getCallee());
      System.out.println("Caller: " + test.getBeginTimeString());
      System.out.println("Caller: " + test.getEndTimeString());
    }
    else {
      PhoneCall test = new PhoneCall(args[0], "None", args[1], args[2], args[3] + " " + args[4], args[5] + " " + args[6]);
      bill.addPhoneCall(test);
    }

  }

}