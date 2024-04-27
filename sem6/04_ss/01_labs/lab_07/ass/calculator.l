%{
#include "calculator.tab.h" // Include the correct header file
#include <stdlib.h> // Include standard library for atoi function
extern int yylval; // Declaration of yylval
int yylex();
void yyerror(const char *);
%}

DIGIT       [0-9]
%%
{DIGIT}+    { yylval = atoi(yytext); return NUMBER; }
[-+*/\(\)\n]    { return yytext[0]; }
[ \t]           ; /* skip whitespace */
.               { yyerror("Invalid character"); }
%%

int yywrap(void) {
    return 1;
}
