package com.schrodinger;

import com.schrodinger.interpreter.Interpreter;

public class Main {
    public static void main(String[] args) {
        final Interpreter interpreter = new Interpreter();
        interpreter.interpret("""
                hi bhai;
                    bhai ye hai a = 1;
                    jab tak bhai (a < 10) {
                        bol bhai a;
                        a = a + 1;
                    }
                bye bhai;
                """);
    }
}