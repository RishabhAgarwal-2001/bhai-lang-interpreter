package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class LogicalANDExpression extends Expression {
    public LogicalANDExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getLogicalExpression(NodeType.EQUALITY_EXPRESSION, TokenType.LOGICAL_AND);
    }
}
