%{
#include<stdio.h>
char num,temp[100];
int rev=0;
char arr[100];
%}
%%
. scanf("%s",arr); temp=arr; while(temp!=0){rev=rev*10+(temp%10); temp/=10;} if(rev==num) printf("PALI"); else printf("NO"); clear();
%%
clear(){rev=0;}
main(){yylex();}
