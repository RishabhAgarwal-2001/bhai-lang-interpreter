package com.schrodinger.parser.statement;

import com.schrodinger.parser.ASTNode;
import com.schrodinger.parser.DependencyModule;
import com.schrodinger.parser.TokenExecutor;
import com.schrodinger.tokenizer.Token;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Statement {
    protected TokenExecutor tokenExecutor;
    public abstract ASTNode getStatement();
    public static Statement getStatementImpl(final Token lookaheadToken) {
        return switch (lookaheadToken.getTokenType()) {
            case BOL_BHAI_TYPE -> DependencyModule.getPrintStatement();
            case SEMI_COLON_TYPE -> DependencyModule.getEmptyStatement();
            case OPEN_CURLY_BRACE_TYPE -> DependencyModule.getBlockStatement();
            case BHAI_YE_HAI_TYPE -> DependencyModule.getVariableStatement();
            case AGAR_BHAI -> DependencyModule.getIfStatement();
            case JAB_TAK_BHAI -> DependencyModule.getWhileStatement();
            case BAS_KAR_BHAI -> DependencyModule.getBreakStatement();
            case AGLA_DEKH_BHAI -> DependencyModule.getContinueStatement();
            default -> DependencyModule.getExpressionStatement();
        };
    }
}
