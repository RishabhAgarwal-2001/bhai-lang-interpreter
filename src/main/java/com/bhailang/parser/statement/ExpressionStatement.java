package com.bhailang.parser.statement;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.parser.statement.expression.Expression;
import com.bhailang.tokenizer.TokenType;

public class ExpressionStatement extends Statement {
    public ExpressionStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        final ASTNode expression = Expression.getExpressionImpl(NodeType.ASSIGNMENT_EXPRESSION).getExpression();
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.SEMI_COLON_TYPE);
        return ASTNode.builder().type(NodeType.EXPRESSION_STATEMENT).expression(expression).build();
    }
}
