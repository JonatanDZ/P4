package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrecedenceTest {
    // without proper precedence the gamerule reads !(occupied(position) and !occupied(offset...)
    // this is definitely not what the user intended
    // proper precedence fixes this so it reads !occupied.. and !occupied.. as it should
    // it now parses as: (!occupied(position)) and (!occupied(offset position right 1)) which is correct
    @Test
    void itCanEvaluateAndAtomicallyWithNotBexps() {
        String program =
                "board(3,3);" +
                "player Black has 2 piece O;" +
                "gamerules position piece {!occupied(position) and !occupied(offset (position, right, 1))};" +
                "place piece O at (2,2);" +
                "assert placeAllowed {occupied((2,2))};" +
                "place piece O at (2,1);"
                ;
        BoardGameLangParser parser = ParseTreeHelper.createParser(program);
        ProgramNode ast = (ProgramNode) new AstBuilder().visit(parser.program());

        Interpreter interp = new Interpreter();
        RuntimeException ex = assertThrows(RuntimeException.class, () -> interp.run(ast));

        // the throw must be from gamerule rejection on move 2 not some other error
        assertTrue(ex.getMessage().contains("Game rule"), "wrong exception: " + ex.getMessage());

        // assertion ran before the throw and saved true
        assertEquals(true, interp.state.t.get("placeAllowed"));
    }
}
