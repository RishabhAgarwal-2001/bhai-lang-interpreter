package com.schrodinger.tokenizer;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenizerImpl implements Tokenizer {

    private String stringToTokenize;
    private int cursor;

    @Override
    public void initTokenizer(final String stringToTokenize) {
        this.stringToTokenize = stringToTokenize;
        this.cursor = 0;
    }

    @Override
    public boolean isEOF() {
        return Objects.isNull(stringToTokenize) || cursor >= stringToTokenize.length();
    }

    @Override
    public boolean hasMoreTokens() {
        return Objects.nonNull(stringToTokenize) && cursor < stringToTokenize.length();
    }

    @Override
    public Token getNextToken() {
        // TODO: Use better exception classes than RuntimeException
        if(Objects.isNull(stringToTokenize)) {
            throw new RuntimeException("Tokenizer not initialized!");
        }

        if(!hasMoreTokens()) {
            return null;
        }

        final String slicedString = stringToTokenize.substring(cursor);

        for(final Specification specification: Specifications.values()) {
            final String matchedString = matched(specification.getRegex(), slicedString);
            if(Objects.isNull(matchedString)) {
                continue;
            }
            if(specification.getTokenType() == TokenType.NULL_TYPE) {
                return getNextToken();
            }
            return new Token(specification.getTokenType(), matchedString);
        }
        throw new RuntimeException("Kya krr raha hai bhai tu?? Unexpected token: " + stringToTokenize.charAt(0));
    }

    private String matched(final String regex, final String slicedString) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(slicedString);
        if(matcher.find()) {
            final String matchedString = matcher.group();
            cursor += matchedString.length();
            return matchedString;
        }
        return null;
    }
}
