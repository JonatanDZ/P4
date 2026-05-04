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

public class InterpretWinWhenPositionsTest {
    @Test
    void winWhenPositionsSetsWinCondition() {
        String input = "board(3,3); win when positions {occupied((56652,1)) and occupied((223,23)) or occupied((323,232)) and occupied((323,132))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertNotNull(state.w);
        assertInstanceOf(BexpNode.class, state.w);
    }

    @Test
    void winConditionEvaluatesToFalseWhenPositionsNotOccupied() {
        String input = "board(3,3); win when positions {occupied((1,1)) and occupied((2,2))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertFalse(interpreter.execBexp(state.w));
    }

    @Test
    void winConditionEvaluatesToTrueWhenPositionsOccupied() {
        String input = "board(3,3); win when positions {occupied((1,1)) and occupied((2,2))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        interpreter.state.beta.put(new Position(1, 1), "X");
        interpreter.state.beta.put(new Position(2, 2), "X");

        assertTrue(interpreter.execBexp(state.w));
    }
}
