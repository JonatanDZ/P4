package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitAndBexpTest {

    @Test
    public void visitAndBexpReturnsAndNode() {
        String input = "occupied(3,2) and occupied(2,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AndNode node = (AndNode) builder.visit(parser.bexp());

        assertInstanceOf(AndNode.class, node);
    }

    @Test
    public void visitAndBexpHasCorrectOperands() {
        String input = "occupied(3,2) and occupied(2,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AndNode node = (AndNode) builder.visit(parser.bexp());
        OccupiedNode left = (OccupiedNode) node.left;

        assertEquals(3, ((PositionNode) left.pos).x);
        assertEquals(2, ((PositionNode) left.pos).y);

        OccupiedNode right = (OccupiedNode) node.right;
        assertEquals(2, ((PositionNode) right.pos).x);
        assertEquals(2, ((PositionNode) right.pos).y);
    }

    @Test
    public void visitMultipleAndsPreservesPrecedence() {
        // occupied(3,2) and occupied(2,2) and occupied(1,1)
        // should parse as: (occupied(3,2) and occupied(2,2)) and occupied(1,1)
        //        AndNode
        //        /      \
        //    AndNode   occupied(1,1)
        //    /      \
        //  occ(3,2) occ(2,2)

        String input = "occupied(3,2) and occupied(2,2) and occupied(1,1)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AndNode root = (AndNode) builder.visit(parser.bexp());

        // right of root should be the last occupied
        OccupiedNode right = (OccupiedNode) root.right;
        assertEquals(1, ((PositionNode) right.pos).x);
        assertEquals(1, ((PositionNode) right.pos).y);

        // left of root should be another AndNode (the inner pair)
        AndNode innerAnd = (AndNode) root.left;
        OccupiedNode innerLeft = (OccupiedNode) innerAnd.left;
        OccupiedNode innerRight = (OccupiedNode) innerAnd.right;

        assertEquals(3, ((PositionNode) innerLeft.pos).x);
        assertEquals(2, ((PositionNode) innerLeft.pos).y);
        assertEquals(2, ((PositionNode) innerRight.pos).x);
        assertEquals(2, ((PositionNode) innerRight.pos).y);
    }

    @Test
    public void visitAndOrBuildsOrNodeWithAndOnLeft() {
        // occupied(3,2) and occupied(2,2) or occupied(1,1)
        // and has higher precedence than or, so parses as:
        // (occupied(3,2) and occupied(2,2)) or occupied(1,1)
        //        OrNode
        //        /      \
        //    AndNode   occupied(1,1)
        //    /      \
        //  occ(3,2) occ(2,2)

        String input = "occupied(3,2) and occupied(2,2) or occupied(1,1)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        OrNode root = (OrNode) builder.visit(parser.bexp());

        OccupiedNode right = (OccupiedNode) root.right;
        assertEquals(1, ((PositionNode) right.pos).x);
        assertEquals(1, ((PositionNode) right.pos).y);

        AndNode innerAnd = (AndNode) root.left;
        OccupiedNode innerLeft = (OccupiedNode) innerAnd.left;
        OccupiedNode innerRight = (OccupiedNode) innerAnd.right;

        assertEquals(3, ((PositionNode) innerLeft.pos).x);
        assertEquals(2, ((PositionNode) innerLeft.pos).y);
        assertEquals(2, ((PositionNode) innerRight.pos).x);
        assertEquals(2, ((PositionNode) innerRight.pos).y);
    }
}
