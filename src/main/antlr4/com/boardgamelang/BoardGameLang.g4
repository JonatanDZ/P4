grammar BoardGameLang;

program : def comp* EOF;

def : BOARD pos SEMI     # BoardDef
    ;

gameRule : PLAYER IDENT HAS NUM PIECE IDENT  # PlayerHasPieceGameRule
         ;

comp : stmt (SEMI stmt)* SEMI ;

stmt : PLACE PIECE IDENT AT pos              # PlacePieceAtStmt
     | ASSERT IDENT LPAR bexp RPAR           # AssertStmt
     | gameRule                               # GameRuleStmt
     ;

aexp : NUM                                    # NumAexp
     ;

bexp : OCCUPIED pos                           # OccupiedBexp
     ;

pos : LPAR NUM COMMA NUM RPAR                 # Position
    | OFFSET pos dir NUM                     # OffsetPos
    ;

dir : LEFT                                    # LeftDir
    | RIGHT                                   # RightDir
    | UP                                      # UpDir
    | DOWN                                    # DownDir
    ;
/*
orExp : andExp ('or' andExp)*
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
COMMA    : ','        ;
OCCUPIED : 'occupied' ;
ASSERT   : 'assert'   ;
PLAYER   : 'player'   ;
HAS      : 'has'      ;
LEFT     : 'left'     ;
RIGHT    : 'right'    ;
UP       : 'up'       ;
DOWN     : 'down'     ;
OFFSET     : 'offset'     ;

// int (not double): board game values are inherently discrete — positions, counts, offsets are all integer-valued
NUM : [0-9]+ ;
IDENT   : [a-zA-Z] [a-zA-Z0-9]* ;
WS      : [ \t\r\n]+ -> skip ;