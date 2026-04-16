// Generated from com/boardgamelang/BoardGameLang.g4 by ANTLR 4.13.2
package com.boardgamelang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BoardGameLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BoardGameLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BoardGameLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BoardGameLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BoardGameLangParser#def_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef_(BoardGameLangParser.Def_Context ctx);
	/**
	 * Visit a parse tree produced by {@link BoardGameLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(BoardGameLangParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link BoardGameLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(BoardGameLangParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BoardGameLangParser#pos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPos(BoardGameLangParser.PosContext ctx);
}