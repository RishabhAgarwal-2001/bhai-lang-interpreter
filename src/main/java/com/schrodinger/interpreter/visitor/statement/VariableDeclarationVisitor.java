package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.NodeType;

import java.util.Objects;

public class VariableDeclarationVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        if(Objects.isNull(node.getId()) || Objects.isNull(node.getInit())) {
            throw new RuntimeException("No id or init in variable declaration");
        }
        final String identifier = node.getId().getName();
        final Object value;
        if(node.getInit().getType() == NodeType.NULL_LITERAL) {
            value = null;
        } else if(node.getInit().getType() == NodeType.BOOLEAN_LITERAL) {
            value = node.getInit().getStringValue().equals("sahi");
        } else {
            value = DependencyModule.getVisitor(node.getInit().getType()).visitNode(node.getInit());
        }
        if(Objects.nonNull(identifier)) {
            DependencyModule.getCurrentScope().declareVariable(identifier, value);
        }
        return null;
    }
}
