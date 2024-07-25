package com.schrodinger.interpreter.visitor.statement;

import com.schrodinger.interpreter.DependencyModule;
import com.schrodinger.interpreter.Scope;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.parser.ASTNode;

import java.util.List;
import java.util.Objects;

public class IfStatementVisitor implements Visitor {
    @Override
    public Object visitNode(ASTNode node) {
        final ASTNode test = node.getTest();
        final Scope parentScope = DependencyModule.getCurrentScope();
        if (Objects.nonNull(test)) {
            final Object testResult = DependencyModule.getVisitor(test.getType()).visitNode(test);
            if (Objects.nonNull(testResult)
                    && ((testResult instanceof Boolean && (Boolean) testResult)
                                    || (testResult instanceof String && testResult.equals("sahi")))) {
                evaluateNode(node.getConsequent(), parentScope);
            } else {
                final List<ASTNode> alternates = node.getAlternates();
                if (Objects.nonNull(alternates)) {
                    for(final ASTNode alternate: alternates) {
                        final ASTNode alternateTest = alternate.getTest();
                        if(Objects.isNull(alternateTest)) {
                            evaluateNode(alternate.getConsequent(), parentScope);
                        } else {
                            final Object alternateTestResult = DependencyModule.getVisitor(alternateTest.getType()).visitNode(alternateTest);
                            if (Objects.nonNull(alternateTestResult)
                                    && ((alternateTestResult instanceof Boolean && (Boolean) alternateTestResult)
                                    || (alternateTestResult instanceof String && alternateTestResult.equals("sahi")))) {
                                evaluateNode(alternate.getConsequent(), parentScope);
                                break;
                            }
                        }
                    }
                }
            }
        }
        parentScope.setBreakStatement(DependencyModule.getCurrentScope().isBreakStatement());
        parentScope.setContinueStatement(DependencyModule.getCurrentScope().isContinueStatement());
        DependencyModule.setCurrentScope(parentScope);
        return null;
    }

    private void evaluateNode(final ASTNode node, final Scope parentScope) {
        if(Objects.nonNull(node)) {
            DependencyModule.setCurrentScope(new Scope(parentScope));
            DependencyModule.getCurrentScope().setLoop(parentScope.isLoop());
            DependencyModule.getVisitor(node.getType()).visitNode(node);
        }
    }
}
