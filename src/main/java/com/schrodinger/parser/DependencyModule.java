package com.schrodinger.parser;

import com.schrodinger.parser.statement.*;
import com.schrodinger.parser.statement.expression.*;
import com.schrodinger.parser.statement.expression.literal.BooleanLiteral;
import com.schrodinger.parser.statement.expression.literal.NullLiteral;
import com.schrodinger.parser.statement.expression.literal.NumericLiteral;
import com.schrodinger.parser.statement.expression.literal.StringLiteral;
import com.schrodinger.tokenizer.Tokenizer;
import com.schrodinger.tokenizer.TokenizerImpl;

import java.util.Objects;

public class DependencyModule {
    private static Tokenizer tokenizer;
    private static TokenExecutor tokenExecutor;

    private static InitStatement initStatement;
    private static PrintStatement printStatement;
    private static EmptyStatement emptyStatement;
    private static BlockStatement blockStatement;
    private static VariableStatement variableStatement;
    private static IfStatement ifStatement;
    private static WhileStatement whileStatement;
    private static BreakStatement breakStatement;
    private static ContinueStatement continueStatement;
    private static ExpressionStatement expressionStatement;

    private static PrimaryExpression primaryExpression;
    private static MultiplicativeExpression multiplicativeExpression;
    private static AdditiveExpression additiveExpression;
    private static RelationalExpression relationalExpression;
    private static EqualityExpression equalityExpression;
    private static LogicalANDExpression logicalANDExpression;
    private static LogicalORExpression logicalORExpression;
    private static AssignmentExpression assignmentExpression;
    private static ParanthesizedExpression paranthesizedExpression;
    private static IdentifierExpression identifierExpression;

    private static StringLiteral stringLiteral;
    private static NumericLiteral numericLiteral;
    private static BooleanLiteral booleanLiteral;
    private static NullLiteral nullLiteral;

    private static Program program;

    public static Tokenizer getTokenizer() {
        if(Objects.isNull(tokenizer)) {
            tokenizer = new TokenizerImpl();
        }
        return tokenizer;
    }

    public static TokenExecutor getTokenExecutor() {
        if(Objects.isNull(tokenExecutor)) {
            tokenExecutor = new TokenExecutor(getTokenizer());
        }
        return tokenExecutor;
    }

    public static InitStatement getInitStatement() {
        if(Objects.isNull(initStatement)) {
            initStatement = new InitStatement(getTokenExecutor());
        }
        return initStatement;
    }

    public static PrintStatement getPrintStatement() {
        if(Objects.isNull(printStatement)) {
            printStatement = new PrintStatement(getTokenExecutor());
        }
        return printStatement;
    }

    public static EmptyStatement getEmptyStatement() {
        if(Objects.isNull(emptyStatement)) {
            emptyStatement = new EmptyStatement(getTokenExecutor());
        }
        return emptyStatement;
    }

    public static BlockStatement getBlockStatement() {
        if(Objects.isNull(blockStatement)) {
            blockStatement = new BlockStatement(getTokenExecutor());
        }
        return blockStatement;
    }

    public static VariableStatement getVariableStatement() {
        if(Objects.isNull(variableStatement)) {
            variableStatement = new VariableStatement(getTokenExecutor());
        }
        return variableStatement;
    }

    public static IfStatement getIfStatement() {
        if(Objects.isNull(ifStatement)) {
            ifStatement = new IfStatement(getTokenExecutor());
        }
        return ifStatement;
    }

    public static WhileStatement getWhileStatement() {
        if(Objects.isNull(whileStatement)) {
            whileStatement = new WhileStatement(getTokenExecutor());
        }
        return whileStatement;
    }

    public static BreakStatement getBreakStatement() {
        if(Objects.isNull(breakStatement)) {
            breakStatement = new BreakStatement(getTokenExecutor());
        }
        return breakStatement;
    }

    public static ContinueStatement getContinueStatement() {
        if(Objects.isNull(continueStatement)) {
            continueStatement = new ContinueStatement(getTokenExecutor());
        }
        return continueStatement;
    }

    public static ExpressionStatement getExpressionStatement() {
        if(Objects.isNull(expressionStatement)) {
            expressionStatement = new ExpressionStatement(getTokenExecutor());
        }
        return expressionStatement;
    }

    public static PrimaryExpression getPrimaryExpression() {
        if(Objects.isNull(primaryExpression)) {
            primaryExpression = new PrimaryExpression(getTokenExecutor());
        }
        return primaryExpression;
    }

    public static MultiplicativeExpression getMultiplicativeExpression() {
        if(Objects.isNull(multiplicativeExpression)) {
            multiplicativeExpression = new MultiplicativeExpression(getTokenExecutor());
        }
        return multiplicativeExpression;
    }

    public static AdditiveExpression getAdditiveExpression() {
        if(Objects.isNull(additiveExpression)) {
            additiveExpression = new AdditiveExpression(getTokenExecutor());
        }
        return additiveExpression;
    }

    public static RelationalExpression getRelationalExpression() {
        if(Objects.isNull(relationalExpression)) {
            relationalExpression = new RelationalExpression(getTokenExecutor());
        }
        return relationalExpression;
    }

    public static EqualityExpression getEqualityExpression() {
        if(Objects.isNull(equalityExpression)) {
            equalityExpression = new EqualityExpression(getTokenExecutor());
        }
        return equalityExpression;
    }

    public static LogicalANDExpression getLogicalANDExpression() {
        if (Objects.isNull(logicalANDExpression)) {
            logicalANDExpression = new LogicalANDExpression(getTokenExecutor());
        }
        return logicalANDExpression;
    }

    public static LogicalORExpression getLogicalORExpression() {
        if(Objects.isNull(logicalORExpression)) {
            logicalORExpression = new LogicalORExpression(getTokenExecutor());
        }
        return logicalORExpression;
    }

    public static AssignmentExpression getAssignmentExpression() {
        if(Objects.isNull(assignmentExpression)) {
            assignmentExpression = new AssignmentExpression(getTokenExecutor());
        }
        return assignmentExpression;
    }

    public static ParanthesizedExpression getParanthesizedExpression() {
        if(Objects.isNull(paranthesizedExpression)) {
            paranthesizedExpression = new ParanthesizedExpression(getTokenExecutor());
        }
        return paranthesizedExpression;
    }

    public static IdentifierExpression getIdentifierExpression() {
        if(Objects.isNull(identifierExpression)) {
            identifierExpression = new IdentifierExpression(getTokenExecutor());
        }
        return identifierExpression;
    }

    public static StringLiteral getStringLiteral() {
        if(Objects.isNull(stringLiteral)) {
            stringLiteral = new StringLiteral(getTokenExecutor());
        }
        return stringLiteral;
    }

    public static NumericLiteral getNumericLiteral() {
        if(Objects.isNull(numericLiteral)) {
            numericLiteral = new NumericLiteral(getTokenExecutor());
        }
        return numericLiteral;
    }

    public static NullLiteral getNullLiteral() {
        if(Objects.isNull(nullLiteral)) {
            nullLiteral = new NullLiteral(getTokenExecutor());
        }
        return nullLiteral;
    }

    public static BooleanLiteral getBooleanLiteral() {
        if(Objects.isNull(booleanLiteral)) {
            booleanLiteral = new BooleanLiteral(getTokenExecutor());
        }
        return booleanLiteral;
    }

    public static Program getProgram() {
        if(Objects.isNull(program)) {
            program = new Program();
        }
        return program;
    }
}
