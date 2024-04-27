#!/bin/bash

read -p "Enter a num : " num

if ((num < 0)); then 
    echo "Factorial is not defined for negative numbers."
    exit 1
fi

factorial=1

for ((i=1; i<=num; i++)); do
    factorial=$((factorial * i))
done

echo "The factorial of $num is $factorial."
