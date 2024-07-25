package com.bhailang.parser;

import java.util.List;

public class Program {
    public ASTNode getProgram() {
        return ASTNode.builder().type(NodeType.PROGRAM)
                .body(List.of(DependencyModule.getInitStatement().getStatement())).build();
    }
}
