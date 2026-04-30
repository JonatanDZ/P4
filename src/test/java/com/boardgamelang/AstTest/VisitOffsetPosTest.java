package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.direction.RightNode;
import com.boardgamelang.AST.direction.UpNode;
import com.boardgamelang.AST.pos.OffsetNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitOffsetPosTest {
    @Test
    void visitOffsetPosTestReturnsExpectedValues() {
        String input = "offset (1,2) left 1";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OffsetNode node = (OffsetNode) builder.visit(parser.pos());
        PositionNode pos = (PositionNode) node.pos;

        assertEquals(1, pos.x);
        assertEquals(2, pos.y);
        assertInstanceOf(LeftNode.class, node.dir);
        assertEquals(1, node.n);
    }

    @Test
    void visitOffsetPosNestedReturnsNestedOffsetNode() {
        String input = "offset offset (1,1) up 2 right 3";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OffsetNode outer = (OffsetNode) builder.visit(parser.pos());

        assertInstanceOf(OffsetNode.class, outer.pos);
        assertInstanceOf(RightNode.class, outer.dir);
        assertEquals(3, outer.n);

        OffsetNode inner = (OffsetNode) outer.pos;
        PositionNode innerBase = (PositionNode) inner.pos;
        assertEquals(1, innerBase.x);
        assertEquals(1, innerBase.y);
        assertInstanceOf(UpNode.class, inner.dir);
        assertEquals(2, inner.n);
    }
}
