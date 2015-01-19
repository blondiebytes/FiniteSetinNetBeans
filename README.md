Finite Sets
===================

3.1 Finite Sets

Your challenge is to develop a correct, pure implementation of the finite integer set data-structure. A finite integer set is a finite set of only integers. By "pure", we mean that operations on the set return new sets and do not modify the old set. You should implement the sets using binary search trees.

Your finite sets should support the following operations:

procedure
(empty) → finite-set
Returns a fresh empty set.
procedure
(cardinality t) → integer
  t : finite-set
Returns the number of elements in t.
procedure
(isEmptyHuh t) → boolean
  t : finite-set
Determines if t is empty.
procedure
(member t elt) → boolean
  t : finite-set
  elt : integer
Determines if elt is in t.
procedure
(add t elt) → finite-set
  t : finite-set
  elt : integer
Returns a set containing elt and everything in t.
procedure
(remove t elt) → finite-set
  t : finite-set
  elt : integer
Returns a set containing everything in t except elt.
procedure
(union t u) → finite-set
  t : finite-set
  u : finite-set
Returns a set containing everything in t and u.
procedure
(inter t u) → finite-set
  t : finite-set
  u : finite-set
Returns a set containing everything that is in both t and u.
procedure
(diff t u) → finite-set
  t : finite-set
  u : finite-set
Returns a set containing everything in u except those that are in t.
procedure
(equal t u) → boolean
  t : finite-set
  u : finite-set
Determines if t and u contain the same elements.
procedure
(subset t u) → boolean
  t : finite-set
  u : finite-set
Determines if t is a subset of u.
You should thoroughly demonstrate the correctness of these operations by appealing to properties of finite sets. Here is a non-exhaustive list of properties:
member (add t x) y = true <-> x = y \/ member t y = true
 
member (union s s') x = true <-> member s x = true \/ member s' x = true
I highly suggest you use these properties to randomly generate large numbers of test cases and search for witnesses of their negation.

You should bring in your source code, a console transcript of your test suite’s execution, and an essay which describes your approach to implementing and verifying the system. Your essay should probably make reference to specific lines in your source code and test transcript.
