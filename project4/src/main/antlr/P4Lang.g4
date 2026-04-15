grammar P4Lang;

definition : board ;

board : grid ;

grid : '(' value ',' value ')' ;

value : NUM ;

NUM : [0-9]+ ;
SPACES : [ \t] -> skip ;