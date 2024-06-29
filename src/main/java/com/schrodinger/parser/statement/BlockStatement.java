package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockStatement extends Statement {
    public BlockStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.OPEN_CURLY_BRACE_TYPE);
        final List<ASTNode> body = tokenExecutor.getLookahead().getTokenType() == TokenType.CLOSED_CURLY_BRACE_TYPE ?
                List.of() : getListOfStatements();
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.CLOSED_CURLY_BRACE_TYPE);
        tokenExecutor.eatOptionalSemiColonToken();
        return ASTNode.builder().type(NodeType.BLOCK_STATEMENT).body(body).build();
    }

    private List<ASTNode> getListOfStatements() {
        final List<ASTNode> statementList = new ArrayList<>();
        for (Token lookahead = tokenExecutor.getLookahead();
             Objects.nonNull(lookahead) && lookahead.getTokenType() != TokenType.CLOSED_CURLY_BRACE_TYPE;
             lookahead = tokenExecutor.getLookahead()) {
            statementList.add(Statement.getStatementImpl(lookahead).getStatement());
        }
        return statementList;
    }
}
