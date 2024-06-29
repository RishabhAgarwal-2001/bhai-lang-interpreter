package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class AdditiveExpression extends Expression {
    public AdditiveExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.MULTIPLICATIVE_EXPRESSION, TokenType.ADDITIVE_OPERATOR_TYPE);
    }
}
