package com.netcracker.edu;

/**
 * Created by Zhassulan on 29.11.2015.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        switch (args[0].toLowerCase()) {
            case "file":
                ScriptReader.execute();
                break;
            case "console":
                Client.execute();
                break;
        }
    }
}
