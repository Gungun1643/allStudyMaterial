%{
#include <stdio.h>
int yylex(void); // Define or declare yylex function
void yyerror(const char *s); // Define or declare yyerror function
%}


%token PLUS MULTIPLY VARIABLE

%%

S: S S '*'   { $$ = $1 * $2; }
 | S S '+'   { $$ = $1 + $2; }
 | VARIABLE   { $$ = $1; }
 ;

%%

int main() {
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    printf("Error: %s\n", s);
}

int yywrap() {
    return 1;
}
