%{
#include "y.tab.h"
%}

%%
"a" { yylval = 0; return A; }
"b" { yylval = 0; return B; }
\n  { return EOL; }
.    { /* Ignore all other characters */ }
%%

int yywrap(void) {
    return 1;
}
