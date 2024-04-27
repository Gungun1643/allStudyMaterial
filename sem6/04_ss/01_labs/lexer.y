%{
#include <stdio.h>
#include <stdlib.h>
%}

%token a b NL

%%

input: expr NL { printf("Valid string: a^n b where n >= 5\n"); }
     | NL { yyerror("Invalid string: a^n b where n >= 5\n"); }
     ;

expr: a expr b
    | a a expr b
    | a a a expr b
    | a a a a expr b
    | a a a a a b
    ;

%%

void yyerror(const char *s) {
    printf("%s", s);
}

int main(void) {
    printf("Enter a string in the form a^n b where n >= 5: ");
    yyparse();
    return 0;
}
