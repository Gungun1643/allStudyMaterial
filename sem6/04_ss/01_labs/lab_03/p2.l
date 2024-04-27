%{
#include <stdio.h>
#include <stdlib.h>
    #include<math.h>
    double yylval=1;  // Declare yylval as a global variable

%}
%option noyywrap


DIGIT [0-9]
WS [ \t\n]+

%%
{DIGIT}+    { printf("Number: %s\n", yytext); }
{WS}        { /* Ignore whitespace */ }
"exit"      { exit(0); }
[+-]?[0-9]+ { printf("Number: %s\n", yytext);
yylval= yylval * yytext; }
[*/\n]      { printf("Operator: %s\n", yytext);
 }
.           { printf("Invalid character: %s\n", yytext); }

%%

int main() {
    yylex();
    printf("Simple Interest : ", yylval);
    return 0;
}
