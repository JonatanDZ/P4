package com.boardgamelang.AST.gamerule;

import com.boardgamelang.AST.bexp.BexpNode;

public final class GamerulesPositionPieceNode extends GameRuleNode {
    // It needs to be of the bexp type, since gamerules takes s boolean expression
    public final BexpNode bexp;

    public GamerulesPositionPieceNode(BexpNode bexp) {this.bexp = bexp;}
}
