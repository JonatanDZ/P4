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

public class DeclarePiece {

    @Test
    void declaredPiecePassesTypeChecker() {
        String input = "board(3,3); player red has 2 piece knight; place piece knight at (1,1);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        // Assert that the typeChecker does not throw any exceptions since the piece is declared
        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(program));

    }

    @Test
    void undeclaredPieceFailsTypeChecker() {
        String input = "board(3,3); player red has 2 piece knight; place piece dragon at (1,1);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        // Assert that the typeChecker throws an exception, that the piece is not declared.
        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());
    }

    @Test
    void noDeclarationFailsTypeChecker() {
        String input = "board(3,3); place piece knight at (1,1);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        ProgramNode program = (ProgramNode) new AstBuilder().visit(parser.program());

        // Assert that the typeChecker throws an exception, that the piece is not declared.
        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());
    }
}
