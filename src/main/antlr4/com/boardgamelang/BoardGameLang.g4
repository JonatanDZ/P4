grammar BoardGameLang;

program : def* stmt EOF;

def : def SEMI
    | BOARD LPAR NUM COMMA NUM RPAR
    ;

stmt : stmt SEMI stmt
     | PLACE PIECE IDENT AT pos
     | ASSERT IDENT LPAR bexp RPAR
     | PLAYER IDENT HAS NUM PIECE IDENT
     | bexp
     ;

bexp :
     | OCCUPIED LPAR pos RPAR
     ;

pos : LPAR NUM COMMA NUM RPAR
    ;


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