package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.NotNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
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

        assertEquals(3, coordinates.pos.x);
        assertEquals(2, coordinates.pos.y);
    }
}
