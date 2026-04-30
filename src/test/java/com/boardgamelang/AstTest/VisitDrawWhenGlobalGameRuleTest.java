package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.gamerule.DrawWhenGlobalNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitDrawWhenGlobalGameRuleTest {

    @Test
    void instanceOfBexpAnd(){
        String input = "draw when global {occupied(2,3) and occupied(3,3) or occupied(4,4)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        DrawWhenGlobalNode node = (DrawWhenGlobalNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }
}
