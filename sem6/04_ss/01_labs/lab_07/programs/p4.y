%{
#include<stdlib.h>
#include<stdio.h>
int yylex();
void yyerror(char *s);
%}
%token Z O
%%
r : s {printf("\nACCEPTED\n");}
;
s : n
| Z a
| O b
;
a : n a
| Z
;
b : n b
| O
;
n : Z
| O
;
%%
int main()
{
printf("Enter the string: \n");
yyparse();
}
void yyerror(char *s)
{
fprintf(stdout,"\nNOT ACCEPTED\n");
}