package com.boardgamelang.TypeCheckerTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.typechecker.TypeChecker;
import com.boardgamelang.typechecker.TypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GamerulePositionPieceTest {
    @Test
    public void gamerulePositionPieceDeclaredCorrectTest() {
        String input = "board(8,8);" +
                "player y has 4 piece donkey;" +
                "gamerules position piece {!occupied((3,4))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));
    }

    @Test
    public void gamerulePositionPieceDeclaredTwiceTest() {
        String input = "board(8,8);" +
                "player y has 4 piece donkey;" +
                "gamerules position piece {!occupied((3,4))};" +
                "gamerules position piece {!occupied((3,6))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());

    }
}
