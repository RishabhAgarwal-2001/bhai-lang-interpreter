package com.bhailang.parser.statement.expression;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.DependencyModule;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.Token;
import com.bhailang.tokenizer.TokenType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Expression {
    protected TokenExecutor tokenExecutor;

    public static Expression getExpressionImpl(final NodeType expressionNodeType) {
        return switch (expressionNodeType) {
            case PRIMARY_EXPRESSION -> DependencyModule.getPrimaryExpression();
            case MULTIPLICATIVE_EXPRESSION -> DependencyModule.getMultiplicativeExpression();
            case ADDITIVE_EXPRESSION -> DependencyModule.getAdditiveExpression();
            case RELATIONAL_EXPRESSION -> DependencyModule.getRelationalExpression();
            case EQUALITY_EXPRESSION -> DependencyModule.getEqualityExpression();
            case LOGICAL_AND_EXPRESSION -> DependencyModule.getLogicalANDExpression();
            case LOGICAL_OR_EXPRESSION -> DependencyModule.getLogicalORExpression();
            case ASSIGNMENT_EXPRESSION -> DependencyModule.getAssignmentExpression();
            case PARANTHESIZED_EXPRESSION -> DependencyModule.getParanthesizedExpression();
            case IDENTIFIER_EXPRESSION -> DependencyModule.getIdentifierExpression();
            default -> throw new RuntimeException("Unidentified expression type " + expressionNodeType);
        };
    }

    public abstract ASTNode getExpression();

    protected ASTNode getBinaryExpression(final NodeType downstreamExpressionNodeType, final TokenType operatorTokenType) {
        return _getExpression(downstreamExpressionNodeType, operatorTokenType, NodeType.BINARY_EXPRESSION);
    }

    protected ASTNode getLogicalExpression(final NodeType downstreamExpressionNodeType, final TokenType operatorTokenType) {
        return _getExpression(downstreamExpressionNodeType, operatorTokenType, NodeType.LOGICAL_EXPRESSION);
    }

    private ASTNode _getExpression(
            final NodeType downstreamExpressionNodeType,
            final TokenType operatorTokenType,
            final NodeType expressionNodeType
    ) {
        ASTNode left = getExpressionImpl(downstreamExpressionNodeType).getExpression();
        while (tokenExecutor.getLookahead()!=null && tokenExecutor.getLookahead().getTokenType() == operatorTokenType) {
            final Token operator = tokenExecutor.eatTokenAndForwardLookahead(operatorTokenType);
            final ASTNode right = getExpressionImpl(downstreamExpressionNodeType).getExpression();
            left = ASTNode.builder().type(expressionNodeType).operator(operator.getValue()).left(left).right(right).build();
        }
        return left;
    }
}
