package com.bhailang;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.bhailang.interpreter.Interpreter;

public class Main {
    public static void main(String[] args) {
        checkArgs(args);
        final String fileName = args[0];
        checkFileExtension(fileName);
        try {
            final String content = Files.readString(Paths.get(fileName));
            final Interpreter interpreter = new Interpreter();
            interpreter.interpret(content);
        } catch (final IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }

    private static void checkArgs(final String[] args) {
        if(args.length == 1) {
            return;
        }
        throw new IllegalArgumentException("Expected exactly one argument!");
    }

    private static void checkFileExtension(final String fileName) {
        final int indexOfLastDot = fileName.lastIndexOf('.');
        if(indexOfLastDot == -1) {
            throw new IllegalArgumentException("Only *.bl file type supported");
        }
        final String fileExtension = fileName.substring(indexOfLastDot + 1);
        if(!fileExtension.equals("bl")) {
            throw new IllegalArgumentException("Unknown file extension " + fileExtension +". Only *.bl file type supported");
        }
    }
}