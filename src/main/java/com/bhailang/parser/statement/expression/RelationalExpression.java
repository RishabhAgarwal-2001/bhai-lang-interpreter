package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class RelationalExpression extends Expression {
    public RelationalExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.ADDITIVE_EXPRESSION, TokenType.RELATIONAL_OPERATOR);
    }
}
