package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitPosNodeTest {
    @Test
    void VisitPosNodeTestReturnsPosNode() {
        String input = "(1,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PosNode node = (PosNode) builder.visit(parser.pos());

        assertEquals(1, node.x);
        assertEquals(2, node.y);
    }
}
