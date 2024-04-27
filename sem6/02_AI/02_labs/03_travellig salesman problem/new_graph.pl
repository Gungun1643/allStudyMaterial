domains
/*will allow us coperate with better names, for me this is like #typedef in c++*/
 town = symbol
 distance= unsigned
 rib = r(town, town, distance)
 tlist = town*
 rlist = rib*

predicates
 nondeterm way(town, town,rlist,distance)
 nondeterm route(town, town,rlist,tlist, distance)
 nondeterm route1(town, tlist,rlist,tlist,distance)
 nondeterm ribsmember(rib, rlist)
 nondeterm townsmember(town, tlist)
 nondeterm tsp(town, town, tlist, rlist, tlist, distance)
 nondeterm ham(town,town, tlist, rlist,tlist,distance)
 nondeterm shorterRouteExists(town,town,tlist,rlist,distance)
 nondeterm alltown(tlist,tlist)
 nondeterm write_list(tlist)


clauses
 write_list([]).
 write_list([H|T]):-
    write(H, ' '),
    write_list(T).

 townsmember(X,[X|_]).
 townsmember(X,[_|L]):-
  townsmember(X,L).

  /*Is true if rib X is in list of ribs... */
 ribsmember(r(X,Y,D),[r(X,Y,D)|_]).
 ribsmember(X,[_,|L]):-
  ribsmember(X,L).
  /* Is true if Route consists of all towns presented in second argument.*/
 alltown(_,[]).
 alltown(Route, [H|T]):-
  townsmember(H,Route),
  alltown(Route,T).

/*Is true if there is a way from Town1  to Town2 , and also return distance between them...*/
 way(Town1, Town2,Ways, OutWayDistance):-
    ribsmember(r(Town1,Town2,D),Ways),
    OutWayDistance = D.


%/*

/*If next is uncommented then we are using the non-oriented graph.*/
 way(Town1,Town2,Ways,OutDistance):-
    ribsmember(r(Town2,Town1,D),Ways), /*switching the direction here...*/
    OutWayDistance = D.
%*/

/* Is true if we could build route from Town1 to Town2 */
 route(Town1,Town2,Ways,OutRoute,OutDistance):-
    route1(Town1,[Town2],Ways,OutRoute,T1T2Distance),
%Switch here
    way(Town2,Town1,Ways,LasDist), /*If you want to find the shortest way comment this line...*/
    OutDistance = T1T2Distance + LasDist. /*and make this OutDistance= T1T2Distance.*/
 route1(Town1, [Town1[Route1],_, [Town1[Route1]], OutDistance):-
    OutDistance= 0.
/* does the acutal finding of the route .We take new TownX town and if it not member of the PassedRoute, we continue searching with including TownX in hte list of the passed towns...*/
 route1(Town1, [Town2|PassedRoute], Ways,OutRoute, OutDistance):-
    way(TownX,Town2,Ways,WayDistance),
    not(townsmember(TownX, PassedRoute)),
    route1(Town1, [Town2|PassedRoute], Ways,OutRoute, CompletingRoadDistance),
    OutDistance= CompletingRoadDistance + WayDistance.


 shorterRouteExists(Town1,Town2,Towns,Ways,Distance):-
    ham(Town1,Town2,Towns,Ways,_,Other),
       Other < Distance.

/*calling the tsp(a,a,... picks any one connected to a town and calls another tsp..)*/
 tsp(Town1,Town1,Towns,Ways,BestRoute,MinDistance):-
    way(OtherTown,Town1,Ways,_),
       tsp(Town1,OtherTown,Towns,)




