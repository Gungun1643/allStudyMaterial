%facts
male(shankar).
male(ulhas).
male(satish).
male(saurabh).
male(prashant).

%female
female(umabai).
female(mrunal).
female(sadhana).
female(swati).

% parent
parent(shankar,umabai,ulhas).
parent(shankar,umabai,satish).
parent(ulhas,mrunal,prashant).
parent(satish,sadhana,saurabh).
parent(satish,sadhana,swati).

%brother
brother(ulhas,satish).
brother(satish,ulhas).
brother(prashant,saurabh).
brother(saurabh,prashant).

%sister
sister(swati,saurabh).
sister(swati,prashant).




%father
father(X, Y) :-
    male(X),
    parent(X, _, Y).

%Mother
mother(X, Y):-
    female(X),
    parent(_, X, Y).

%son
son(X, Y):-
    male(X),
    parent(_, Y, X)  ; parent(Y, _, X).


%daughter
daughter(X, Y):-
    female(X),
   (parent(_, Y, X)  ; parent(Y, _, X)).

grandfather(GF, GS) :-
    male(GF),
    (father(GF, Parent), father(Parent, GS) ;
     mother(GF, Parent), father(Parent, GS)).

grandmother(GM, C) :-
    female(GM),
    (mother(GM, Parent), mother(Parent, C) ;
     mother(GM, Parent), father(Parent, C)).

aunt(A, C) :-
    female(A),
    ( (mother(M,C), sister(A,M)) ;(father(F,C), sister(A,F)) ).


uncle(A, C) :-
    male(A),
    ( (mother(M,C), brother(A,M)) ;(father(F,C), brother(A,F)) ).

%cousins
% Cousin predicate
cousin(Cousin, Person) :-
    parent(Parent1, Person, _),
    parent(Parent2, Cousin, _),
    Parent1 \= Parent2,
    ( sibling(Parent1, Parent2) ; sibling(Spouse1, Parent1), sibling(Spouse1, Parent2) ).

% Sibling predicate
sibling(X, Y) :- brother(X, Y) ; brother(Y, X) ; sister(X, Y) ; sister(Y, X).




ancestor(Ancestor, Descendant) :-
    parent(Ancestor, _, Descendant).
ancestor(Ancestor, Descendant) :-
    parent(Parent, _, Descendant),
    ancestor(Ancestor, Parent).

%grandchildren.
grandchildren_count(Person, Count) :-
    findall(GrandChild, (
        parent(Person, _, Child),
        parent(Child, _, GrandChild)
    ), GrandChildren),
    length(GrandChildren, Count).

%sister of prashant
sister_of(X, Y) :-
    findall(Sister, (sister(Sister , Y)  ; sister(Y,Sister) ),  X).

parents_of(X, Y) :-
    findall(Parent, (parent(Parent,_ , Y)  ; parent(_,Parent, Y) ),  X).

