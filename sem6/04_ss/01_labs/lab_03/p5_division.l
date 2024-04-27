/*defination section*/
%{
    #include<stdio.h>
    #include<stdlib.h>
    #include<math.h> 
    int num,den;
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
    printf("Enter the value of the Numerator : \n");
    num = yylex();
    
    printf("\nEnter the value of the Denominator : \n");
    den =  yylex();
   
    if(den==0){
        printf("Division not possible");
    }else {
        float val= num/den;
        printf("The division operator results into : %.2lf", val);
    }
}
/*
ECHO Write matched string

*/
