package com.boardgamelang.AST.program;


import com.boardgamelang.AST.Node;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.stmt.StmtNode;

import java.util.List;

public final class ProgramNode extends Node {
    public final DefNode defnode;
    public final List<StmtNode> stmtNodes;

    public ProgramNode(DefNode defnode, List<StmtNode> stmtNodes) {
        this.defnode = defnode;
        this.stmtNodes = stmtNodes;
    }
}
