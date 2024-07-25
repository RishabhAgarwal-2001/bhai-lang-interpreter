package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

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
