package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.NotNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitNotBexpTest {


    @Test
    public void VisitNotBexpReturnsNotNode() {
        String input = "!occupied(3,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        NotNode node = (NotNode) builder.visit(parser.bexp());

        assertInstanceOf(NotNode.class, node);
    }

    @Test
    public void VisitNotBexpHasCorrectOperands() {
        String input = "!occupied(3,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        NotNode node = (NotNode) builder.visit(parser.bexp());
        OccupiedNode coordinates = (OccupiedNode) node.b;
        PositionNode pos = (PositionNode) coordinates.pos;

        assertEquals(3, pos.x);
        assertEquals(2, pos.y);
    }
}
