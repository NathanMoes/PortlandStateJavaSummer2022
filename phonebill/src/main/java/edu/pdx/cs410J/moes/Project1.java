package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;

import java.io.*;
import java.util.Scanner;

/**
 * The main class for the CS410J Phone Bill Project
 * Note to self, says data and time are seperate arumnets on line. so need to update that
 */
public class Project1 {

  //@VisibleForTesting
  //static boolean isValidPhoneNumber(String phoneNumber) {
   // return true;
  //}


  public static void main(String[] args) {
    PhoneBill bill = new PhoneBill();
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    if (args.length <= 1) {
      System.err.println("Missing command line arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
      return;
    }
    if (args.length < 7){
      System.err.println("Expected at least 7 arguments" + ". Arguments given: " + args.length);
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
      return;
    }
    if (args.length >= 7){
      System.out.println(""); // correct number of arguments
    }
    Scanner read_me = null;
    File readme = new File("./README.md");
    try {
      read_me = new Scanner(readme);
    }
    catch (FileNotFoundException exception){
      System.err.println("failed to open file");
      exception.printStackTrace();
      return;
    }
    /**for (String arg : args) {
      System.out.println(arg);
    }
    System.out.println("OPTION: " + args[0]);
    System.out.println("customer: " + args[1]);
    System.out.println("callerNumber: " + args[2]);
    System.out.println("calleeNumber: " + args[3]);
    System.out.println("begin: " + args[4]);
    System.out.println("end: " + args[5]);
    System.out.println(args.length);
     */

    if (args[0].equals("-print")) {
      PhoneCall test = new PhoneCall(args[1], "Testing stuff", args[2], args[3], args[4] + " " + args[5], args[6] + " " + args[7]);
      bill.addPhoneCall(test);
      test.toString();
      System.out.println("Caller: " + test.getCaller());
      System.out.println("Callee: " + test.getCallee());
      System.out.println("Caller: " + test.getBeginTimeString());
      System.out.println("Caller: " + test.getEndTimeString());
    } else if (args[0].equals("-README")) {
      PhoneCall test = new PhoneCall(args[1], "Testing stuff", args[2], args[3], args[4] + " " + args[5], args[6] + " " + args[7]);
      bill.addPhoneCall(test);
      //BufferedReader readme = new BufferedReader("./README.md");
      while (read_me.hasNextLine()) {
        String data = read_me.nextLine();
        System.out.println(data);
      }
      //TextDumper dump_readme = new TextDumper(read_me);
    }
    else {
      PhoneCall test = new PhoneCall(args[0], "Testing stuff", args[1], args[2], args[3] + " " + args[4], args[5] + " " + args[6]);
      bill.addPhoneCall(test);
    }

  }

}