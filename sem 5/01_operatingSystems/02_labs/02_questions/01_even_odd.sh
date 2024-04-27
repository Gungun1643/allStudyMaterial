#!/bin/bash

echo "Enter a number :"
read num 

#check if the number is divisible by 2 without remainder (i.e even)

if((num%2==0)); then 
    echo "$num is even"

else 
    echo "$num is odd"

fi