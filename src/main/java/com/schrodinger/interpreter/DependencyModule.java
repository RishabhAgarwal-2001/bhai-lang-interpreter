package com.schrodinger.interpreter;

import com.schrodinger.interpreter.visitor.ProgramVisitor;
import com.schrodinger.interpreter.visitor.Visitor;
import com.schrodinger.interpreter.visitor.expression.AssignmentExpressionVisitor;
import com.schrodinger.interpreter.visitor.expression.BinaryAndLogicalExpressionVisitor;
import com.schrodinger.interpreter.visitor.expression.IdentifierExpressionVisitor;
import com.schrodinger.interpreter.visitor.literal.BooleanLiteralVisitor;
import com.schrodinger.interpreter.visitor.literal.NullLiteralVisitor;
import com.schrodinger.interpreter.visitor.literal.NumericLiteralVisitor;
import com.schrodinger.interpreter.visitor.literal.StringLiteralVisitor;
import com.schrodinger.interpreter.visitor.statement.*;
import com.schrodinger.parser.NodeType;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class DependencyModule {
    @Setter
    private static Scope currentScope;

    private static final Map<NodeType, Visitor> visitorMap = Map.ofEntries(
            // Literal visitors
            Map.entry(NodeType.BOOLEAN_LITERAL, new BooleanLiteralVisitor()),
            Map.entry(NodeType.NULL_LITERAL, new NullLiteralVisitor()),
            Map.entry(NodeType.NUMERIC_LITERAL, new NumericLiteralVisitor()),
            Map.entry(NodeType.STRING_LITERAL, new StringLiteralVisitor()),

            // Expression visitors
            Map.entry(NodeType.ASSIGNMENT_EXPRESSION, new AssignmentExpressionVisitor()),
            Map.entry(NodeType.IDENTIFIER_EXPRESSION, new IdentifierExpressionVisitor()),
            Map.entry(NodeType.BINARY_EXPRESSION, new BinaryAndLogicalExpressionVisitor()),
            Map.entry(NodeType.LOGICAL_EXPRESSION, new BinaryAndLogicalExpressionVisitor()),

            // Statement visitors
            Map.entry(NodeType.BREAK_STATEMENT, new BreakStatementVisitor()),
            Map.entry(NodeType.CONTINUE_STATEMENT, new ContinueStatementVisitor()),
            Map.entry(NodeType.EMPTY_STATEMENT, new EmptyStatementVisitor()),
            Map.entry(NodeType.EXPRESSION_STATEMENT, new ExpressionStatementVisitor()),
            Map.entry(NodeType.IF_STATEMENT, new IfStatementVisitor()),
            Map.entry(NodeType.INIT_STATEMENT, new InitStatementVisitor()),
            Map.entry(NodeType.PRINT_STATEMENT, new PrintStatementVisitor()),
            Map.entry(NodeType.VARIABLE_STATEMENT, new VariableStatementVisitor()),
            Map.entry(NodeType.VARIABLE_DECLARATION, new VariableDeclarationVisitor()),
            Map.entry(NodeType.WHILE_STATEMENT, new WhileStatementVisitor()),
            Map.entry(NodeType.BLOCK_STATEMENT, new BlockStatementVisitor()),

            // Program visitors
            Map.entry(NodeType.PROGRAM, new ProgramVisitor())
    );

    public static Scope getCurrentScope() {
        if(Objects.isNull(currentScope)) {
            currentScope = new Scope(null);
        }
        return currentScope;
    }

    public static Visitor getVisitor(NodeType nodeType) {
        final Visitor visitor = visitorMap.get(nodeType);
        if(Objects.isNull(visitor)) {
            throw new RuntimeException("No visitor found for node type: " + nodeType);
        }
        return visitor;
    }
}
