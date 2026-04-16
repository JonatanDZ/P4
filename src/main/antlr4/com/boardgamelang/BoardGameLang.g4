grammar BoardGameLang;

program : def comp* EOF;

def : def SEMI
    | BOARD LPAR NUM COMMA NUM RPAR
    ;

gameRule :  PLAYER IDENT HAS NUM PIECE IDENT
        ;

comp : stmt (SEMI stmt)* SEMI
     ;

stmt : PLACE PIECE IDENT AT pos
           | ASSERT IDENT LPAR bexp RPAR
           | gameRule
           ;

bexp : OCCUPIED pos
     ;

pos : LPAR NUM COMMA NUM RPAR
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