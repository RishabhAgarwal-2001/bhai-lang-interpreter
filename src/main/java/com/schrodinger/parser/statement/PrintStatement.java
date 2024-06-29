package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.parser.statement.expression.Expression;
import com.schrodinger.tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class PrintStatement extends Statement {
    public PrintStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.BOL_BHAI_TYPE);
        final List<ASTNode> expressions = getExpressionList();
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.SEMI_COLON_TYPE);
        return ASTNode.builder().type(NodeType.PRINT_STATEMENT).expressions(expressions).build();
    }

    private List<ASTNode> getExpressionList() {
        final List<ASTNode> expressions = new ArrayList<>();
        do {
            expressions.add(getExpression());
        } while (
                tokenExecutor.getLookahead().getTokenType() == TokenType.COMMA_TYPE
                && tokenExecutor.eatTokenAndForwardLookahead(TokenType.COMMA_TYPE) != null
        );
        return expressions;
    }

    private ASTNode getExpression() {
        return Expression.getExpressionImpl(NodeType.ASSIGNMENT_EXPRESSION).getExpression();
    }
}
