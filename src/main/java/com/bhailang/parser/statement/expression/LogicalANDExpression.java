package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class LogicalANDExpression extends Expression {
    public LogicalANDExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getLogicalExpression(NodeType.EQUALITY_EXPRESSION, TokenType.LOGICAL_AND);
    }
}
