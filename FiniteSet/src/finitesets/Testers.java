
package finitesets;

import static finitesets.FiniteSet.empty;
import java.util.Random;

public class Testers {
    
    public static int rndInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max-min) + 1) + min;
    }
    
    public static Tree rndTree(int branchNumber) {
        if (branchNumber == 0) {
            return empty();
        } else {
            return new FiniteSet(rndInt(0, 50),rndTree(branchNumber-1), rndTree(branchNumber-1));
        }
    }
    
    
    
    // Properties: EXAMPLES
    public static void checkTree_isEmptyHuh_cardinality(Tree l) {
        // If the tree is not empty, it shouldn't have a cardinality of zero
        // If the tree is empty, it should have a cardinality of zero
        if (((l.cardinality() != 0) && !l.isEmptyHuh())) {
                System.out.println("Success! "
                        + "It was non-empty and "
                        + "had a cardinality greater than zero.");
            } else {if (l.isEmptyHuh() && (l.cardinality() == 0)) {
                System.out.println("Success! It was empty and had a "
                        + "cardinality equal to zero");
            } else System.out.println("Failure");
        }
    }
            
    
    public static void checkTree_remove_cardinality(Tree l, int elt ) {
        int left = l.remove(elt).cardinality();
        // Either something was removed -> and it decreased the tree by one
        // Or the thing wasn't there to begin with, and nothing was removed
        if (left == (l.cardinality() - 1))  {
            System.out.println("Success! An item was removed!");
        } else {if (left == l.cardinality()) {
                System.out.println("Success! An item was not there so it was "
                        + "not removed!");
            } else System.out.println("Failure");
        }
    }
    
    public static void checkTree_add_cardinality(Tree l, int elt) {
        int more = l.add(elt).cardinality();
         if (more == (l.cardinality() + 1))  {
            System.out.println("Success! An item was added!");
        } else {if (more == l.cardinality()) {
                System.out.println("Success! An item was already there so it was "
                        + "not added!");
            } else System.out.println("Failure");
        }
    }
    
    public static void checkTree_union_cardinality(Tree l, Tree r) {
        Tree union = l.union(r);
        if (union.cardinality() < l.cardinality() + r.cardinality()) {
            System.out.println("Success! There were elements in common that "
                    + "weren't duplicated in the union");
        } else {if (union.cardinality() == l.cardinality() + r.cardinality()) {
                System.out.println("Success! l & r were disjoint.");
            } else System.out.println("Failure");
        }
        
    }
    
    
    
    public static void checkTree_add_member(Tree l, int elt) {
        Tree newTree = l.add(elt);
        if (newTree.member(elt)) {
            System.out.println("Success! It was added to the tree");
        } else {
            System.out.println("Failure! The added element is not a member "
                    + "of the finite set");
        }
    }
    
    public static void checkTree_add_member_test2(Tree l, int x, int y) {
        Boolean bool = l.add(x).member(y);
        if ( bool && x == y) {
            System.out.println("Success! X = Y and it's in the tree");
        } else { if (bool && l.member(y)) {
            System.out.println("Success! Y was a member of y beforehand and "
                    + "it's in the tree");
        } else { if (!bool && (x != y || !l.member(y))) {
            System.out.println("Success, X != Y and is not a member of the original"
                    + " tree and therefore is not a member of this tree");
        } else { System.out.println("Failure!");
        }
        }
    }
    }
    
    public static void checkTree_union_member(Tree l, Tree r, int x) {
        Boolean bool = l.union(r).member(x);
        if ( bool && l.member(x)) {
            System.out.println("Success! X is a member of the l tree");
        } else { if (bool && r.member(x)) {
            System.out.println("Success! X is a member of the r tree");
        } else { if (!bool && (!r.member(x) && !l.member(x))) {
            System.out.println("Success, X is not a member of the right or left "
                    + "tree and therefore not a part of the union");
        } else { System.out.println("Failure!");}
        }
        }
    }
        
}
  

