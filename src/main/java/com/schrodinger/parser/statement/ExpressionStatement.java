package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.parser.statement.expression.Expression;
import com.schrodinger.tokenizer.TokenType;

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
