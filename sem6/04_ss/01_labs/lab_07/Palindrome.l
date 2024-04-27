%{
#include "Palindrome.tab.h"
%}

%%
[a-zA-Z]+   { yylval = strdup(yytext); return STRING; }
.           ;

%%

int yywrap(void) {
    return 1;
}
