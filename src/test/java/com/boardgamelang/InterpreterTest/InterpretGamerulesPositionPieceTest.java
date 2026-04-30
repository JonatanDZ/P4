package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class InterpretGamerulesPositionPieceTest {

    @Test
    void interpretGamerulesPositionPieceBexp() {
        String input = "board(3,3); gamerules position piece {occupied(3,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = new Interpreter().run(programNode);

        assertInstanceOf(BexpNode.class, state.g);
    }

    @Test
    void visitGamerulesExpectsOccupiedAndOccupied() {
        String input = "board(7,9); gamerules position piece {occupied(2,5) and occupied(3,5)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = new Interpreter().run(programNode);

        assertInstanceOf(BexpNode.class, state.g);
    }

    @Test
    void visitGamerulesExpectsOccupiedOrOccupied() {
        String input = "board(9,12); gamerules position piece {occupied(1,7) or occupied(4,9)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = new Interpreter().run(programNode);

        assertInstanceOf(BexpNode.class, state.g);
    }

    @Test
    void visitGamerulesExpectsOccupiedAndOccupiedOrOccupied() {
        String input = "board(13,10); gamerules position piece {occupied(1,7) and occupied(3,7) or occupied(4,9)};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = new Interpreter().run(programNode);

        assertInstanceOf(BexpNode.class, state.g);
    }

}
