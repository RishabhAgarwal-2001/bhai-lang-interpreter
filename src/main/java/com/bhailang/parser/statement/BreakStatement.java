package com.bhailang.parser.statement;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;
import com.bhailang.tokenizer.TokenType;

public class BreakStatement extends Statement {
    public BreakStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        tokenExecutor.eatTokenAndForwardLookahead(TokenType.BAS_KAR_BHAI);
        return ASTNode.builder().type(NodeType.BREAK_STATEMENT).build();
    }
}
