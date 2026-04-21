package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.BoardGameLangParser;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class VisitAssertStmtTest {

    @Test
    void visitAssertStmtReturnsAssertNode() {
        String input = "assert foo (occupied(1,2));";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AssertNode node = (AssertNode) builder.visit(parser.stmt());

        assertEquals("foo", node.ident);
    }
}
