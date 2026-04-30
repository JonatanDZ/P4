grammar BoardGameLang;

program : def comp* EOF;

def : BOARD pos SEMI     # BoardDef
    ;

gameRule : PLAYER IDENT HAS NUM PIECE IDENT  # PlayerHasPieceGameRule
         | WIN WHEN POSITIONS LBRAC bexp RBRAC         # WinWhenPositionsGameRule
         ;

comp : stmt (SEMI stmt)* SEMI ;

stmt : PLACE PIECE IDENT AT pos              # PlacePieceAtStmt
     | ASSERT IDENT LPAR bexp RPAR           # AssertStmt
     | gameRule                               # GameRuleStmt
     ;

bexp : OCCUPIED pos          # OccupiedBexp
     | bexp AND bexp         # AndBexp
     | bexp OR bexp          # OrBexp
     ;

aexp : COUNT LPAR IDENT RPAR                  # CountAexp
     ;

pos : LPAR NUM COMMA NUM RPAR                 # Position
    ;

dir : LEFT                                    # LeftDir
    | RIGHT                                   # RightDir
    | UP                                      # UpDir
    | DOWN                                    # DownDir
    ;

strexp : PIECE pos                              # PieceStrexp
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
OR : 'or';
AND : 'and';
LEFT     : 'left'     ;
RIGHT    : 'right'    ;
UP       : 'up'       ;
DOWN     : 'down'     ;
WIN : 'win' ;
WHEN : 'when' ;
POSITIONS : 'positions' ;
COUNT    : 'count'    ;

NUM : [0-9]+ ;
IDENT   : [a-zA-Z] [a-zA-Z0-9]* ;
WS      : [ \t\r\n]+ -> skip ;