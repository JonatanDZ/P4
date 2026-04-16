// Generated from com/boardgamelang/BoardGameLang.g4 by ANTLR 4.13.2
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
public class BoardGameLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, NUM=11, ID=12, WS=13;
	public static final int
		RULE_program = 0, RULE_def_ = 1, RULE_stmt = 2, RULE_expr = 3, RULE_pos = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "def_", "stmt", "expr", "pos"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'board'", "'('", "','", "')'", "'piece'", "';'", "'place'", "'at'", 
			"'assert'", "'occupied'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NUM", 
			"ID", "WS"
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
	public String getGrammarFileName() { return "BoardGameLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BoardGameLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(BoardGameLangParser.EOF, 0); }
		public List<Def_Context> def_() {
			return getRuleContexts(Def_Context.class);
		}
		public Def_Context def_(int i) {
			return getRuleContext(Def_Context.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BoardGameLangVisitor ) return ((BoardGameLangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__4) {
				{
				{
				setState(10);
				def_();
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==T__8) {
				{
				{
				setState(16);
				stmt(0);
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(22);
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
	public static class Def_Context extends ParserRuleContext {
		public List<TerminalNode> NUM() { return getTokens(BoardGameLangParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BoardGameLangParser.NUM, i);
		}
		public TerminalNode ID() { return getToken(BoardGameLangParser.ID, 0); }
		public Def_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).enterDef_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).exitDef_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BoardGameLangVisitor ) return ((BoardGameLangVisitor<? extends T>)visitor).visitDef_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Def_Context def_() throws RecognitionException {
		Def_Context _localctx = new Def_Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_def_);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				match(T__0);
				setState(25);
				match(T__1);
				setState(26);
				match(NUM);
				setState(27);
				match(T__2);
				setState(28);
				match(NUM);
				setState(29);
				match(T__3);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				match(T__4);
				setState(31);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class StmtContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BoardGameLangParser.ID, 0); }
		public PosContext pos() {
			return getRuleContext(PosContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BoardGameLangVisitor ) return ((BoardGameLangVisitor<? extends T>)visitor).visitStmt(this);
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
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(35);
				match(T__6);
				setState(36);
				match(T__4);
				setState(37);
				match(ID);
				setState(38);
				match(T__7);
				setState(39);
				pos();
				}
				break;
			case T__8:
				{
				setState(40);
				match(T__8);
				setState(41);
				match(T__1);
				setState(42);
				expr();
				setState(43);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StmtContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stmt);
					setState(47);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(48);
					match(T__5);
					setState(49);
					stmt(4);
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
	public static class ExprContext extends ParserRuleContext {
		public PosContext pos() {
			return getRuleContext(PosContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BoardGameLangVisitor ) return ((BoardGameLangVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(T__9);
			setState(56);
			match(T__1);
			setState(57);
			pos();
			setState(58);
			match(T__3);
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
		public List<TerminalNode> NUM() { return getTokens(BoardGameLangParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(BoardGameLangParser.NUM, i);
		}
		public PosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).enterPos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGameLangListener ) ((BoardGameLangListener)listener).exitPos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BoardGameLangVisitor ) return ((BoardGameLangVisitor<? extends T>)visitor).visitPos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PosContext pos() throws RecognitionException {
		PosContext _localctx = new PosContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_pos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__1);
			setState(61);
			match(NUM);
			setState(62);
			match(T__2);
			setState(63);
			match(NUM);
			setState(64);
			match(T__3);
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
		case 2:
			return stmt_sempred((StmtContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stmt_sempred(StmtContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\rC\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0005\u0000\f\b\u0000\n\u0000\f\u0000\u000f\t\u0000\u0001\u0000"+
		"\u0005\u0000\u0012\b\u0000\n\u0000\f\u0000\u0015\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001!\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u00023\b\u0002\n\u0002\f\u00026\t"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0000\u0001\u0004\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000B"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0002 \u0001\u0000\u0000\u0000\u0004"+
		"-\u0001\u0000\u0000\u0000\u00067\u0001\u0000\u0000\u0000\b<\u0001\u0000"+
		"\u0000\u0000\n\f\u0003\u0002\u0001\u0000\u000b\n\u0001\u0000\u0000\u0000"+
		"\f\u000f\u0001\u0000\u0000\u0000\r\u000b\u0001\u0000\u0000\u0000\r\u000e"+
		"\u0001\u0000\u0000\u0000\u000e\u0013\u0001\u0000\u0000\u0000\u000f\r\u0001"+
		"\u0000\u0000\u0000\u0010\u0012\u0003\u0004\u0002\u0000\u0011\u0010\u0001"+
		"\u0000\u0000\u0000\u0012\u0015\u0001\u0000\u0000\u0000\u0013\u0011\u0001"+
		"\u0000\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0016\u0001"+
		"\u0000\u0000\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0016\u0017\u0005"+
		"\u0000\u0000\u0001\u0017\u0001\u0001\u0000\u0000\u0000\u0018\u0019\u0005"+
		"\u0001\u0000\u0000\u0019\u001a\u0005\u0002\u0000\u0000\u001a\u001b\u0005"+
		"\u000b\u0000\u0000\u001b\u001c\u0005\u0003\u0000\u0000\u001c\u001d\u0005"+
		"\u000b\u0000\u0000\u001d!\u0005\u0004\u0000\u0000\u001e\u001f\u0005\u0005"+
		"\u0000\u0000\u001f!\u0005\f\u0000\u0000 \u0018\u0001\u0000\u0000\u0000"+
		" \u001e\u0001\u0000\u0000\u0000!\u0003\u0001\u0000\u0000\u0000\"#\u0006"+
		"\u0002\uffff\uffff\u0000#$\u0005\u0007\u0000\u0000$%\u0005\u0005\u0000"+
		"\u0000%&\u0005\f\u0000\u0000&\'\u0005\b\u0000\u0000\'.\u0003\b\u0004\u0000"+
		"()\u0005\t\u0000\u0000)*\u0005\u0002\u0000\u0000*+\u0003\u0006\u0003\u0000"+
		"+,\u0005\u0004\u0000\u0000,.\u0001\u0000\u0000\u0000-\"\u0001\u0000\u0000"+
		"\u0000-(\u0001\u0000\u0000\u0000.4\u0001\u0000\u0000\u0000/0\n\u0003\u0000"+
		"\u000001\u0005\u0006\u0000\u000013\u0003\u0004\u0002\u00042/\u0001\u0000"+
		"\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u00005\u0005\u0001\u0000\u0000\u000064\u0001\u0000\u0000"+
		"\u000078\u0005\n\u0000\u000089\u0005\u0002\u0000\u00009:\u0003\b\u0004"+
		"\u0000:;\u0005\u0004\u0000\u0000;\u0007\u0001\u0000\u0000\u0000<=\u0005"+
		"\u0002\u0000\u0000=>\u0005\u000b\u0000\u0000>?\u0005\u0003\u0000\u0000"+
		"?@\u0005\u000b\u0000\u0000@A\u0005\u0004\u0000\u0000A\t\u0001\u0000\u0000"+
		"\u0000\u0005\r\u0013 -4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}