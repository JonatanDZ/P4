package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.AST.bexp.EqualsNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitEqualsNodeTest {

    @Test
    public void visitEqualsBexpNodeReturnsAexpNodes() {
        // This test asserts that the node contains a count node on each side, which is an aexp
        String input = "count(queen) == count(knight)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        EqualsNode node = (EqualsNode) builder.visit(parser.bexp());


        assertInstanceOf(CountNode.class, node.left);
        assertInstanceOf(CountNode.class, node.right);
    }
}
