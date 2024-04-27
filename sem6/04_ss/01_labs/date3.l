%{
#include <stdio.h>
#include <stdlib.h>
%}

int yywrap() {
    return 1;  
}

%%
[0-9]+-[0-9]+-[0-9]+ {
    int a, b, c;
    sscanf(yytext, "%d-%d-%d", &a, &b, &c);
    if (a <= 12 && b <= 12) {
        printf("Date format: mm-dd-yyyy\n");
    } else if (a <= 12) {
        printf("Date format: dd-mm-yyyy\n");
    } else {
        printf("Date format: yyyy-dd-mm\n");
    }
}
\n    { /* Ignore newlines */ }
.    { printf("Invalid input\n"); }
%%

int main(void) {
    yylex();
    return 0;
}
