package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.aexp.NumNode;
import com.boardgamelang.AST.bexp.EqualityNode;
import com.boardgamelang.interpreter.Interpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpreterEqualsTest {

    @Test
    public void checkEqualsNode() {
        Interpreter interp = new Interpreter();
        EqualityNode node = new EqualityNode(new NumNode(3), new NumNode(3));
        assertTrue(interp.execBexp(node));
    }

    @Test
    public void checkEqualsNodeFails() {
        Interpreter interp = new Interpreter();
        EqualityNode node = new EqualityNode(new NumNode(5), new NumNode(3));
        assertFalse(interp.execBexp(node));
    }
}
