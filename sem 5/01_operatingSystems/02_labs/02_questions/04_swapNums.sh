#!/bin/bash

echo "Enter the first integer: "
read num1

echo "Enter the second integer: "
read num2

echo "Before swapping : num1 = $num1 , num2 = $num2 "

#swapping login using the temporary variable 
temp=$num1
num1=$num2
num2=$temp

echo "After swapping: num1 = $num1, num2 = $num2"
