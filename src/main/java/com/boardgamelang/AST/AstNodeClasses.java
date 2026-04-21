package com.boardgamelang.AST;

import org.w3c.dom.Node;

import java.util.List;

public final class Program extends Node {
    // definition
    public BoardDefNode board;
    public List<StmtNode> stmts;

    // constructor
}
