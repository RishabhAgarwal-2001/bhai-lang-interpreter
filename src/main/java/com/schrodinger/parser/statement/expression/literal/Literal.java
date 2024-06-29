package com.schrodinger.parser.statement.expression.literal;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public abstract class Literal {
    protected TokenExecutor tokenExecutor;

    public Literal(TokenExecutor tokenExecutor) {
        this.tokenExecutor = tokenExecutor;
    }

    public static Literal getLiteralImpl(final TokenType tokenType) {
        return switch (tokenType) {
            case STRING_TYPE -> DependencyModule.getStringLiteral();
            case BOOLEAN_TYPE -> DependencyModule.getBooleanLiteral();
            case NALLA_TYPE -> DependencyModule.getNullLiteral();
            case NUMBER_TYPE -> DependencyModule.getNumericLiteral();
            default -> throw new RuntimeException("Unidentified token type " + tokenType);
        };
    }

    public abstract ASTNode getLiteral();

}
