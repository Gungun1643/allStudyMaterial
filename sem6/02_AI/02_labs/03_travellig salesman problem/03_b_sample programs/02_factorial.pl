% rule to find of the factorial of the number

% Base case : factorial of 0 is 1
factorial(0,1).

% Recursion case : factorial of N is N * factorial(N-1).
factorial(N, F) :-
    N > 0,  % this is the valid case to check for the recursion
    N1 is N-1,  % N1 is the answer from the current recursion
    factorial(N1,F1),  % F1 is the result from the (N-1)th  recursion
    F is N * F1.  % this is the final return of the answer ....curr * next

