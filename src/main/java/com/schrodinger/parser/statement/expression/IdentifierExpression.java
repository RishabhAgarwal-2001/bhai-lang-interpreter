package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class IdentifierExpression extends Expression {
    public IdentifierExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        final String name = tokenExecutor.eatTokenAndForwardLookahead(TokenType.IDENTIFIER_TYPE).getValue();
        return ASTNode.builder().type(NodeType.IDENTIFIER_EXPRESSION).name(name).build();
    }
}
