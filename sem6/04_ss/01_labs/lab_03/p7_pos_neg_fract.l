/*definition section*/
%{
#include<stdio.h>
#include<stdlib.h>
#include<math.h>
int pos, neg, pos_frac, neg_frac;  // Declare yylval as a global variable
%}

%option noyywrap

/*declaration section*/
%{
int yylex(void);
%}

/*rules*/
%%
[0-9]+ {
    pos++;
}
[0-9]+"."[0-9]+ {
    pos_frac++;
}
"-"[0-9]+ {
    neg++;
}
"-"[0-9]+"."[0-9]+ {
    neg_frac++;
}
\n {
    /* Handle newline character */
    return '\n';
}
[ \t] {
    /* Skip whitespace characters */
}
. {
    /* Ignore other characters */
}
%%

/*main program*/
int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Usage: %s filename\n", argv[0]);
        return 1;
    }

    FILE* file = fopen(argv[1], "r");
    if (!file) {
        perror("Error opening file");
        return 1;
    }

    yyin = file;
    while (yylex() != 0) {
        // Continue processing
    }

    fclose(file);

    printf("Positive numbers are: %d\n", pos);
    printf("Negative numbers are: %d\n", neg);
    printf("Positive fractions are: %d\n", pos_frac);
    printf("Negative fractions are: %d\n", neg_frac);

    return 0;
}
