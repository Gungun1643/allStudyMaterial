%{
#include "Calculator.tab.h"
%}

%%
[0-9]+          { yylval = atoi(yytext); return NUMBER; }
[-+*/()\n]      { return yytext[0]; }
[ \t]           ; /* ignore whitespace */
.               ;

%%

int yywrap(void) {
    return 1;
}
