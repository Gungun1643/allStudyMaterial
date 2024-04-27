%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
%}

%%

[0-9]+          {
    
                    double radius = atof(yytext);
                    double area = M_PI * pow(radius, 2);
                    printf("Area of the circle with radius %.2f is %.2f\n", radius, area);
                 }

[0-9]+"."[0-9]+ {
                    double radius = atof(yytext);
                    double area = M_PI * pow(radius, 2);
                    printf("Area of the circle with radius %.2f is %.2f\n", radius, area);
                 }

\n              { return '\n'; }
.               { return yytext[0]; }

%%

int main() {
    printf("Enter the radius");
    yylex();
    return 0;
}

int yywrap() {
    // Indicate to the lexer that no more input is available
    return 1;
}
