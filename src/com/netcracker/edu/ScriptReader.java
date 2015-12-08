package com.netcracker.edu;

import java.io.*;
import java.net.Socket;

/**
 * Testing server by reading commands from file, if expected value!=currentvalue, test will fail with exception
 * Created by Zhassulan on 29.11.2015.
 */
public class ScriptReader {
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void execute(String[] args) throws IOException {
        System.out.println("Welcome! Enter command");

        Socket fromserver = null;

        System.out.println("Connecting to localhost ");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(args[1]));
            fromserver = new Socket("localhost", 4444);
            BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);

            String fuser, fserver;
            fuser = "sign_in " + args[2] + " " + args[3] + " ;0";
            while (true) {
                if (fuser == null) {
                    br.close();
                    br = new BufferedReader(new FileReader(args[1]));
                    fuser = br.readLine();
                }
                String[] command = fuser.split(";");
                out.println(command[0]);
                fserver = in.readLine();
                int executionCode = Integer.parseInt(fserver);
                System.out.println("currentValue = " + executionCode + ", expected value=" + command[1]);
                if (Integer.parseInt(command[command.length - 1]) != -1) {
                    if (executionCode != Integer.parseInt(command[command.length - 1])) {
                        throw new Exception("Test failed: " + fuser + ", currentValue = " + executionCode + ", expected value=" + command[1]);
                    }else {
                        System.out.println(executionCode + " unchecked command");
                    }
                }
                fuser = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }
}
