package com.bhailang.interpreter.visitor.statement;

import java.util.Objects;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;
import com.bhailang.parser.NodeType;

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
