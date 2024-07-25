package com.bhailang.interpreter.visitor;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.parser.ASTNode;

public class ProgramVisitor implements Visitor{
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getBody().size() == 1;
        final ASTNode body = node.getBody().getFirst();
        DependencyModule.getVisitor(body.getType()).visitNode(body);
        return null;
    }
}
