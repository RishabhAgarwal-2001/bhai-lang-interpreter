package com.schrodinger.interpreter.visitor;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.parser.ASTNode;

public class ProgramVisitor implements Visitor{
    @Override
    public Object visitNode(ASTNode node) {
        assert node.getBody().size() == 1;
        final ASTNode body = node.getBody().getFirst();
        DependencyModule.getVisitor(body.getType()).visitNode(body);
        return null;
    }
}
