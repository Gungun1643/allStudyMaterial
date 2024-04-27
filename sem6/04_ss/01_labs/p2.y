%{
#include <stdio.h>
int yylex(void);
void yyerror(const char *s);
%}

%token A B EOL

%%
input: EOL | input line
line: 'a' line 'b' { printf("Valid string: a^nb\n"); }
    | EOL { yyerror("Invalid string: a^nb\n"); }
    ;
%%

void yyerror(const char *s) {
    printf("%s \n", s);
}

int main(void) {
    printf("Enter the string : \n");
    yyparse();
    printf("--------\n");
    return 0;
}
