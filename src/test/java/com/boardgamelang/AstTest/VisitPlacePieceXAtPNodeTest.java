package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitPlacePieceXAtPNodeTest {
    @Test
    void VisitPlacePieceXAtPNode() {


        ///  LAV TEST HER evt insp under:
        String input = "occupied(3,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OccupiedNode node = (OccupiedNode) builder.visit(parser.bexp());

        assertEquals(3, node.pos.x);
        assertEquals(2, node.pos.y);
    }
}