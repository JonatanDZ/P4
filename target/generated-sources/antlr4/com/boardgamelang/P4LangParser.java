// Generated from com/boardgamelang/P4Lang.g4 by ANTLR 4.13.2
package com.boardgamelang;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class P4LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PLACE=1, BOARD=2, PIECE=3, AT=4, SEMI=5, LPAR=6, RPAR=7, COMMA=8, OCCUPIED=9, 
		ASSERT=10, PLAYER=11, HAS=12, NUM=13, IDENT=14, WS=15;
	public static final int
		RULE_program = 0, RULE_def = 1, RULE_stmt = 2, RULE_bexp = 3, RULE_pos = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "def", "stmt", "bexp", "pos"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'place'", "'board'", "'piece'", "'at'", "';'", "'('", "')'", "','", 
			"'occupied'", "'assert'", "'player'", "'has'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PLACE", "BOARD", "PIECE", "AT", "SEMI", "LPAR", "RPAR", "COMMA", 
			"OCCUPIED", "ASSERT", "PLAYER", "HAS", "NUM", "IDENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "P4Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public P4LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TerminalNode EOF() { return getToken(P4LangParser.EOF, 0); }
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof P4LangVisitor ) return ((P4LangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(10);
					def(0);
					}
					} 
				}
				setState(15);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(16);
			stmt(0);
			setState(17);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefContext extends ParserRuleContext {
		public TerminalNode BOARD() { return getToken(P4LangParser.BOARD, 0); }
		public TerminalNode LPAR() { return getToken(P4LangParser.LPAR, 0); }
		public List<TerminalNode> NUM() { return getTokens(P4LangParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(P4LangParser.NUM, i);
		}
		public TerminalNode COMMA() { return getToken(P4LangParser.COMMA, 0); }
		public TerminalNode RPAR() { return getToken(P4LangParser.RPAR, 0); }
		public TerminalNode SEMI() { return getToken(P4LangParser.SEMI, 0); }
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof P4LangVisitor ) return ((P4LangVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		return def(0);
	}

	private DefContext def(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DefContext _localctx = new DefContext(_ctx, _parentState);
		DefContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_def, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(20);
			match(BOARD);
			setState(21);
			match(LPAR);
			setState(22);
			match(NUM);
			setState(23);
			match(COMMA);
			setState(24);
			match(NUM);
			setState(25);
			match(RPAR);
			setState(26);
			match(SEMI);
			}
			_ctx.stop = _input.LT(-1);
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DefContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_def);
					setState(28);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(29);
					match(SEMI);
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public TerminalNode PLACE() { return getToken(P4LangParser.PLACE, 0); }
		public TerminalNode PIECE() { return getToken(P4LangParser.PIECE, 0); }
		public List<TerminalNode> IDENT() { return getTokens(P4LangParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(P4LangParser.IDENT, i);
		}
		public TerminalNode AT() { return getToken(P4LangParser.AT, 0); }
		public PosContext pos() {
			return getRuleContext(PosContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(P4LangParser.ASSERT, 0); }
		public TerminalNode LPAR() { return getToken(P4LangParser.LPAR, 0); }
		public BexpContext bexp() {
			return getRuleContext(BexpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(P4LangParser.RPAR, 0); }
		public TerminalNode PLAYER() { return getToken(P4LangParser.PLAYER, 0); }
		public TerminalNode HAS() { return getToken(P4LangParser.HAS, 0); }
		public TerminalNode NUM() { return getToken(P4LangParser.NUM, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(P4LangParser.SEMI, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof P4LangVisitor ) return ((P4LangVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		return stmt(0);
	}

	private StmtContext stmt(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StmtContext _localctx = new StmtContext(_ctx, _parentState);
		StmtContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_stmt, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(36);
				match(PLACE);
				setState(37);
				match(PIECE);
				setState(38);
				match(IDENT);
				setState(39);
				match(AT);
				setState(40);
				pos();
				}
				break;
			case 2:
				{
				setState(41);
				match(ASSERT);
				setState(42);
				match(IDENT);
				setState(43);
				match(LPAR);
				setState(44);
				bexp();
				setState(45);
				match(RPAR);
				}
				break;
			case 3:
				{
				setState(47);
				match(PLAYER);
				setState(48);
				match(IDENT);
				setState(49);
				match(HAS);
				setState(50);
				match(NUM);
				setState(51);
				match(PIECE);
				setState(52);
				match(IDENT);
				}
				break;
			case 4:
				{
				setState(53);
				bexp();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StmtContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stmt);
					setState(56);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(57);
					match(SEMI);
					setState(58);
					stmt(6);
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BexpContext extends ParserRuleContext {
		public TerminalNode OCCUPIED() { return getToken(P4LangParser.OCCUPIED, 0); }
		public TerminalNode LPAR() { return getToken(P4LangParser.LPAR, 0); }
		public PosContext pos() {
			return getRuleContext(PosContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(P4LangParser.RPAR, 0); }
		public BexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).enterBexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).exitBexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof P4LangVisitor ) return ((P4LangVisitor<? extends T>)visitor).visitBexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BexpContext bexp() throws RecognitionException {
		BexpContext _localctx = new BexpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bexp);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				match(OCCUPIED);
				setState(66);
				match(LPAR);
				setState(67);
				pos();
				setState(68);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PosContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(P4LangParser.LPAR, 0); }
		public List<TerminalNode> NUM() { return getTokens(P4LangParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(P4LangParser.NUM, i);
		}
		public TerminalNode COMMA() { return getToken(P4LangParser.COMMA, 0); }
		public TerminalNode RPAR() { return getToken(P4LangParser.RPAR, 0); }
		public PosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).enterPos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof P4LangListener ) ((P4LangListener)listener).exitPos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof P4LangVisitor ) return ((P4LangVisitor<? extends T>)visitor).visitPos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PosContext pos() throws RecognitionException {
		PosContext _localctx = new PosContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_pos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(LPAR);
			setState(73);
			match(NUM);
			setState(74);
			match(COMMA);
			setState(75);
			match(NUM);
			setState(76);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return def_sempred((DefContext)_localctx, predIndex);
		case 2:
			return stmt_sempred((StmtContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean def_sempred(DefContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean stmt_sempred(StmtContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000fO\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0005\u0000\f\b\u0000\n\u0000\f\u0000\u000f\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001\u001f\b\u0001\n\u0001\f\u0001\"\t\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u00027\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002<\b\u0002\n\u0002\f\u0002?\t\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003G\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0000\u0002\u0002\u0004\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000"+
		"P\u0000\r\u0001\u0000\u0000\u0000\u0002\u0013\u0001\u0000\u0000\u0000"+
		"\u00046\u0001\u0000\u0000\u0000\u0006F\u0001\u0000\u0000\u0000\bH\u0001"+
		"\u0000\u0000\u0000\n\f\u0003\u0002\u0001\u0000\u000b\n\u0001\u0000\u0000"+
		"\u0000\f\u000f\u0001\u0000\u0000\u0000\r\u000b\u0001\u0000\u0000\u0000"+
		"\r\u000e\u0001\u0000\u0000\u0000\u000e\u0010\u0001\u0000\u0000\u0000\u000f"+
		"\r\u0001\u0000\u0000\u0000\u0010\u0011\u0003\u0004\u0002\u0000\u0011\u0012"+
		"\u0005\u0000\u0000\u0001\u0012\u0001\u0001\u0000\u0000\u0000\u0013\u0014"+
		"\u0006\u0001\uffff\uffff\u0000\u0014\u0015\u0005\u0002\u0000\u0000\u0015"+
		"\u0016\u0005\u0006\u0000\u0000\u0016\u0017\u0005\r\u0000\u0000\u0017\u0018"+
		"\u0005\b\u0000\u0000\u0018\u0019\u0005\r\u0000\u0000\u0019\u001a\u0005"+
		"\u0007\u0000\u0000\u001a\u001b\u0005\u0005\u0000\u0000\u001b \u0001\u0000"+
		"\u0000\u0000\u001c\u001d\n\u0002\u0000\u0000\u001d\u001f\u0005\u0005\u0000"+
		"\u0000\u001e\u001c\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000\u0000\u0000"+
		" \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\u0003\u0001"+
		"\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000#$\u0006\u0002\uffff\uffff"+
		"\u0000$%\u0005\u0001\u0000\u0000%&\u0005\u0003\u0000\u0000&\'\u0005\u000e"+
		"\u0000\u0000\'(\u0005\u0004\u0000\u0000(7\u0003\b\u0004\u0000)*\u0005"+
		"\n\u0000\u0000*+\u0005\u000e\u0000\u0000+,\u0005\u0006\u0000\u0000,-\u0003"+
		"\u0006\u0003\u0000-.\u0005\u0007\u0000\u0000.7\u0001\u0000\u0000\u0000"+
		"/0\u0005\u000b\u0000\u000001\u0005\u000e\u0000\u000012\u0005\f\u0000\u0000"+
		"23\u0005\r\u0000\u000034\u0005\u0003\u0000\u000047\u0005\u000e\u0000\u0000"+
		"57\u0003\u0006\u0003\u00006#\u0001\u0000\u0000\u00006)\u0001\u0000\u0000"+
		"\u00006/\u0001\u0000\u0000\u000065\u0001\u0000\u0000\u00007=\u0001\u0000"+
		"\u0000\u000089\n\u0005\u0000\u00009:\u0005\u0005\u0000\u0000:<\u0003\u0004"+
		"\u0002\u0006;8\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001"+
		"\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u0005\u0001\u0000\u0000"+
		"\u0000?=\u0001\u0000\u0000\u0000@G\u0001\u0000\u0000\u0000AB\u0005\t\u0000"+
		"\u0000BC\u0005\u0006\u0000\u0000CD\u0003\b\u0004\u0000DE\u0005\u0007\u0000"+
		"\u0000EG\u0001\u0000\u0000\u0000F@\u0001\u0000\u0000\u0000FA\u0001\u0000"+
		"\u0000\u0000G\u0007\u0001\u0000\u0000\u0000HI\u0005\u0006\u0000\u0000"+
		"IJ\u0005\r\u0000\u0000JK\u0005\b\u0000\u0000KL\u0005\r\u0000\u0000LM\u0005"+
		"\u0007\u0000\u0000M\t\u0001\u0000\u0000\u0000\u0005\r 6=F";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}