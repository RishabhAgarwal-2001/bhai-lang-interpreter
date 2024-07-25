package com.bhailang.parser.statement;

import java.util.List;
import java.util.Objects;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.parser.statement.expression.Expression;
import com.bhailang.tokenizer.TokenType;

public class WhileStatement extends Statement {
    public WhileStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.JAB_TAK_BHAI);
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.OPEN_PARENTHESIS_TYPE);
        final ASTNode condition = Expression.getExpressionImpl(NodeType.ASSIGNMENT_EXPRESSION).getExpression();
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.CLOSED_PARENTHESIS_TYPE);
        if(Objects.isNull(tokenExecutor.getLookahead())) {
            throw new RuntimeException("Unexpected end of 'jab tak bhai' statement");
        }
        final ASTNode body = Statement.getStatementImpl(tokenExecutor.getLookahead()).getStatement();
        return ASTNode.builder().type(NodeType.WHILE_STATEMENT).test(condition).body(List.of(body)).build();
    }
}
