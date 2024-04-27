/* Sample database */
proposer(john).
proposer(sarah).
proposer(mike).

age(john, 25).
age(sarah, 28).
age(mike, 32). /* Mike is too old */

membership(john, 3).
membership(sarah, 2).
membership(mike, 5).

parent(john, alice).
parent(sarah, bob).
parent(mike, carol).

/* Rules */
acceptable_for_membership(Applicant) :-
    proposer(Proposer1),
    proposer(Proposer2),
    Proposer1 \= Proposer2, /* Ensures proposers are different */
    age(Applicant, Age),
    Age >= 18,
    Age =< 30,
    membership(Proposer1, Years1),
    membership(Proposer2, Years2),
    Years1 >= 2,
    Years2 >= 2,
    not(parent(Proposer1, Applicant)),
    not(parent(Proposer2, Applicant)).

/* Example Query */
/* acceptable_for_membership(john). */
