package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitPieceStrexpTest {
    @Test
    public void testVisitPieceStrexp() {
        String input = "piece(3,2);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PieceNode node = (PieceNode) builder.visit(parser.strexp());

        assertEquals(3, node.pos.x);
        assertEquals(2, node.pos.y);
    }

}
