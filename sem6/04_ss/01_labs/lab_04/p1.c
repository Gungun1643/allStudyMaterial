#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define maximum lengths for various tokens
#define MAX_TOKEN_LENGTH 50
#define MAX_OPCODE_LENGTH 10
#define MAX_OPERAND_LENGTH 10
#define MAX_LABEL_LENGTH 10

// Define structures to hold symbol table entries and literal table entries
struct symbol_table_entry {
    char label[MAX_LABEL_LENGTH];
    int address;
};

struct literal_table_entry {
    char literal[MAX_OPERAND_LENGTH];
    int address;
};

// Function prototypes
void add_to_symbol_table(char *label, int address);
void add_to_literal_table(char *literal, int address);
void print_symbol_table();
void print_literal_table();

// Initialize symbol table and literal table
struct symbol_table_entry symbol_table[100];
struct literal_table_entry literal_table[100];
int symbol_table_index = 0;
int literal_table_index = 0;
int location_counter = 0;

// Empty function definitions to avoid linker errors
void add_to_symbol_table(char *label, int address) {}
void add_to_literal_table(char *literal, int address) {}
void print_symbol_table() {}
void print_literal_table() {}

%option noyywrap

DIGIT [0-9]
LETTER [a-zA-Z]
LABEL ({LETTER}({LETTER}|{DIGIT}){0,8})
OPERAND ({LETTER}|{DIGIT}|{LABEL})
OPCODE ("START"|"MOVER"|"MOVEM"|"LOOP"|"ADD"|"BC"|"LTORG"|"SUB"|"ORIGIN"|"MULT"|"EQU")
WS [ \t]

%%

{WS}+   ; // Ignore whitespace

{OPCODE}   {
            printf("Opcode: %s\n", yytext);
            }

{LABEL} {
        if (strcmp(yytext, "NAN") != 0) {
            printf("Label: %s\n", yytext);
            add_to_symbol_table(yytext, location_counter);
        }
        }

{OPERAND} {
            printf("Operand: %s\n", yytext);
            if (strcmp(yytext, "NAN") != 0 && yytext[0] == '=') {
                add_to_literal_table(yytext, location_counter);
            }
            }

[0-9]+ {
        location_counter += atoi(yytext);
        printf("Location Counter Incremented by: %d\n", atoi(yytext));
        }

\n      {line_number++;}

.       {printf("Unknown token at line %d: %s\n", line_number, yytext);}

%%

int main() {
    yylex();
    print_symbol_table();
    print_literal_table();
    return 0;
}
