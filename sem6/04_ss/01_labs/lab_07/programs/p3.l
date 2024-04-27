%{
#include<stdio.h>
#include "y.tab.h"
extern int yylval;
%}
%%
[*] {yylval = 1;return A;}
[+] {yylval = 2;return B;}
a {yylval = 3;return C;}
\n {yylval = 4;return 0;}
%%
int yywrap()
{
return 1;
}
