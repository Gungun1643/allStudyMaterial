%{
#include <stdio.h>
#include <stdlib.h>
int yylex();
void yyerror(const char*);
%}

%token NUMBER

%left '+' '-'
%left '*' '/'

%%

ArithmeticExpression: E {
    printf("\nResult = %d\n", $1);
    exit(0); // Exit the program after printing the result
};

E: E '+' E {
    $$ = $1 + $3;
}
| E '-' E {
    $$ = $1 - $3;
}
|
'(' E ')' {
    $$ = $2;
}
| E '*' E {
    $$ = $1 * $3;
}
| E '/' E {
    if ($3 != 0)
        $$ = $1 / $3;
    else {
        yyerror("Division by zero");
        $$ = 0;
    }
}
| NUMBER {
    $$ = $1;
}
;

%%

int main() {
    printf("Enter an arithmetic expression:\n");
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    printf("Error: %s\n", s);
}
