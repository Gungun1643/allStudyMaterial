append(integer,list,list).
rev(list, list).

append(X,[],[X]).
append(X,[H|T],[H|T1]):- append(X, T, T1).


rev([],[]).
rev([H|T],Reversed) :- rev(T,L),append(H,L,Reversed).

