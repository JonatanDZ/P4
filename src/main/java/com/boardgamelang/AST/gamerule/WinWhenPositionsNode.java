package com.boardgamelang.AST.gamerule;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.pos.PosNode;

public final class WinWhenPositionsNode extends GameRuleNode {
    public final PosNode pos;
    public final BexpNode bexp;

    public WinWhenPositionsNode(PosNode pos, BexpNode bexp) {
        this.pos = pos;
        this.bexp = bexp;
    }
}
