package com.boardgamelang.AST.program;


import com.boardgamelang.AST.Node;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.stmt.StmtNode;

import java.util.List;

public final class ProgramNode extends Node {
    public final List<DefNode> defNodes;
    public final List<StmtNode> stmtNodes;

    public ProgramNode(List<DefNode> defNodes, List<StmtNode> stmtNodes) {
        this.defNodes = defNodes;
        this.stmtNodes = stmtNodes;
    }
}
