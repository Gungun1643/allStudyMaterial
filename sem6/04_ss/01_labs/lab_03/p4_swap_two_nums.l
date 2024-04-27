%{
#include <stdio.h>
#include <stdlib.h>
int num1, num2;
%}

%option noyywrap

%%
[0-9]+      { return atoi(yytext);  }
"exit"      { return 0; }
\n          { /* Ignore newline characters */ }
.           { /* Ignore other characters */ }
%%

int main() {
    

    printf("Enter the first number: ");
     num1  = yylex();
   

    printf("Enter the second number: ");
    num2 =yylex();

    // Swap without a temporary variable
    num1 = num1 ^ num2;
    num2 = num1 ^ num2;
    num1 = num1 ^ num2;

    printf("Swapped numbers (without temp): %d, %d\n", num1, num2);

    return 0;
}
