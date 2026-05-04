package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.BoardGameLangParser;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class VisitAssertStmtTest {

    //Ensure that the AST gets the correct values from assert, when going into occupied
    @Test
    void visitAssertStmtReturnsIdentAndPos() {
        String input = "assert foo (occupied((1,2)));";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AssertNode node = (AssertNode) builder.visit(parser.stmt());

        assertEquals("foo", node.ident);
        OccupiedNode occupied = (OccupiedNode) node.bexp; // Go into occupiedNode and exstract the values
        PositionNode pos = (PositionNode) occupied.pos;
        assertEquals(1, pos.x);
        assertEquals(2, pos.y);
    }

    // Ensure that Assert should hold a bexp
    @Test
    void visitAssertStmtExpectsBexp() {
        String input = "assert foo (occupied((1,2)));";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AssertNode node = (AssertNode) builder.visit(parser.stmt());

        assertInstanceOf(BexpNode.class, node.bexp);
    }
}
