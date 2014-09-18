
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
        // Creating a empty tree or a random tree
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
    
    public static void checkTree_isEmptyHuh_cardinality(Tree t) {
        if (t.isEmptyHuh() && (t.cardinality() == 0)) {
            System.out.println("Success! It was empty and had a "
                    + "cardinality equal to zero");
        } else {
            if (!t.isEmptyHuh() && (t.cardinality() != 0)) {
                System.out.println("Success! "
                        + "It was non-empty and "
                        + "had a cardinality greater than zero.");
            } else {
                System.out.println("Failure - the cardinality did not match"
                        + " the type of set.");
            }
        }
    }
    
         public static void checkTree_cardinality_remove(Tree t, int x ) {
        int nT = t.remove(x).cardinality();
        // Either something was removed -> and it decreased the tree by one
        // Or the thing wasn't there to begin with, and nothing was removed
        if (nT == (t.cardinality() - 1))  {
            System.out.println("Success! An item was removed!");
        } else {if (nT == t.cardinality()) {
                System.out.println("Success! An item was not there so it was "
                        + "not removed!");
            } else System.out.println("Failure - the remove and/or cardinality"
                    + " function failed :( ");
        }
    }
         
         public static void checkTree_remove_equal_add(Tree t) {
             // Add and remove the same element from a copied tree
             int rand = rndInt(51,100);
             Tree nT = t.add(rand);
             nT = nT.remove(rand);
             // If the tree we messed with is the same as the original tree
             // then we are correct!
             if (t.equal(nT)) {
                 System.out.println("Success! The tree stayed the same after "
                         + "adding and removing the same item.");
             } else {
                 System.out.println("Failure! The tree changed.");
             }
         }

     
    public static void checkTree_add_member(Tree t, int x, int y) {
        Boolean bool = t.add(x).member(y);
        if ( bool && x == y) {
            System.out.println("Success! X = Y and it's in the tree");
        } else { if (bool && t.member(y)) {
            System.out.println("Success! Y was a member of y beforehand and "
                    + "it's in the tree");
        } else { if (!bool && (x != y && !t.member(y))) {
            System.out.println("Success! X != Y and is not a member of the original"
                    + " tree and therefore is not a member of this tree");
        } else { System.out.println("Failure! Problem with member or add!");
        }
        }
    }
    }
    
    public static void checkTree_member_union(Tree t, Tree r, int x) {
        Boolean bool = t.union(r).member(x);
        if ( bool && t.member(x)) {
            System.out.println("Success! X is a member of the t tree");
        } else { if (bool && r.member(x)) {
            System.out.println("Success! X is a member of the r tree");
        } else { if (!bool && (!r.member(x) && !t.member(x))) {
            System.out.println("Success! X is not a member of the right or left "
                    + "tree and therefore not a part of the union");
        } else { System.out.println("Failure! Problem with member or union!");}
        }
        }
    }
    
    public static void checkTree_union_subset (Tree t, Tree r) {
        Tree unionLR = t.union(r);
        if (t.subset(unionLR) && r.subset(unionLR)) {
            System.out.println("Success! The left and right trees are subsets"
                    + " of their union");
        } else {
            System.out.println("Failure! Problem with union or subset!");
        }
    }
    
    
    // 
    public static void checkTree_subset_diff (Tree t, Tree r) {
        // If we take R - T = D; then T is either the empty set or it is not
        // a subset of it's difference
        Tree difference = t.diff(r);
        if (t.isEmptyHuh()) {
            System.out.println("Success! The tree t is empty leaving the diff "
                    + "to be all of r");
        } else if (!t.subset(difference)) {
            System.out.println("Success! A tree is not a subset of the "
                    + "difference");
        }
            else {
            System.out.println("Failure! Problem with subset or diff!");
        }
    }
   
    // This test also says something is wrong with diff because all of the other
    // tests -> besides the ones using diff -> work
    public static void checkTree_diff_inter_empty_equal (Tree t, Tree r) {
        // t inter B = the empty set iff t - B = t
        if ((t.inter(r)).equal(empty()) && r.diff(t).equal(t)) {
            System.out.println("Success! A inter B = the empty set iff A - B = A");
        } else if (!(t.inter(r)).equal(empty()) && !r.diff(t).equal(t)) {
            System.out.println("Success! A inter B != the empty set iff A - B != A");
    } else 
            System.out.println("Failure!");
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
  

