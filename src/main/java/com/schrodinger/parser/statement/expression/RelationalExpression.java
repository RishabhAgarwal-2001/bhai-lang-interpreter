package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.TokenType;

public class RelationalExpression extends Expression {
    public RelationalExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.ADDITIVE_EXPRESSION, TokenType.RELATIONAL_OPERATOR);
    }
}
