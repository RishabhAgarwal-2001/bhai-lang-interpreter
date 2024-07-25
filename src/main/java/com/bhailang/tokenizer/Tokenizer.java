package com.bhailang.tokenizer;

public interface Tokenizer {
    void initTokenizer(String stringToTokenize);

    boolean isEOF();

    boolean hasMoreTokens();

    Token getNextToken();
}
