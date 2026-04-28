package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
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
    public void visitAndBexpIsOccupiedWithCorrectPos() {
        String input = "occupied(3,2) and occupied(2,2)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AndNode node = (AndNode) builder.visit(parser.bexp());
        OccupiedNode left = (OccupiedNode) node.left;

        assertEquals(3, left.pos.x);
        assertEquals(2, left.pos.y);

        OccupiedNode right = (OccupiedNode) node.right;
        assertEquals(2, right.pos.x);
        assertEquals(2, right.pos.y);
    }

    @Test
    public void visitMultipleAndIsLeftAssociative() {
        // occupied(3,2) and occupied(2,2) and occupied(1,1)
        // should parse as: (occupied(3,2) and occupied(2,2)) and occupied(1,1)

        //        AndNode          <--- root
        //        /      \
        //    AndNode   occupied(1,1)   <--- right is the last operand
        //    /      \
        //  occ(3,2) occ(2,2)

        String input = "occupied(3,2) and occupied(2,2) and occupied(1,1)";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        AndNode root = (AndNode) builder.visit(parser.bexp());

        // right of root should be the last occupied
        OccupiedNode right = (OccupiedNode) root.right;
        assertEquals(1, right.pos.x);
        assertEquals(1, right.pos.y);

        // left of root should be another AndNode (the inner pair)
        AndNode innerAnd = (AndNode) root.left;
        OccupiedNode innerLeft = (OccupiedNode) innerAnd.left;
        OccupiedNode innerRight = (OccupiedNode) innerAnd.right;

        assertEquals(3, innerLeft.pos.x);
        assertEquals(2, innerLeft.pos.y);
        assertEquals(2, innerRight.pos.x);
        assertEquals(2, innerRight.pos.y);
    }


}
