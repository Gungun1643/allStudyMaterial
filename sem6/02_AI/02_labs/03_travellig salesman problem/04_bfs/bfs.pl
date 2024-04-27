s(a,b).
s(a,c).
s(b,d).
s(b,e).
s(c,f).
s(c,g).
s(d,h).
s(e,i).
s(e,j).
s(f,k).
goal(f).
goal(j).

% Breadth-First Search algorithm
bfs(Start, Solution) :-
    bfs([[Start]], Solution).

bfs([[Node | Path] |_], [Node | Path]) :-
    goal(Node).

bfs([Path | Paths], Solution) :-
    extend(Path, NewPaths),
    conc(Paths, NewPaths, Paths1),
    bfs(Paths1, Solution).


extend([Node | Path], NewPaths) :-
    findall([newNode, Node|Path], (s(Node,NewNode), \+ member(NewNode, [Node |Path])), Extensions),
    conc(Extensions, [], NewPaths).


conc([], L, L).
conc([X | L1], L2, [X | L3]) :-
    conc(L1, L2, L3).



