%{
#include <stdio.h>
#include <string.h>
int yylex();
void yyerror(const char*);
int isPalindrome(char*);
%}

%token STRING

%%

start: STRING {
    if (isPalindrome($1))
        printf("\"%s\" is a palindrome.\n", $1);
    else
        printf("\"%s\" is not a palindrome.\n", $1);
}
;

%%

int main() {
    printf("Enter a string to check if it's a palindrome or not:\n");
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    printf("Error: %s\n", s);
}

int isPalindrome(char *str) {
    int len = strlen(str);
    for (int i = 0; i < len / 2; i++) {
        if (str[i] != str[len - i - 1])
            return 0; // Not a palindrome
    }
    return 1; // Palindrome
}
