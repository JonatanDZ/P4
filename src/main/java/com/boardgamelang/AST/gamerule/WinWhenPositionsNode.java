package com.boardgamelang.AST.gamerule;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.pos.PosNode;

public final class WinWhenPositionsNode extends GameRuleNode {
    public final BexpNode bexp;

    public WinWhenPositionsNode(BexpNode bexp) {
        this.bexp = bexp;
    }
}
