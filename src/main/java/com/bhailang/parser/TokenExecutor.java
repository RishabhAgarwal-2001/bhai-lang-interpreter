package com.bhailang.parser;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import com.bhailang.tokenizer.Token;
import com.bhailang.tokenizer.TokenType;
import com.bhailang.tokenizer.Tokenizer;

public class TokenExecutor {
    private Tokenizer tokenizer;
    @Getter @Setter private Token lookahead;

    public TokenExecutor(final Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Token eatTokenAndForwardLookahead(final TokenType tokenType) {
        final Token token = lookahead;
        if(Objects.isNull(token)) {
            throw new RuntimeException("Unexpected EOF, expected " + tokenType);
        }
        if(token.getTokenType() != tokenType) {
            throw new RuntimeException("kya kar rha hai tu??...Unexpected token, expected " + tokenType + " but found " + token.getTokenType());
        }
        this.setLookahead(tokenizer.getNextToken());
        return token;
    }

    public void eatOptionalSemiColonToken() {
        if(Objects.nonNull(lookahead) && lookahead.getTokenType() == TokenType.SEMI_COLON_TYPE) {
            eatTokenAndForwardLookahead(TokenType.SEMI_COLON_TYPE);
        }
    }
}
