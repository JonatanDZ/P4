package com.boardgamelang.AST.gamerule;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.stmt.StmtNode;

public class DrawWhenGlobalNode extends GameRuleNode{
    public final BexpNode BexpInput;

    public DrawWhenGlobalNode(BexpNode BexpInput){
        this.BexpInput = BexpInput;
    }
}
