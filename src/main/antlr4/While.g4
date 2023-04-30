grammar While;

prog: stm+ ;

stm: VAR ':=' aexp
    | 'skip'
    | stm ';' stm
    | 'if' bexp 'then' stm 'else' stm
    | 'while' bexp 'do' stm
    | '(' stm ')'
    ;

aexp: multExpr (('+' | '-') multExpr)*
    ;

multExpr
    : atom ('*' atom)*
    ;

atom: INT
    | VAR
    | '(' aexp ')'
    ;

bexp: 'true'
    | 'false'
    | aexp '=' aexp
    | aexp '<=' aexp
    | NOT '(' bexp ')'
    | bexp AND bexp
    | '(' bexp ')'
    ;

INT: [0-9]+ ;
NOT: 'not' ;
AND: 'and' ;
VAR: [a-zA-Z]+ ;
WS: [ \t\n]+ -> skip ;