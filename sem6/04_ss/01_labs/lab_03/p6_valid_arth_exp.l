/*defination section*/
%{
    #include<stdio.h>
    #include<stdlib.h>
    #include<string.h>
    #include<math.h>
    /*valid => assuming that it is valid expression.*/
    /*top => it is position of the stack pointer.*/
    int operators_count=0, operands_count=0, valid=1, top=-1, l=0,j=0;
    char operands[10][10], operators[10][10], stack[100];
/*determine the size of the stack.*/
%}

%option noyywrap

/*rules*/
%%
"(" {
    top++;
    /*this is to push the parenthesis.*/
    stack[top] = '(';
}
"{" {
    top++;
    /*this is to push the parenthesis.*/
    stack[top] = '{';
}
"[" {
    top++;
    /*this is to push the parenthesis.*/
    stack[top] = '[';
}
")" {
    if(stack[top]!='('){
        valid=0;
    }else if (operands_count>0 && (operands_count- operators_count)!=1){
        /*this is when the stack top has the ( but need to check for the count of the operands
         and the operators between these pair of parentheses.*/
        valid=0;
        /*this means operands_count>0 => atleast one operand must be there ...empty parenthesis is handled here.*/

        /*(operands_count- operators_count)!=1 => this means that there should be only one 
        less operator thna the count of the operands.*/
    }else {
        /*this is the valid set of parenthesis that we have found.*/
        top--;
        /*this is to prepare for the new set of parenthesis.*/
        operands_count=1;
        operators_count=0;
    }
}
"}" {
    if(stack[top]!='{'){
        valid=0;
    }else if (operands_count>0 && (operands_count- operators_count)!=1){
        /*this is when the stack top has the ( but need to check for the count of the operands
         and the operators between these pair of parentheses.*/
        valid=0;
        /*this means operands_count>0 => atleast one operand must be there ...empty parenthesis is handled here.*/

        /*(operands_count- operators_count)!=1 => this means that there should be only one 
        less operator thna the count of the operands.*/
    }else {
        /*this is the valid set of parenthesis that we have found.*/
        top--;
        /*this is to prepare for the new set of parenthesis.*/
        operands_count=1;
        operators_count=0;
    }
}
"]" {
    if(stack[top]!='['){
        valid=0;
    }else if (operands_count>0 && (operands_count- operators_count)!=1){
        /*this is when the stack top has the ( but need to check for the count of the operands
         and the operators between these pair of parentheses.*/
        valid=0;
        /*this means operands_count>0 => atleast one operand must be there ...empty parenthesis is handled here.*/

        /*(operands_count- operators_count)!=1 => this means that there should be only one 
        less operator thna the count of the operands.*/
    }else {
        /*this is the valid set of parenthesis that we have found.*/
        top--;
        /*this is to prepare for the new set of parenthesis.*/
        operands_count=1;
        operators_count=0;
    }
}
"+"|"-"|"*"|"/" {
    /*increment their count.*/
    operators_count++;
    /*copy the opeartors int he array.*/
    strcpy(operators[l], yytext);
    l++;
}
[0-9]+|[A-Za-z]|[a-zA-Z0-9_]* {
    operands_count++;
    strcpy(operands[j], yytext);
    j++;
}
\n {
    return 0;
}

%%
 

/*main program.*/
int main(){
    int k;
    printf("Enter the arithmetic expression. : \n");
    yylex();
    
    if(valid==1 && top==-1){
        /*the stack is empty and the expression is valid.*/
        printf("\n THIS IS A VALID EXPRESSION.\n");
    }else {
         printf("\n THIS IS NOT A VALID EXPRESSION.\n");
    }
    return 0;
}
