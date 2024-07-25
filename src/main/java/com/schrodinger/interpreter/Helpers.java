package com.schrodinger.interpreter;

public class Helpers {
    public static Object getOperationValue(final Object left, final Object right, final String operator) {
        final RuntimeException runtimeException =
                new RuntimeException(String.format("Ye kya krr raha hai bhai tu? %s k saath %s aur %s kaam nahi karta", operator, left, right));

        switch(operator) {
            case "=":
                return right;
            case "+=":
            case "+":
                if(checkNumberOperands(left, right)) {
                    return (double) left + (double) right;
                }
                if(checkStringOperands(left, right)) {
                    return left + right.toString();
                }
                if(checkStringAndNumberOperands(left, right)) {
                    return left + right.toString();
                }
                throw runtimeException;
            case "-=":
            case "-":
                if(checkNumberOperands(left, right)) {
                    return (double) left - (double) right;
                }
                throw runtimeException;
            case "*=":
            case "*":
                if(checkNumberOperands(left, right)) {
                    return (double) left * (double) right;
                }
                throw runtimeException;
            case "/=":
            case "/":
                if(checkNumberOperands(left, right)) {
                    if((double) right == 0) {
                        throw new RuntimeException("Zero se divide nahi krr skte bhai!");
                    }
                    return (double) left / (double) right;
                }
                throw runtimeException;

            case "%=":
            case "%":
                if (checkNumberOperands(left, right)) {
                    if ((double) right == 0) {
                        throw new RuntimeException("Zero se modulo calculate nahi krr skte bhai!");
                    }
                    return (double) left % (double) right;
                }
                throw runtimeException;

            case "==":
                return left.equals(right);

            case "!=":
                return !left.equals(right);

            case ">":
                if(checkNumberOperands(left, right)) {
                    return (double) left > (double) right;
                }
                if(checkStringOperands(left, right)) {
                    return left.toString().compareTo(right.toString()) > 0;
                }
                throw runtimeException;

            case "<":
                if(checkNumberOperands(left, right)) {
                    return (double) left < (double) right;
                }
                if(checkStringOperands(left, right)) {
                    return left.toString().compareTo(right.toString()) < 0;
                }
                throw runtimeException;

            case ">=":
                if(checkNumberOperands(left, right)) {
                    return (double) left >= (double) right;
                }
                if(checkStringOperands(left, right)) {
                    return left.toString().compareTo(right.toString()) >= 0;
                }
                throw runtimeException;

            case "<=":
                if(checkNumberOperands(left, right)) {
                    return (double) left <= (double) right;
                }
                if(checkStringOperands(left, right)) {
                    return left.toString().compareTo(right.toString()) <= 0;
                }
                throw runtimeException;

            case "&&":
                if(checkBooleanOperands(left, right)) {
                    return (boolean) left && (boolean) right;
                }
                throw runtimeException;

            case "||":
                if(checkBooleanOperands(left, right)) {
                    return (boolean) left || (boolean) right;
                }
                throw runtimeException;
        }

        throw new RuntimeException("Unsupported operator %s".formatted(operator));
    }

    private static boolean checkNumberOperands(final Object left, final Object right) {
        return left instanceof Double && right instanceof Double;
    }

    private static boolean checkStringOperands(final Object left, final Object right) {
        return left instanceof String && right instanceof String;
    }

    private static boolean checkStringAndNumberOperands(final Object left, final Object right) {
        return (left instanceof String && right instanceof Double) || (left instanceof Double && right instanceof String);
    }

    private static boolean checkBooleanOperands(final Object left, final Object right) {
        return left instanceof Boolean && right instanceof Boolean;
    }
}
