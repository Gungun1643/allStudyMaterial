%{
#include <stdio.h>
%}

%%
[0-9]{2}-[0-9]{2}-[0-9]{4} {
    char day[3], month[3], year[5];
    sscanf(yytext, "%2s-%2s-%4s", day, month, year);
    printf("Day: %s, Month: %s, Year: %s (dd-mm-yyyy)\n", day, month, year);
}
[0-9]{2}-[0-9]{2}-[0-9]{4} {
    char month[3], day[3], year[5];
    sscanf(yytext, "%2s-%2s-%4s", month, day, year);
    printf("Month: %s, Day: %s, Year: %s (mm-dd-yyyy)\n", month, day, year);
}
[0-9]{4}-[0-9]{2}-[0-9]{2} {
    char year[5], month[3], day[3];
    sscanf(yytext, "%4s-%2s-%2s", year, month, day);
    printf("Year: %s, Month: %s, Day: %s (yyyy-mm-dd)\n", year, month, day);
}
\n    { /* Ignore newlines */ }
.    { printf("Invalid date format\n"); }
%%

int yywrap(void) {
    return 1;
}

int main(void) {
    yylex();
    return 0;
}
