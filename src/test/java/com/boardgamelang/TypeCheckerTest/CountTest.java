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

public class CountTest {

    @Test
    void pieceNotDeclared() {
        String input = "board(3,3); player red has 2 piece knight; draw when global {count(jack) == 9};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        // Assert that the typeChecker throws exception since the counted piece is not declared
        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());

    }
}
