/*defination section*/
%{
    #include<stdio.h>
    #include<stdlib.h>
    #include<math.h>
    double yylval;  // Declare yylval as a global variable

%}

%option noyywrap

/*rules*/
%%
[0-9]+(\.[0-9]+)?[ \t]*("F"|"f"|"Fahrenheit"|"fahrenheit") {
    float fah= atof(yytext);
    float cel= (fah-32)*(5.0/9.0);
    printf(" %.2f Fahrenheit  is celsius :  %.2f \n",fah, cel);
    
}
 
"exit" {
    exit(0);
}

\n {
    return '\n';
}
 

%%
 

/*main program.*/
int main(){
    printf("Enter the temperature in fahrenheigt \n");
    yylex();
    
return 0;
}
/*
ECHO Write matched string

*/
