package com.boardgamelang.AST.gamerule;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.stmt.StmtNode;

public class DrawWhenGlobalNode extends GameRuleNode{
    public final BexpNode bexp;

    public DrawWhenGlobalNode(BexpNode bexp){
        this.bexp = bexp;
    }
}
