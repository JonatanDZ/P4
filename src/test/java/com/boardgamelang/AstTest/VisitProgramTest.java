package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.StmtNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class VisitProgramTest {

    @Test
    void visitProgramReturnsProgramNode() {
        String input = "board(3,3); assert foo (occupied(1,2));";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode node = (ProgramNode) builder.visit(parser.program());

        assertEquals(1, node.defNodes.size());
        assertEquals(1, node.stmtNodes.size());

        BoardNode board = (BoardNode) node.defNodes.get(0);
        assertEquals(3, board.width);
        assertEquals(3, board.height);

        AssertNode stmt = (AssertNode) node.stmtNodes.get(0);
        assertEquals("foo", stmt.ident);

    }
}
