package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitOrBexpTest {

    @Test
    public void visitOrBexpReturnsOrNode() {
        String input = "occupied(3,2) or occupied(2,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OrNode node = (OrNode) builder.visit(parser.bexp());

        assertInstanceOf(OrNode.class, node);
    }

    @Test
    public void visitOrBexpHasCorrectOperands() {
        String input = "occupied(3,2) or occupied(2,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OrNode node = (OrNode) builder.visit(parser.bexp());
        OccupiedNode left = (OccupiedNode) node.left;
        OccupiedNode right = (OccupiedNode) node.right;

        assertEquals(3, ((PositionNode) left.pos).x);
        assertEquals(2, ((PositionNode) left.pos).y);
        assertEquals(2, ((PositionNode) right.pos).x);
        assertEquals(2, ((PositionNode) right.pos).y);
    }

    @Test
    public void visitMultipleOrsPreservesPrecedence() {
        // occupied(3,2) or occupied(2,2) or occupied(1,1)
        // should parse as: (occupied(3,2) or occupied(2,2)) or occupied(1,1)
        //        OrNode
        //        /      \
        //    OrNode   occupied(1,1)
        //    /      \
        //  occ(3,2) occ(2,2)

        String input = "occupied(3,2) or occupied(2,2) or occupied(1,1)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OrNode root = (OrNode) builder.visit(parser.bexp());

        OccupiedNode right = (OccupiedNode) root.right;
        assertEquals(1, ((PositionNode) right.pos).x);
        assertEquals(1, ((PositionNode) right.pos).y);

        OrNode innerOr = (OrNode) root.left;
        OccupiedNode innerLeft = (OccupiedNode) innerOr.left;
        OccupiedNode innerRight = (OccupiedNode) innerOr.right;

        assertEquals(3, ((PositionNode) innerLeft.pos).x);
        assertEquals(2, ((PositionNode) innerLeft.pos).y);
        assertEquals(2, ((PositionNode) innerRight.pos).x);
        assertEquals(2, ((PositionNode) innerRight.pos).y);
    }

    @Test
    public void visitOrAndBuildsAndNodeWithOrOnLeft() {
        // occupied(3,2) or occupied(2,2) and occupied(1,1)
        // and has higher precedence than or, so parses as:
        // occupied(3,2) or (occupied(2,2) and occupied(1,1))
        //        OrNode
        //        /      \
        //  occ(3,2)   AndNode
        //              /      \
        //          occ(2,2) occ(1,1)

        String input = "occupied(3,2) or occupied(2,2) and occupied(1,1)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OrNode root = (OrNode) builder.visit(parser.bexp());

        OccupiedNode left = (OccupiedNode) root.left;
        assertEquals(3, ((PositionNode) left.pos).x);
        assertEquals(2, ((PositionNode) left.pos).y);

        AndNode innerAnd = (AndNode) root.right;
        OccupiedNode innerLeft = (OccupiedNode) innerAnd.left;
        OccupiedNode innerRight = (OccupiedNode) innerAnd.right;

        assertEquals(2, ((PositionNode) innerLeft.pos).x);
        assertEquals(2, ((PositionNode) innerLeft.pos).y);
        assertEquals(1, ((PositionNode) innerRight.pos).x);
        assertEquals(1, ((PositionNode) innerRight.pos).y);
    }
}