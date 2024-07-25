package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class MultiplicativeExpression extends Expression {
    public MultiplicativeExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.PRIMARY_EXPRESSION, TokenType.MULTIPLICATIVE_OPERATOR_TYPE);
    }
}
