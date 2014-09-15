
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
}
  

