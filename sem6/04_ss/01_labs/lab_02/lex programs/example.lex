%option noyywrap

%{
#include <stdio.h>
%}

%%
"hello"   printf("Hello World\n");
.         printf("Unrecognized character: %s\n", yytext);
%%

int main() {
    yylex();
    return 0;
}
