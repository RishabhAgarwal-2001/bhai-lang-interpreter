package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.parser.statement.expression.Expression;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.schrodinger.tokenizer.TokenType.NAHI_TO_BHAI;
import static com.schrodinger.tokenizer.TokenType.WARNA_BHAI;

public class IfStatement extends Statement {

    private static final List<TokenType> HANDLED_LOOP_TOKEN_TYPES = List.of(WARNA_BHAI, NAHI_TO_BHAI);

    public IfStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        final ASTNode ifStatement = getConditionalStatement(TokenType.AGAR_BHAI);
        final List<ASTNode> alternates = new ArrayList<>();
        for (
                Token lookahead = tokenExecutor.getLookahead();
                Objects.nonNull(lookahead) && HANDLED_LOOP_TOKEN_TYPES.contains(lookahead.getTokenType());
                lookahead = tokenExecutor.getLookahead()
        ) {
            if (lookahead.getTokenType() == WARNA_BHAI) {
                tokenExecutor.eatTokenAndForwardLookahead(WARNA_BHAI);
                alternates.add(Statement.getStatementImpl(tokenExecutor.getLookahead()).getStatement());
            } else if (lookahead.getTokenType() == NAHI_TO_BHAI) {
                alternates.add(getConditionalStatement(NAHI_TO_BHAI));
            }
        }
        return ASTNode.builder().type(NodeType.IF_STATEMENT).test(ifStatement.getTest())
                .consequent(ifStatement.getConsequent()).alternates(alternates).build();
    }

    private ASTNode getConditionalStatement(final TokenType tokenType) {
        tokenExecutor.eatTokenAndForwardLookahead(tokenType);
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.OPEN_PARENTHESIS_TYPE);
        final ASTNode expression = Expression.getExpressionImpl(NodeType.ASSIGNMENT_EXPRESSION).getExpression();
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.CLOSED_PARENTHESIS_TYPE);
        if (Objects.isNull(tokenExecutor.getLookahead())) {
            throw new RuntimeException("Unexpected end of " + tokenType + " statement.");
        }
        final ASTNode consequent = Statement.getStatementImpl(tokenExecutor.getLookahead()).getStatement();
        return ASTNode.builder().type(NodeType.IF_STATEMENT).test(expression).consequent(consequent).build();
    }
}
