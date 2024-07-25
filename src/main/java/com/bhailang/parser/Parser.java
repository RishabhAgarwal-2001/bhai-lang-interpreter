package com.bhailang.parser;

public class Parser {
    private final Program program = new Program();

    public ASTNode parse(final String stringToTokenize) {
        DependencyModule.getTokenizer().initTokenizer(stringToTokenize);
        DependencyModule.getTokenExecutor().setLookahead(DependencyModule.getTokenizer().getNextToken());
        return program.getProgram();
    }
}
