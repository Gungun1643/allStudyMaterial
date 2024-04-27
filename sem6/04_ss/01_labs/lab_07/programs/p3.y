%{
#include<stdio.h>
#include<stdlib.h>
int yyerror(char *msg)
{
printf("Invalid string\n");
exit(0);
}
%}
%token A B C
%%
R : S {printf("Valid string\n");}
;
S : S S B
| S S A
| C
;
%%
int main()
{
printf("Enter the string: ");
yyparse();
return 0;
}
