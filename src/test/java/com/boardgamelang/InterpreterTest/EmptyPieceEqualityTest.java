package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmptyPieceEqualityTest {

    // piece(emptyCell) returns null. Equality treats null on either side as "not equal",
    // so piece((1,1)) == piece((1,2)) on two empty cells is cleanly false instead of an NPE.
    // This removes the need for `occupied(a) and occupied(b) and ...` guards around
    // every chain of piece(...) == piece(...) in win conditions.
    @Test
    void emptyCellEqualityIsFalseNotCrash() {
        String input =
                "board(3,3); "
              + "player A has 1 piece X; "
              + "gamerules position piece {!occupied(position)}; "
              + "win when positions {piece((1,1)) == piece((1,2))}; "
              + "place piece X at (3,3);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        State state = new Interpreter().run(program);

        assertFalse(state.sigma.containsKey("win"));
    }
}
