package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.direction.DownNode;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.direction.RightNode;
import com.boardgamelang.AST.direction.UpNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitDirTest {
    @Test
    void visitLeftDirReturnsLeftNode() {
        BoardGameLangParser parser = ParseTreeHelper.createParser("left");
        AstBuilder builder = new AstBuilder();

        Object node = builder.visit(parser.dir());

        assertInstanceOf(LeftNode.class, node);
    }

    @Test
    void visitRightDirReturnsRightNode() {
        BoardGameLangParser parser = ParseTreeHelper.createParser("right");
        AstBuilder builder = new AstBuilder();

        Object node = builder.visit(parser.dir());

        assertInstanceOf(RightNode.class, node);
    }

    @Test
    void visitUpDirReturnsUpNode() {
        BoardGameLangParser parser = ParseTreeHelper.createParser("up");
        AstBuilder builder = new AstBuilder();

        Object node = builder.visit(parser.dir());

        assertInstanceOf(UpNode.class, node);
    }

    @Test
    void visitDownDirReturnsDownNode() {
        BoardGameLangParser parser = ParseTreeHelper.createParser("down");
        AstBuilder builder = new AstBuilder();

        Object node = builder.visit(parser.dir());

        assertInstanceOf(DownNode.class, node);
    }
}
