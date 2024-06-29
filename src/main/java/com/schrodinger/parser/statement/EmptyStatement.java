package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;
import com.schrodinger.parser.TokenExecutor;

public class EmptyStatement extends Statement {
    public EmptyStatement(TokenExecutor tokenExecutor) {
        super(tokenExecutor);
    }

    @Override
    public ASTNode getStatement() {
        return ASTNode.builder().type(NodeType.EMPTY_STATEMENT).build();
    }
}
