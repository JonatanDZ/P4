grammar BoardGameLang;

program : def compRule* comp* EOF;

def : BOARD pos SEMI     # BoardDef
    ;

compRule : gameRule (SEMI gameRule)* SEMI ;

gameRule : PLAYER IDENT HAS NUM PIECE IDENT  # PlayerHasPieceGameRule
         | GAMERULES POSITION PIECE LBRAC bexp RBRAC     # GamerulesPositionPieceGameRule
         | WIN WHEN POSITIONS LBRAC bexp RBRAC         # WinWhenPositionsGameRule
         | DRAW WHEN GLOBAL LBRAC bexp RBRAC # DrawWhenGlobalGameRule
         ;

comp : stmt (SEMI stmt)* SEMI ;

stmt : PLACE PIECE IDENT AT pos              # PlacePieceAtStmt
     | ASSERT IDENT LBRAC bexp RBRAC           # AssertStmt
     ;

bexp : NOT bexp                              # NotBexp
     | eqexp EQUALITY eqexp                  # EqualityBexp
     | OCCUPIED LPAR pos RPAR                # OccupiedBexp
     | bexp AND bexp                         # AndBexp
     | bexp OR bexp                          # OrBexp
     ;

eqexp : pos
      | aexp
      | strexp
      ;

aexp : COUNT LPAR IDENT RPAR                  # CountAexp
      | NUM                                    # NumAexp
     ;


pos : LPAR NUM COMMA NUM RPAR                    # Position
    | OFFSET LPAR pos COMMA dir COMMA NUM RPAR   # OffsetPos
    | POSITION                                   # PositionRef
    ;

dir : LEFT                                    # LeftDir
    | RIGHT                                   # RightDir
    | UP                                      # UpDir
    | DOWN                                    # DownDir
    ;

strexp : PIECE LPAR pos RPAR                    # PieceStrexp
     ;

/*orExp : andExp ('or' andExp)*
       ;

andExp : eqExp ('and' eqExp)*
        ;

eqExp : relExp ('==' relExp)*
       ;

relExp : plusExp (('<' | '<=') plusExp)*
        ;

plusExp : multExp (('+' | '-') multExp)*
         ;

multExp : notExp ('*' notExp)*
         ;

notExp : ('!' | '-')* term
        ;*/

/*term : IDENT
     | NUM
     | pos
     | 'true'
     | 'false'
*//*
     | LPAR aexp RPAR
*//*
     ;*/


PLACE    : 'place'    ;
DRAW     : 'draw'     ;
BOARD    : 'board'    ;
PIECE    : 'piece'    ;
AT       : 'at'       ;
SEMI     : ';'        ;
LPAR     : '('        ;
RPAR     : ')'        ;
LBRAC    : '{'        ;
RBRAC    : '}'        ;
COMMA    : ','        ;
OCCUPIED : 'occupied' ;
ASSERT : 'assert' ;
PLAYER : 'player' ;
HAS : 'has';
GAMERULES: 'gamerules';
POSITION: 'position';
GLOBAL : 'global' ;
OR : 'or';
AND : 'and';
NOT : '!';
LEFT     : 'left'     ;
RIGHT    : 'right'    ;
UP       : 'up'       ;
DOWN     : 'down'     ;
OFFSET   : 'offset'   ;
WIN : 'win' ;
WHEN : 'when' ;
POSITIONS : 'positions' ;
COUNT    : 'count'    ;
EQUALITY   : '=='   ;

// int (not double): board game values are inherently discrete — positions, counts, offsets are all integer-valued
NUM : [0-9]+ ;
IDENT   : [a-zA-Z] [a-zA-Z0-9]* ;
WS      : [ \t\r\n]+ -> skip ;