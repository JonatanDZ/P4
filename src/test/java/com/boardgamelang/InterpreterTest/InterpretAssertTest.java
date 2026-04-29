package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpretAssertTest {
    @Test
    void assertTestStmtSetsStateTTrue() {
        // populate beta with position, so occupied returns true
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        String input = "board(3,3); assert testTrue (occupied(1,1));";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = interp.run(programNode);

        assertEquals(true, state.t.get("testTrue"));
    }

    @Test
    void assertTestStmtSetsStateTFalse() {
        // populate beta with position, so occupied returns false
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(2, 2), "X");

        String input = "board(3,3); assert testFalse (occupied(1,1));";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        ProgramNode programNode = (ProgramNode) builder.visit(parser.program());
        State state = interp.run(programNode);

        assertEquals(false, state.t.get("testFalse"));
    }
}
