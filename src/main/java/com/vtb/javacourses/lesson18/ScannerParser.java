package com.vtb.javacourses.lesson18;

import java.util.HashMap;
import java.util.Map;

public class ScannerParser {
    public Map<String, String> parseLine(String line) {
        String[] splitted = line.split(" ");
        Map<String, String> args = new HashMap<>();


        if (splitted.length >= 2) {
            args.put("command", splitted[0]);
            for (int i = 1; i < splitted.length; i++) {
                args.put("arg" + i, splitted[i]);
            }
        } else {
            args.put("command", "err");
            System.err.println("Wrong input");
        }

//        if (splitted.length == 2 && splitted[0].equals("/showProductsByConsumer")) {
//            args.put("command", splitted[0]);
//            args.put("arg1", splitted[1]);
//        } else if (splitted.length == 2 && splitted[0].equals("/showConsumersByProductTitle")) {
//            args.put("command", splitted[0]);
//            args.put("arg1", splitted[1]);
//        } else {
//            args.put("command", "err");
//            System.err.println("Wrong input");
//        }

//        if (splitted.length == 2 && splitted[0].equals("/showConsumersByProductTitle")) {
//            args.put("command", splitted[0]);
//            args.put("arg1", splitted[1]);
//        } else {
//            args.put("command", "err");
//            System.err.println("Wrong input");
//        }


        return args;
    }
}
