package com.bhailang.parser.statement;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class ContinueStatement extends Statement {
    public ContinueStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.AGLA_DEKH_BHAI);
        return ASTNode.builder().type(NodeType.CONTINUE_STATEMENT).build();
    }
}
