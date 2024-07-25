package com.bhailang.tokenizer;

public enum TokenType {
    NULL_TYPE(null),
    HI_BHAI_TYPE("hi bhai"),
    BYE_BHAI_TYPE("bye bhai"),
    BOL_BHAI_TYPE("bol bhai"),
    BHAI_YE_HAI_TYPE("bhai ye hai"),
    AGAR_BHAI("agar bhai"),
    WARNA_BHAI("warna bhai"),
    NAHI_TO_BHAI("nahi to bhai"),
    JAB_TAK_BHAI("jab tak bhai"),
    BAS_KAR_BHAI("bas kar bhai"),
    AGLA_DEKH_BHAI("agla dekh bhai"),
    NALLA_TYPE("NALLA"),
    SEMI_COLON_TYPE(";"),
    OPEN_CURLY_BRACE_TYPE("{"),
    CLOSED_CURLY_BRACE_TYPE("}"),
    OPEN_PARENTHESIS_TYPE("("),
    CLOSED_PARENTHESIS_TYPE(")"),
    COMMA_TYPE(","),
    NUMBER_TYPE("NUMBER"),
    IDENTIFIER_TYPE("IDENTIFIER"),
    SIMPLE_ASSIGN_TYPE("SIMPLE_ASSIGN"),
    COMPLEX_ASSIGN_TYPE("COMPLEX_ASSIGN"),
    ADDITIVE_OPERATOR_TYPE("ADDITIVE_OPERATOR"),
    MULTIPLICATIVE_OPERATOR_TYPE("MULTIPLICATIVE_OPERATOR"),
    RELATIONAL_OPERATOR("RELATIONAL_OPERATOR"),
    EQUALITY_OPERATOR("EQUALITY_OPERATOR"),
    STRING_TYPE("STRING"),
    BOOLEAN_TYPE("BOOLEAN"),
    LOGICAL_AND("LOGICAL_AND"),
    LOGICAL_OR("LOGICAL_OR");

    TokenType(final String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }
}
