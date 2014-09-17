
package finitesets;

import static finitesets.FiniteSet.empty;
import java.util.Random;

public class Testers {
    
    public static int rndInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max-min) + 1) + min;
    }
    
    public static Tree rndTree(int count) {
        if (count == 0) {
            return empty();
        } else {
            return rndTree(count-1).add(rndInt(0,50));
        }
    }
    
    public static void checkTree_empty_isEmptyHuh(int count) {
        // Upping the odds -> creating a empty tree or a random tree
        if (count == 0) {
            Tree t = empty();
            if (t.isEmptyHuh()) {
            System.out.println("Success! An empty set is empty!");}
            else {
                System.out.println("Failure! An empty set is not empty!");
            }
        } else {
            int len = rndInt(1, 10);
            Tree l = rndTree(len);
            if (!l.isEmptyHuh()) {
                System.out.println("Success! A nonempty set is not empty!");
            } else {
                System.out.println("Failure! A nonempty set is empty!");
            }
    }
    }
    
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
    
         public static void checkTree_cardinality_remove(Tree l, int elt ) {
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
         
         public static void checkTree_remove_equal_add(Tree l, int elt) {
             // Add and remove the same element from a copied tree
             Tree newTree = l.add(elt);
             newTree = l.remove(elt);
             // If the tree we messed with is the same as the original tree
             // then we are correct!
             if (l.equal(newTree)) {
                 System.out.println("Success! The tree stayed the same after "
                         + "adding and removing the same item.");
             } else {
                 System.out.println("Failure! The tree changed");
             }
         }

     
    public static void checkTree_add_member(Tree l, int x, int y) {
        Boolean bool = l.add(x).member(y);
        if ( bool && x == y) {
            System.out.println("Success! X = Y and it's in the tree");
        } else { if (bool && l.member(y)) {
            System.out.println("Success! Y was a member of y beforehand and "
                    + "it's in the tree");
        } else { if (!bool && (x != y || !l.member(y))) {
            System.out.println("Success! X != Y and is not a member of the original"
                    + " tree and therefore is not a member of this tree");
        } else { System.out.println("Failure!");
        }
        }
    }
    }
    
    public static void checkTree_member_union(Tree l, Tree r, int x) {
        Boolean bool = l.union(r).member(x);
        if ( bool && l.member(x)) {
            System.out.println("Success! X is a member of the l tree");
        } else { if (bool && r.member(x)) {
            System.out.println("Success! X is a member of the r tree");
        } else { if (!bool && (!r.member(x) && !l.member(x))) {
            System.out.println("Success! X is not a member of the right or left "
                    + "tree and therefore not a part of the union");
        } else { System.out.println("Failure!");}
        }
        }
    }
    
    public static void checkTree_union_subset (Tree l, Tree r) {
        Tree unionLR = l.union(r);
        if (l.subset(unionLR) && r.subset(unionLR)) {
            System.out.println("Success! The left and right trees are subsets"
                    + " of their union");
        } else {
            System.out.println("Failure!");
        }
    }
    
    
    // DIFF NOT WORKING :(
    public static void checkTree_subset_diff (Tree l, Tree r) {
        // If we take L-R = D; then R cannot be a subset of D beacuse all of
        // it's elements are not supposed to be their by def of diff
        Tree difference = l.diff(r);
        if (!r.subset(difference)) {
            System.out.println("Success! A tree is not a subset of it's "
                    + "difference in a given universe");
        } else {
            System.out.println("Failure");
        }
    }
    
    // This test also says something is wrong with diff because all of the other
    // tests -> besides the ones using diff -> work
    public static void checkTree_diff_empty_equal (Tree l, Tree r) {
        // Two sets have the empty set as their diff iff they are equal
        if (l.equal(r) && l.diff(r).equal(empty())) {
            System.out.println("Success! Two trees are equal and their diff is"
                    + " the empty set");
        } else if (!l.equal(r) && !l.diff(r).equal(empty())) {
            System.out.println("Success! Two trees are different and their diff "
                    + "is not the empty set");
        } else {
            System.out.println("Failure!");
        }
    }
    

    public static void checkTree_equal_union_inter (Tree l, Tree r) { 
        // Two sets are equal iff their union and intersection is the same
        if ((l.union(r).equal(l.inter(r))) && l.equal(r)) {
            System.out.println("Success! The two trees are equal and have "
                    + "the same intersection and union");
        } else if ((l.union(r) != l.inter(r)) && !l.equal(r)) {
            System.out.println("Success! They are not equal and their"
                    + " intersection and union are different");
        } else {
            System.out.println("Failure!");
        }
    }
    
    // The Identity Property for Inter
    public static void checkTree_inter_empty(Tree l) {
        Boolean bool = l.inter(empty()).equal(empty());
        // If the intersection of any tree with the empty set
        // equals the empty set...
        if (bool) {
            System.out.println("Success! The intersection of any tree with the "
                    + "empty set is just the empty set!");
        } else {
             System.out.println("Failure!");
        }
    }
    
    
   
    
//    // Properties: EXAMPLES
//            
//    
//    public static void checkTree_remove_cardinality(Tree l, int elt ) {
//        int left = l.remove(elt).cardinality();
//        // Either something was removed -> and it decreased the tree by one
//        // Or the thing wasn't there to begin with, and nothing was removed
//        if (left == (l.cardinality() - 1))  {
//            System.out.println("Success! An item was removed!");
//        } else {if (left == l.cardinality()) {
//                System.out.println("Success! An item was not there so it was "
//                        + "not removed!");
//            } else System.out.println("Failure");
//        }
//    }
//    
//    
//    
//    public static void checkTree_union_cardinality(Tree l, Tree r) {
//        Tree union = l.union(r);
//        if (union.cardinality() < l.cardinality() + r.cardinality()) {
//            System.out.println("Success! There were elements in common that "
//                    + "weren't duplicated in the union");
//        } else {if (union.cardinality() == l.cardinality() + r.cardinality()) {
//                System.out.println("Success! l & r were disjoint.");
//            } else System.out.println("Failure");
//        }
//        
//    }
    
    
    
//    public static void checkTree_add_member(Tree l, int elt) {
//        Tree newTree = l.add(elt);
//        if (newTree.member(elt)) {
//            System.out.println("Success! It was added to the tree");
//        } else {
//            System.out.println("Failure! The added element is not a member "
//                    + "of the finite set");
//        }
   // }
    
    
//     public static void checkTree_cardinality_add(Tree l, int elt) {
//        int more = l.add(elt).cardinality();
//         if (more == (l.cardinality() + 1))  {
//            System.out.println("Success! An item was added!");
//        } else {if (more == l.cardinality()) {
//                System.out.println("Success! An item was already there so it was "
//                        + "not added!");
//            } else System.out.println("Failure");
//        }
//    }
        
}
  

