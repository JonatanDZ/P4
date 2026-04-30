package com.boardgamelang.AstTest;

import com.boardgamelang.BoardGameLangParser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitEqualsNodes {

//    @Test
//    public void visitEqualsBexpNodeReturnsAexpNodes() {
//        // This test asserts that the node contains two Aexp on both sides of equality, count is an aexp
//        String input = "count(queen) == count(knight)";
//        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
//        AstBuilder builder = new AstBuilder();
//
//        EqualsAexpNode node = (EqualsAexpNode) builder.visit(parser.bexp());
//
//        assertInstanceOf(EqualsAexpNode.class, node);
//    }
//
//    @Test
//    public void visitEqualsBexpNodeFail() {
//        // This test asserts to fail, because the equality contains two different expressions
//        String input = "count(queen) == occupied(1,1)";
//        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
//        AstBuilder builder = new AstBuilder();
//
//        EqualsAexpNode node = (EqualsAexpNode) builder.visit(parser.bexp());
//
//        assertInstanceOf(EqualsAexpNode.class, node);
//    }
}
