package com.netcracker.edu;

import java.io.*;
import java.net.*;

/* This class is for reading from console.*/
public class Client {
    public static void execute() throws IOException,ClassNotFoundException {

        System.out.println("Welcome! Enter command");

        Socket fromserver = null;

        System.out.println("Connecting to localhost ");

        fromserver = new Socket("localhost", 4444);
        BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
        PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);
        BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

        String fuser, fserver;
        while (true) {
            fuser=inu.readLine();
            out.println(fuser);
            fserver = in.readLine();
            System.out.println(fserver);
            if(fserver.equals("0")){
                System.out.println("command executed");
            }else {
                System.out.println("smth happend wrong");
            }
            if (fuser.equalsIgnoreCase("close")) break;
        }

        out.close();
        in.close();
        inu.close();
        fromserver.close();
    }
}