package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.stmt.PlacePieceXAtPNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitPlacePieceXAtPNodeTest {
    @Test
    void VisitPlacePieceXAtPNode() {
        String input = "place piece X at (3,2);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PlacePieceXAtPNode node = (PlacePieceXAtPNode) builder.visit(parser.stmt());

        assertEquals("X", node.ident);
        assertEquals(3, node.pos.x);
        assertEquals(2, node.pos.y);
    }
}