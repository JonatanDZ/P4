package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpretGamerulesPositionPieceTest {

    @Test
    void interpretGamerulesPositionPieceIsOfTypeBexp() {
        String input = "board(3,3); gamerules position piece {occupied(3,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertInstanceOf(BexpNode.class, state.g);
    }

    @Test
    void interpretGamerulesPositionPieceIsTrue() {
        String input = "board(3,3); gamerules position piece {occupied(1,3)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        interpreter.state.beta.put(new Position(1, 3), "X");


        assertTrue(interpreter.execBexp(state.g));
    }

    @Test
    void interpretGamerulesPositionPieceIsFalse() {
        String input = "board(3,3); gamerules position piece {occupied(2,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertFalse(interpreter.execBexp(state.g));
    }

}
