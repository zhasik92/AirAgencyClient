package com.netcracker.edu;

import java.io.*;
import java.net.Socket;

/**
 * Created by Zhassulan on 29.11.2015.
 */
public class ScriptReader {
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void execute() throws IOException {
        System.out.println("Welcome! Enter command");

        Socket fromserver = null;

        System.out.println("Connecting to localhost ");
        BufferedReader br=null;
        try {
             br = new BufferedReader(new FileReader("buying_ticket_scenario.txt"));
            fromserver = new Socket("localhost", 4444);
            BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);

            String fuser, fserver;
            while (true) {
                fuser = br.readLine();
                if (fuser == null) {
                    br.close();
                    br=new BufferedReader(new FileReader("buying_ticket_scenario.txt"));
                    fuser=br.readLine();
                }
                String[]command=fuser.split(":");
                out.println(command[0]);
                fserver = in.readLine();
                int executionCode=Integer.parseInt(fserver);
                System.out.println("currentValue = "+executionCode+", expected value="+command[1]);
                if(executionCode!=Integer.parseInt(command[1])){
                    throw new Exception("Test failed :"+"currentValue = "+executionCode+", expected value="+command[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }
}
