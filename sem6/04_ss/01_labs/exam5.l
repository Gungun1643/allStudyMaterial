%{
#include "y.tab.h"
%}

%%
"A" { yylval.count = 1; return A; }
"B" { yylval.count = 1; return B; }
\n { return NEWLINE; }
[ \t] ;  /* Ignore whitespace */
. ; /* Ignore any other characters */

%%

int yywrap() {
    return 1;
}
