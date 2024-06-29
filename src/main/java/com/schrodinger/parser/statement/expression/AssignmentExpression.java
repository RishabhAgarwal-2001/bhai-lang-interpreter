package com.schrodinger.parser.statement.expression;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;

import java.util.Objects;

public class AssignmentExpression extends Expression {

    private LogicalORExpression logicalORExpression;

    public AssignmentExpression(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
        logicalORExpression = (LogicalORExpression) Expression.getExpressionImpl(NodeType.LOGICAL_OR_EXPRESSION);
    }

    @Override
    public ASTNode getExpression() {
        final ASTNode left = logicalORExpression.getExpression();
        if(!isAssignmentOperator(tokenExecutor.getLookahead())) {
            return left;
        }
        return ASTNode.builder().type(NodeType.ASSIGNMENT_EXPRESSION).operator(getAssignmentOperator().getValue())
                .left(checkValidAssignmentTarget(left))
                .right(getExpression()).build();
    }

    private boolean isAssignmentOperator(final Token token) {
        return Objects.nonNull(token) && (token.getTokenType() == TokenType.SIMPLE_ASSIGN_TYPE || token.getTokenType() == TokenType.COMPLEX_ASSIGN_TYPE);
    }

    private Token getAssignmentOperator() {
        final Token lookahead = tokenExecutor.getLookahead();
        if(Objects.nonNull(lookahead) && lookahead.getTokenType() == TokenType.SIMPLE_ASSIGN_TYPE) {
            return tokenExecutor.eatTokenAndForwardLookahead(TokenType.SIMPLE_ASSIGN_TYPE);
        }
        return tokenExecutor.eatTokenAndForwardLookahead(TokenType.COMPLEX_ASSIGN_TYPE);
    }

    private ASTNode checkValidAssignmentTarget(final ASTNode node) {
        if(node.getType() == NodeType.IDENTIFIER_EXPRESSION) {
            return node;
        }
        throw new RuntimeException("Kya krr raha hai bhai tu?");
    }
}
