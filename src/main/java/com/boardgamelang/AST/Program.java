package com.boardgamelang.AST;

import java.util.List;

public final class Program extends Node {
    public final List<Def> defs;
    public final List<Stmt> stmts;   // or whatever your top-level shape is
    public Program(List<Def> defs, List<Stmt> stmts) {
        this.defs = defs;
        this.stmts = stmts;
    }
}
