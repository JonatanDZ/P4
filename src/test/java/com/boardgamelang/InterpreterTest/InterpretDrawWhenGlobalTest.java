package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;
import com.boardgamelang.AST.bexp.BexpNode;
import static org.junit.jupiter.api.Assertions.*;

public class InterpretDrawWhenGlobalTest {
    @Test
    void drawWhenGlobalSetDrawCondition(){
        String input = "board(5,5); player x has 2 piece knight; draw when global {occupied(2,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertInstanceOf(BexpNode.class, state.eta);
    }

    @Test
    void drawWhenGlobalEvaluatesToTrue() {
        String input = "board(5,5); draw when global {occupied(2,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        interpreter.state.beta.put(new Position(2, 2), "X");

        assertTrue(interpreter.execBexp(state.eta));
    }

    @Test
    void drawWhenGlobalEvaluatesToFalse() {
        String input = "board(5,5); draw when global {occupied(2,2)};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        Interpreter interpreter = new Interpreter();
        State state = interpreter.run(program);

        assertFalse(interpreter.execBexp(state.eta));
    }
}
