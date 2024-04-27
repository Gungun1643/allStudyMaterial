fun(X, Result) :-
    X < 5,
    write('Condition 1 is true'), nl,
    Result = 1,
    !.

fun(X,Result) :-
    X >=5 ,
    write('Condition 2 is true'),nl,
    Result = 2.

