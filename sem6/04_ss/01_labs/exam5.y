%{
#include <stdio.h>
%}

%token A B NEWLINE
%token <count> COUNT_A COUNT_B

%%

start: lines
    | /* Empty input */
    ;

lines: lines line
    | line
    ;

line: COUNT_A COUNT_B NEWLINE { if ($1 >= 5 && $2 == 1) printf("Valid: A^%d B\n", $1); else printf("Invalid\n"); }
    ;

%%

int main() {
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}
