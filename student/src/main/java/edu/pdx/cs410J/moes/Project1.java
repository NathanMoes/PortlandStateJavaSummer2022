package edu.pdx.cs410J.moes;

public class Project1 {
    public static void main(String[] args) {
        if (args.length <= 1){
            System.out.println("not enough arguments");
            System.out.println(args.length);
        }
        PhoneCall temp = new PhoneCall(args[0], args[1], args[2], args[3], args[4], args[5]);
    }
}
