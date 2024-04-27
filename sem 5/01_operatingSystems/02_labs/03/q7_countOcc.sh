#!/bin/bash

# Get the filename as the argument 
filename="$1"

# Check if the file exists
if [ ! -e "$filename" ]; then 
    echo "File not found"
    exit 1
fi

# Count the number of characters 
charCount=$(wc -m < "$filename")

# Count the number of words 
wordCount=$(wc -w < "$filename")

# Count the number of lines 
linesCount=$(wc -l < "$filename")

# Count the number of spaces 
spaceCount=$(tr -cd ' ' < "$filename" | wc -c)

echo "Character count is: $charCount"
echo "Word count is: $wordCount"
echo "Line count is: $linesCount"
echo "Space count is: $spaceCount"
