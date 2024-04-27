% Rule for converting Celsius to Fahrenheit.
c_to_f(C,F) :-
    F is C * 9 /5 + 32.

% Rule for checking if the temperature is freezing (Fahrenheit <= 32)
freezing(F) :-
    F =< 32.


