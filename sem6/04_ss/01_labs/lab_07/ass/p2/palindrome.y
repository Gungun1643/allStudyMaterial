%{
#include<stdio.h>
#include<stdlib.h>
%}

%%
[0-9]+ {
    int num = atoi(yytext);  // Convert matched number string to integer
    int rev = 0;
    int temp = num;
    while (temp != 0) {
        rev = rev * 10 + (temp % 10);
        temp /= 10;
    }
    if (rev == num)
        printf("PALI\n");
    else
        printf("NO\n");
}
. ;  // Discard any other characters
%%

int main() {
    printf("Enter a number to check if it's a palindrome:\n");
    yylex();
    return 0;
}
