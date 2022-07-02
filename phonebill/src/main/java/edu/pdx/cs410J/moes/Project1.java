package edu.pdx.cs410J.moes;

import com.google.common.annotations.VisibleForTesting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    return true;
  }

  public static void main(String[] args) {
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    if (args.length <= 1) {
      System.err.println("Missing command line arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
      return;
    }
    if (args.length < 5){
      System.err.println("Expected at least 5 arguments");
      System.err.println("Expected input is [options] <customer> <callerNumber> <calleeNumber> <begin> <end>");
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
    PhoneCall test = new PhoneCall(args[1], "Testing stuff", args[2], args[3], args[4], args[5]);
    if (args[0].equals("-print")) {
      System.out.println("Caller: " + test.getCaller());
      System.out.println("Callee: " + test.getCallee());
      System.out.println("Caller: " + test.getBeginTimeString());
      System.out.println("Caller: " + test.getEndTimeString());
    } else if (args[0].equals("-README")) {
      //BufferedReader readme = new BufferedReader("./README.md");
      FileReader readme = new FileReader("./README.md");
      TextDumper dump_readme = new TextDumper(readme);
    }
  }

}