%{ 
#include <stdio.h>
int lines = 0, characters = 0, words = 0, tabs = 0, spaces = 0;
%}

%% 
[\n] { lines++; characters += yyleng; }
[ \t] { tabs++; characters += yyleng; }
[ ] { spaces++; characters += yyleng; }
[^\n\t ]+ { words++; characters += yyleng; }
. { characters += yyleng; }

%%

int yywrap() { return 1; }

// driver code
int main() {
    extern FILE *yyin, *yyout;
    yyin = fopen("Input.txt", "r");

    yylex();

    printf("Number of lines: %d\n", lines);
    printf("Number of characters: %d\n", characters);
    printf("Number of words: %d\n", words);
    printf("Number of tabs: %d\n", tabs);
    printf("Number of spaces: %d\n", spaces);

    return 0;
}
