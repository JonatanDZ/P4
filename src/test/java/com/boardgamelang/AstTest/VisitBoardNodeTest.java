package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitBoardNodeTest {
    @Test
    void visitBoardNode() {
        String input = "board(3,3);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        BoardNode node = (BoardNode) builder.visit(parser.def());

        assertEquals(3, node.width);
        assertEquals(3, node.height);
    }
}
