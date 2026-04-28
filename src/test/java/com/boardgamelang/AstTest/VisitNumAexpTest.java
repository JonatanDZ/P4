package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.aexp.NumNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitNumAexpTest {
    @Test
    void visitPosTestReturnsPos() {
        String input = "10";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        NumNode node = (NumNode) builder.visit(parser.aexp());

        assertEquals(10, node.n);
    }
}
