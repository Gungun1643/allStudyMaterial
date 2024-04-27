%{
#include<stdio.h>
int lc=0, sc=0, tc=0, ch=0, wc=0; 
%}


%% 
[\n] { lc++; ch+= yyleng; }
[ \t] { sc++; ch+=yyleng; }
[^\t\n] { tc++; ch+= yyleng; }
[^\t\n]+ { wc++; ch+=yyleng; }
%% 

int yywrap(){
    return 0;
}

int main(){
    printf("Enter the sentence. ");
    yylex();
    printf("Number of lines :  %d\n", lc);
    printf("Number of spaces: %d\n", sc);
    printf("Number of tabs, words, chars: %d, %d, %d \n", tc, wc, ch);

    return 0;
}
