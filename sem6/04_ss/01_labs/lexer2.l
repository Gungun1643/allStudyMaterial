%{
#include "y.tab.h"
%}

%%
"a" { return 'a'; }
"b" { return 'b'; }
\n  { return NL; }
.    { /* Ignore all other characters */ }
%%

int yywrap(void) {
    return 1;
}
