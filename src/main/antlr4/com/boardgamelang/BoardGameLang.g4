grammar BoardGameLang;

program : def_* stmt* EOF ;

def_    : 'board' '(' NUM ',' NUM ')'
        | 'piece' ID
        ;

stmt    : stmt ';' stmt
        | 'place' 'piece' ID 'at' pos
        | 'assert' '(' expr ')'
        ;

expr    : 'occupied' '(' pos ')' ;

pos     : '(' NUM ',' NUM ')' ;

NUM     : [0-9]+ ;
ID      : [a-zA-Z][a-zA-Z0-9_]* ;
WS      : [ \t\r\n]+ -> skip ;
