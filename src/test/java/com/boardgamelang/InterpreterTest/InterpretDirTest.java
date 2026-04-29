package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.direction.DownNode;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.direction.RightNode;
import com.boardgamelang.AST.direction.UpNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpretDirTest {
    @Test
    void execDirUpDecreasesRow() {
        Interpreter interp = new Interpreter();

        Position result = interp.execDir(new UpNode());

        assertEquals(new Position(-1, 0), result);
    }

    @Test
    void execDirDownIncreasesRow() {
        Interpreter interp = new Interpreter();

        Position result = interp.execDir(new DownNode());

        assertEquals(new Position(1, 0), result);
    }

    @Test
    void execDirRightIncreasesColumn() {
        Interpreter interp = new Interpreter();

        Position result = interp.execDir(new RightNode());

        assertEquals(new Position(0, 1), result);
    }

    @Test
    void execDirLeftDecreasesColumn() {
        Interpreter interp = new Interpreter();

        Position result = interp.execDir(new LeftNode());

        assertEquals(new Position(0, -1), result);
    }
}
