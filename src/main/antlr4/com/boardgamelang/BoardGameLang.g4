grammar BoardGameLang;

program : def comp* EOF;

def : BOARD LPAR NUM COMMA NUM RPAR SEMI     # BoardDef
    ;

gameRule : PLAYER IDENT HAS NUM PIECE IDENT  # PlayerHasRule
         ;

comp : stmt (SEMI stmt)* SEMI ;

stmt : PLACE PIECE IDENT AT pos              # PlaceStmt
     | ASSERT IDENT LPAR bexp RPAR           # AssertStmt
     | gameRule                               # GameRuleStmt
     ;

bexp : OCCUPIED pos                           # OccupiedBexp
     ;

pos : LPAR NUM COMMA NUM RPAR                 # Position
    ;

/*aexp : orExp
     ;

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


PLACE : 'place' ;
BOARD : 'board' ;
PIECE : 'piece' ;
AT    : 'at'    ;
SEMI  : ';'     ;
LPAR  : '('     ;
RPAR  : ')'     ;
COMMA : ','     ;
OCCUPIED : 'occupied' ;
ASSERT : 'assert' ;
PLAYER : 'player' ;
HAS : 'has';

NUM : [0-9]+ ;
IDENT   : [a-zA-Z] [a-zA-Z0-9]* ;
WS      : [ \t\r\n]+ -> skip ;