package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

import java.util.Objects;

public class VariableStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        if(Objects.isNull(node.getDeclarations()) || node.getDeclarations().isEmpty()) {
            throw new RuntimeException("No declarations in variable statement");
        }
        for(final ASTNode declaration: node.getDeclarations()) {
            DependencyModule.getVisitor(declaration.getType()).visitNode(declaration);
        }
        return null;
    }
}
