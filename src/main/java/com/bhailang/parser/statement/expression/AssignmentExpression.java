package com.bhailang.parser.statement.expression;

import java.util.Objects;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Token;
import com.bhailang.tokenizer.TokenType;

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
