% base case : fib(0) is 0
fib(0,0).

%predicate for computing the fibonacci numbers
fib(X,Y) :-
    X > 0,
    fib(X, Y, _).

%base case for fib(1) = 1
% this is an additional argument for handling the previous fibonaci
% number
fib(1,1,0).

% recursive case
fib(X,Y1,Y2) :-
    X > 1,
    X1 is X-1,
    fib(X1, Y2,Y3),
    Y1 is Y2 + Y3.
