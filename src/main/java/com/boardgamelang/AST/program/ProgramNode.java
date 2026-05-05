package com.boardgamelang.AST.program;


import com.boardgamelang.AST.Node;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.gamerule.GameRuleNode;
import com.boardgamelang.AST.stmt.StmtNode;

import java.util.ArrayList;
import java.util.List;

public final class ProgramNode extends Node {
    public final DefNode defnode;
    public final List<StmtNode> stmtNodes;
    public final List<GameRuleNode> gameRuleNodes;

    public ProgramNode(DefNode defnode, List<StmtNode> stmtNodes, List<GameRuleNode> gameRuleNodes) {
        this.defnode = defnode;
        this.gameRuleNodes = gameRuleNodes;
        this.stmtNodes = stmtNodes;
    }
}
