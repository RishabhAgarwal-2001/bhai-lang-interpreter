package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class LogicalORExpression extends Expression {
    public LogicalORExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getLogicalExpression(NodeType.LOGICAL_AND_EXPRESSION, TokenType.LOGICAL_OR);
    }
}
