package com.bhailang.tokenizer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {
    private TokenType tokenType;
    private String value;
}
