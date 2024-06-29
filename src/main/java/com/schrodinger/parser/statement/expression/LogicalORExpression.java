package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class LogicalORExpression extends Expression {
    public LogicalORExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getLogicalExpression(NodeType.LOGICAL_AND_EXPRESSION, TokenType.LOGICAL_OR);
    }
}
