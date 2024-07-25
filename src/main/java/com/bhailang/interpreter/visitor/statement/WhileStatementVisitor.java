package com.bhailang.interpreter.visitor.statement;

import java.util.Objects;
import java.util.function.Function;

import com.bhailang.interpreter.DependencyModule;
import com.bhailang.interpreter.Scope;
import com.bhailang.interpreter.visitor.Visitor;
import com.bhailang.parser.ASTNode;

public class WhileStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        final ASTNode test = node.getTest();
        if (Objects.nonNull(test)) {
            final Scope parentScope = DependencyModule.getCurrentScope();
            DependencyModule.setCurrentScope(new Scope(parentScope));
            DependencyModule.getCurrentScope().setLoop(true);

            final Function<ASTNode, Object> getTestResult = testNode -> DependencyModule.getVisitor(testNode.getType()).visitNode(testNode);

            final Function<Object, Boolean> isTrue = result -> {
                if (result instanceof Boolean) {
                    return (Boolean) result;
                }
                if (result instanceof String) {
                    return !result.equals("galat");
                }
                throw new RuntimeException("Invalid test result");
            };

            int loopCount = 0;
            for (Object result = getTestResult.apply(test); isTrue.apply(result); result = getTestResult.apply(test)) {
                if(DependencyModule.getCurrentScope().isBreakStatement()) {
                    break;
                }
                if(loopCount > 50000) {
                    throw new RuntimeException("Bohot jyada hi chale jaa rha hai loop");
                }
                if(DependencyModule.getCurrentScope().isContinueStatement()) {
                    DependencyModule.getCurrentScope().setContinueStatement(false);
                    continue;
                }
                if(Objects.nonNull(node.getBody())) {
                    for (final ASTNode statement : node.getBody()) {
                        DependencyModule.getVisitor(statement.getType()).visitNode(statement);
                    }
                }
                loopCount++;
            }
            DependencyModule.setCurrentScope(parentScope);
        }
        return null;
    }
}
