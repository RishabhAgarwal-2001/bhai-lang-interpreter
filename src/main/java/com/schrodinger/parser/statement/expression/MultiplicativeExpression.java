package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class MultiplicativeExpression extends Expression {
    public MultiplicativeExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.PRIMARY_EXPRESSION, TokenType.MULTIPLICATIVE_OPERATOR_TYPE);
    }
}
