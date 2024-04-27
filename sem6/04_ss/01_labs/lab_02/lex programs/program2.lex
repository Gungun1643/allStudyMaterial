%{ 
#include <stdio.h>
int lines=0, characters=0, words=0,tabs=0,spaces=0;
%}


%% 
[\n] {lines++; characters+=yyleng; }
[ \t] { spaces++; characters+=yyleng; }
[^\t] { tabs++; characters+=yyleng; }
[^\n\t ]+ { words++; characters+=yyleng; printf("Word %d: %s", words, yytext); }
 
%%

int yywrap() {return 1;}
//after inputting press => ctrl+d

//driver code 
int main(){
    extern FILE *yyin, *yyout;



    /* yyin => points to the file input.txt and opens in the read mode.*/
    yyin = fopen("Input.txt" ,"r");

if (yyin == NULL) {
        fprintf(stderr, "Error opening file.\n");
        return 1;
    } 


    yylex();
    printf("------------\n");

    printf("Number of lines : %d\n", lines);
    printf("Number of characters : %d\n", characters);
    printf("Number of words : %d\n", words);
    printf("Number of tabs : %d\n", tabs);
    printf("Number of spaces : %d\n", spaces);
    return 0;
}
