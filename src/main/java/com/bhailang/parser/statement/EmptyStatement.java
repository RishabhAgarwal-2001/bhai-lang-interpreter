package com.bhailang.parser.statement;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;
import com.bhailang.parser.TokenExecutor;

public class EmptyStatement extends Statement {
    public EmptyStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        return ASTNode.builder().type(NodeType.EMPTY_STATEMENT).build();
    }
}
