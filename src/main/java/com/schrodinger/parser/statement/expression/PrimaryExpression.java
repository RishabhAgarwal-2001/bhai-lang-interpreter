package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.parser.statement.expression.literal.Literal;
import com.schrodinger.tokenizer.Token;

public class PrimaryExpression extends Expression {
    public PrimaryExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getExpression() {
        final Token lookahead = tokenExecutor.getLookahead();
        return switch (lookahead.getTokenType()) {
            case OPEN_PARENTHESIS_TYPE ->
                    Expression.getExpressionImpl(NodeType.PARANTHESIZED_EXPRESSION).getExpression();
            case STRING_TYPE, NUMBER_TYPE, BOOLEAN_TYPE, NALLA_TYPE ->
                    Literal.getLiteralImpl(lookahead.getTokenType()).getLiteral();
            default -> Expression.getExpressionImpl(NodeType.IDENTIFIER_EXPRESSION).getExpression();
        };
    }
}
