package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.gamerule.PlayerHasPieceNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class VisitPlayerHasPieceGameRuleTest {

    @Test
    void visitPlayerHasPieceGameRuleReturnsPlayerIdentAndAmountAndPieceIdent() {
        String input = "player alice has 3 piece X";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PlayerHasPieceNode node = (PlayerHasPieceNode) builder.visit(parser.gameRule());

        assertEquals("alice", node.playerIdent);
        assertEquals(3, node.n);
        assertEquals("X", node.pieceIdent);
    }
}
