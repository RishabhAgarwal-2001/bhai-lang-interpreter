package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class EqualityExpression extends Expression {
    public EqualityExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        return getBinaryExpression(NodeType.RELATIONAL_EXPRESSION, TokenType.EQUALITY_OPERATOR);
    }
}
