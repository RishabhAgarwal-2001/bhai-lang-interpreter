package com.bhailang.interpreter.visitor.statement;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

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
