%{
#include <stdio.h>
%}

digit           [0-9]
date            ({digit}{4})\-({digit}{2})\-({digit}{2})
date2           ({digit}{2})\/({digit}{2})\/({digit}{4})
date3           ({digit}{2})\-({digit}{2})\-({digit}{4})

%%

{date} {
    printf("Date format: YYYY-MM-DD\n");
    // printf("Year: %s, Month: %s, Day: %s\n", yytext, yytext+5, yytext+8);
}

{date2} {
    printf("Date format: DD/MM/YYYY\n");
    // printf("Day: %s, Month: %s, Year: %s\n", yytext, yytext+3, yytext+6);
}

{date3} {
    printf("Date format: MM-DD-YYYY\n");
    // printf("Month: %s, Day: %s, Year: %s\n", yytext, yytext+3, yytext+6);
}

.|\n    ;

%%

int yywrap() {
    return 1;
}

int main(int argc, char* argv[]) {
    yyin = stdin;
    yylex();
    return 0;
}
