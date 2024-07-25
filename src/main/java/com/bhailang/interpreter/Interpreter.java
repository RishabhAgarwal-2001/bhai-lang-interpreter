package com.bhailang.interpreter;

import com.bhailang.parser.ASTNode;
import com.bhailang.parser.Parser;


public class Interpreter {
    private final Parser parser = new Parser();

    public void interpret(final String code) {
        try {
            final ASTNode ast = parser.parse(code);
            DependencyModule.getVisitor(ast.getType()).visitNode(ast);
        } finally {
            DependencyModule.setCurrentScope(new Scope(null));
        }
    }
}
