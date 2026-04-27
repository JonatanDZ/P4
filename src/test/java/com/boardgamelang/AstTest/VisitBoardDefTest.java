package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitBoardDefTest {
    @Test
    void visitBoardDefReturnsWidthAndHeight() {
        String input = "board(3,3);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        BoardNode node = (BoardNode) builder.visit(parser.def());

        assertEquals(3, node.pos.x);
        assertEquals(3, node.pos.y);
    }
}
