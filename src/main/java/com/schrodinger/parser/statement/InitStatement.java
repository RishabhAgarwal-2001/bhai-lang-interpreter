package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import com.schrodinger.tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InitStatement extends Statement {

    private final TokenType START_TOKEN = TokenType.HI_BHAI_TYPE;
    private final TokenType STOP_TOKEN = TokenType.BYE_BHAI_TYPE;

    public InitStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        for(Token lookahead = tokenExecutor.getLookahead();
            Objects.nonNull(lookahead) && lookahead.getTokenType() != START_TOKEN;
            lookahead = tokenExecutor.getLookahead()) {
            tokenExecutor.eatTokenAndForwardLookahead(lookahead.getTokenType());
        }

        tokenExecutor.eatTokenAndForwardLookahead(START_TOKEN);
        tokenExecutor.eatOptionalSemiColonToken();

        List<ASTNode> body = (tokenExecutor.getLookahead().getTokenType() != STOP_TOKEN) ?
                getListOfStatements() : List.of();

        tokenExecutor.eatTokenAndForwardLookahead(STOP_TOKEN);
        tokenExecutor.eatOptionalSemiColonToken();
        return ASTNode.builder().type(NodeType.INIT_STATEMENT).body(body).build();
    }

    private List<ASTNode> getListOfStatements() {
        final List<ASTNode> statementList = new ArrayList<>();
        for (Token lookahead = tokenExecutor.getLookahead();
             Objects.nonNull(lookahead) && lookahead.getTokenType() != STOP_TOKEN;
             lookahead = tokenExecutor.getLookahead()) {
            statementList.add(Statement.getStatementImpl(lookahead).getStatement());
        }
        return statementList;
    }
}
