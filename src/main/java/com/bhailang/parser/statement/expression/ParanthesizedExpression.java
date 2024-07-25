package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class ParanthesizedExpression extends Expression {
    public ParanthesizedExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.OPEN_PARENTHESIS_TYPE);
        final ASTNode node = Expression.getExpressionImpl(NodeType.ASSIGNMENT_EXPRESSION).getExpression();
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.CLOSED_PARENTHESIS_TYPE);
        return node;
    }
}
