package com.bhailang.parser.statement;

import java.util.ArrayList;
import java.util.List;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.parser.statement.expression.Expression;
import com.bhailang.parser.statement.expression.literal.Literal;
import com.bhailang.tokenizer.TokenType;

public class VariableStatement extends Statement {
    public VariableStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.BHAI_YE_HAI_TYPE);
        final List<ASTNode> declarations = getVariableDeclarationList();
        tokenExecutor.eatOptionalSemiColonToken();
        return ASTNode.builder().type(NodeType.VARIABLE_STATEMENT).declarations(declarations).build();
    }

    private List<ASTNode> getVariableDeclarationList() {
        final List<ASTNode> variableDeclarationList = new ArrayList<>();
        do {
            variableDeclarationList.add(getVariableDeclaration());
        } while (tokenExecutor.getLookahead().getTokenType() == TokenType.COMMA_TYPE
                && tokenExecutor.eatTokenAndForwardLookahead(TokenType.COMMA_TYPE) != null);
        return variableDeclarationList;
    }

    private ASTNode getVariableDeclaration() {
        final ASTNode id = Expression.getExpressionImpl(NodeType.IDENTIFIER_EXPRESSION).getExpression();
        final ASTNode initializer = (tokenExecutor.getLookahead().getTokenType() != TokenType.SEMI_COLON_TYPE &&
                tokenExecutor.getLookahead().getTokenType() != TokenType.COMMA_TYPE) ? getVariableInitializer() :
                Literal.getLiteralImpl(TokenType.NALLA_TYPE).getLiteral();
        return ASTNode.builder().type(NodeType.VARIABLE_DECLARATION).id(id).init(initializer).build();
    }

    private ASTNode getVariableInitializer() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.SIMPLE_ASSIGN_TYPE);
        return Expression.getExpressionImpl(NodeType.ASSIGNMENT_EXPRESSION).getExpression();
    }
}
