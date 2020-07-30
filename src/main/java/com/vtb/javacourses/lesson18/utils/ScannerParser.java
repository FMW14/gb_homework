package com.vtb.javacourses.lesson18.utils;

import com.vtb.javacourses.lesson18.exceptions.WrongInputException;

import java.util.HashMap;
import java.util.Map;

public class ScannerParser {
    public Map<String, String> parseLine(String line) {
        if (!line.startsWith("/")) {
            throw new WrongInputException("Command should started with '/'");
        }

        String[] splitted = line.split(" ");
        Map<String, String> args = new HashMap<>();

        if (splitted.length > 0) {
            args.put("command", splitted[0]);
        }

        if (splitted.length > 1) {
            for (int i = 1; i < splitted.length; i++) {
                args.put("arg" + i, splitted[i]);
            }
        }

        return args;
    }

    public void checkArgsNumber(Map<String, String> args, int expectedNumber) {
        if (args.size() - 1 != expectedNumber) {
            throw new WrongInputException("Need at least " + expectedNumber + " argument(s), received " + (args.size() - 1));
        }
    }
}
