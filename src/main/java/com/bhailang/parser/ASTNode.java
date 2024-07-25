package com.bhailang.parser;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ASTNode {
    private NodeType type;
    private List<ASTNode> body;
    private List<ASTNode> expressions;
    private ASTNode expression;
    private String operator;
    private String name;
    private ASTNode left;
    private ASTNode right;
    private String stringValue;
    private Double numberValue;
    private ASTNode id;
    private ASTNode init;
    private List<ASTNode> declarations;
    private ASTNode test;
    private ASTNode consequent;
    private List<ASTNode> alternates;
}
