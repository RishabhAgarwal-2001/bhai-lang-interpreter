package com.schrodinger.tokenizer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Specification {
    private String regex;
    private TokenType tokenType;
}
