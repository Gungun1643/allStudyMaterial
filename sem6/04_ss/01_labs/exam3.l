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
