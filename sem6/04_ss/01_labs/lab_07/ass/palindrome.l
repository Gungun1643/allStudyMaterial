%{
#include <string.h> // Include the header for strdup function
#include "palindrome.tab.h"
%}

%%
[a-zA-Z]+ { yylval.str = strdup(yytext); return STRING; }
.|\n     { return yytext[0]; }
%%

int yywrap(void) {
    return 1;
}
