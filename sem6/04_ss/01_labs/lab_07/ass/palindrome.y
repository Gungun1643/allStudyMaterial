%{
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "palindrome.tab.h"
int yylex(void); // Explicit declaration for yylex
void yyerror(const char *); // Explicit declaration for yyerror
%}

%token STRING

%%
palindrome_check: STRING {
    char *str = $1;
    int len = strlen(str);
    int is_palindrome = 1;
    for (int i = 0; i < len / 2; ++i) {
        if (str[i] != str[len - i - 1]) {
            is_palindrome = 0;
            break;
        }
    }
    if (is_palindrome)
        printf("The given string is a palindrome.\n");
    else
        printf("The given string is not a palindrome.\n");
};

%%

int main() {
    printf("Enter a string to check if it's a palindrome:\n");
    yyparse();
    return 0;
}

void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
    exit(1);
}
