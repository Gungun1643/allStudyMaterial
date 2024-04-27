/*defination section*/
%{
    #include<stdio.h>
    #include<stdlib.h>
    #include<math.h> 
float principle,rate,time;
%}

%option noyywrap

/*rules*/
%%
[0-9]+ {
    return atoi(yytext);
}
[ \t] ;
\n ;
. {return yytext[0];}
[0-9]+"."+[0-9]+ {
    double val= atof(yytext);
    return 1;
}
[0-9]+[Ee][+-]?[0-9]+ {
    double val= atof(yytext);
    return 1;
}
 

%%
 

/*main program.*/
int main(){
    printf("Enter the value of the P(principle) : \n");
    principle = yylex();
    
    printf("\nEnter the value of the R(rate) : \n");
    rate =  yylex();
    //double rate= yylval;
    printf("\nEnter the value of the T(Time) : \n");
    time = yylex();
    //double time=yylval;

    float simple_interest= (principle*rate*time)/100;
    printf("Simple Interest : %.2lf \n", simple_interest);
}
/*
ECHO Write matched string

*/
