%{
 
%}

%%
"+" { return PLUS; }
"*" { return MULTIPLY; }
[a-zA-Z] { yylval = *yytext; return VARIABLE; }
[ \t\n] ; // Skip whitespace
. { return *yytext; } // Return any other character as is
%%

int yywrap() {
    return 1;
}
