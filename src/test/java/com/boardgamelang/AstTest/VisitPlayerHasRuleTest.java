package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.gamerule.PlayerXHasNPieceYNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class VisitPlayerHasRuleTest {

    @Test
    void visitPlayerHasRuleReturnsPlayerXhasNPieceYNode() {
        String input = "player alice has 3 piece X";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PlayerXHasNPieceYNode node = (PlayerXHasNPieceYNode) builder.visit(parser.gameRule());

        assertEquals("alice", node.playerIdent);
        assertEquals(3, node.n);
        assertEquals("X", node.pieceIdent);
    }
}
