package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class AdditiveExpression extends Expression {
    public AdditiveExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.MULTIPLICATIVE_EXPRESSION, TokenType.ADDITIVE_OPERATOR_TYPE);
    }
}
