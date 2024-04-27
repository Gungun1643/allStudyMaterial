#!/bin/bash

echo "Enter an integer: "
read num

#initialize the reversedNum
reversedNum=0

#reverse the number using the while loop 
while((num!=0)); do
    lastDigit=$((num%10))
    reversedNum=$((reversedNum*10+lastDigit))
    num=$((num/10))
done

echo "Reversed number is : $reversedNum"

