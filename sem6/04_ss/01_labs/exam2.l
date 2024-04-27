%{
#include <stdio.h>
#include <stdlib.h>
%}

digit           [0-9]
date            ({digit}{2})\-({digit}{2})\-({digit}{4})
date2           ({digit}{2})\/({digit}{2})\/({digit}{4})
date3           ({digit}{2})\-({digit}{2})\-({digit}{4})

%%

{date} {
    int month = atoi(yytext);
    int day = atoi(yytext+3);
    int year = atoi(yytext+6);

    if (month <= 12 && day <= 12) {
        printf("Date format: MM-DD-YYYY\n");
        printf("Month: %d, Day: %d, Year: %d\n", month, day, year);
    } else if (month <= 12 && year <= 12) {
        printf("Date format: MM-YYYY-DD\n");
        printf("Month: %d, Year: %d, Day: %d\n", month, year, day);
    } else if (day <= 12 && year <= 12) {
        printf("Date format: DD-YYYY-MM\n");
        printf("Day: %d, Year: %d, Month: %d\n", day, year, month);
    } else {
        printf("Date format: YYYY-MM-DD\n");
        printf("Year: %d, Month: %d, Day: %d\n", year, month, day);
    }
}

{date2} {
    printf("Date format: DD/MM/YYYY\n");
    printf("Day: %s, Month: %s, Year: %s\n", yytext, yytext+3, yytext+6);
}

{date3} {
    printf("Date format: MM-DD-YYYY\n");
    printf("Month: %s, Day: %s, Year: %s\n", yytext, yytext+3, yytext+6);
}

{date4} {
    printf("Date format: YYYY/MM/DD\n");
    printf("Year: %s, Month: %s, Day: %s\n", yytext, yytext+5, yytext+8);
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
