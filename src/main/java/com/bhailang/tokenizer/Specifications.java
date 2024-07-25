package com.bhailang.tokenizer;

import java.util.List;

public class Specifications {
    private static final List<Specification> specifications = List.of(
            // Whitespaces
            new Specification("^\\s+", TokenType.NULL_TYPE),

            // Single line comments
            new Specification("^//.*", TokenType.NULL_TYPE),

            // Multi line comments
            new Specification("^/\\*[\\s\\S]*?/\\*", TokenType.NULL_TYPE),

            // Symbols, Delimiters
            new Specification("^;", TokenType.SEMI_COLON_TYPE),
            new Specification("^\\{", TokenType.OPEN_CURLY_BRACE_TYPE),
            new Specification("^\\}", TokenType.CLOSED_CURLY_BRACE_TYPE),
            new Specification("^\\(", TokenType.OPEN_PARENTHESIS_TYPE),
            new Specification("^\\)", TokenType.CLOSED_PARENTHESIS_TYPE),
            new Specification("^,", TokenType.COMMA_TYPE),

            // Keywords
            new Specification("^\\bhi bhai\\b", TokenType.HI_BHAI_TYPE),
            new Specification("^\\bbye bhai\\b", TokenType.BYE_BHAI_TYPE),
            new Specification("^\\bbol bhai\\b", TokenType.BOL_BHAI_TYPE),
            new Specification("^\\bbhai ye hai\\b", TokenType.BHAI_YE_HAI_TYPE),
            new Specification("^\\bagar bhai\\b", TokenType.AGAR_BHAI),
            new Specification("^\\bnahi to bhai\\b", TokenType.NAHI_TO_BHAI),
            new Specification("^\\bwarna bhai\\b", TokenType.WARNA_BHAI),
            new Specification("^\\bnalla\\b", TokenType.NALLA_TYPE),
            new Specification("^\\bjab tak bhai\\b", TokenType.JAB_TAK_BHAI),
            new Specification("^\\bbas kar bhai\\b", TokenType.BAS_KAR_BHAI),
            new Specification("^\\bagla dekh bhai\\b", TokenType.AGLA_DEKH_BHAI),

            // Number
            new Specification("^[+-]?([\\d]*[.])?[\\d]+", TokenType.NUMBER_TYPE),

            // Boolean
            new Specification("^\\bsahi\\b", TokenType.BOOLEAN_TYPE),
            new Specification("^\\bgalat\\b", TokenType.BOOLEAN_TYPE),

            // Identifier
            new Specification("^\\w+", TokenType.IDENTIFIER_TYPE),

            // Equality operators
            new Specification("^[=!]=", TokenType.EQUALITY_OPERATOR),

            // Assignment operators
            new Specification("^=", TokenType.SIMPLE_ASSIGN_TYPE),
            new Specification("^[\\*\\%\\/\\+\\-]=/", TokenType.COMPLEX_ASSIGN_TYPE),

            // Operators
            new Specification("^[\\+\\-]", TokenType.ADDITIVE_OPERATOR_TYPE),
            new Specification("^[\\*\\%\\/]", TokenType.MULTIPLICATIVE_OPERATOR_TYPE),
            new Specification("^[><]=?", TokenType.RELATIONAL_OPERATOR),

            // Logical operators
            new Specification("^&&", TokenType.LOGICAL_AND),
            new Specification("^\\|\\|", TokenType.LOGICAL_OR),

            // Strings
            new Specification("^\"[^\"]*?\"", TokenType.STRING_TYPE),
            new Specification("^'[^']*?'", TokenType.STRING_TYPE)
    );

    public static List<Specification> values() {
        return specifications;
    }
}
