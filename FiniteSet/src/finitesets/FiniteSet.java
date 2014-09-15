
package finitesets;
import static finitesets.Testers.rndInt;
import static finitesets.Testers.rndTree;
import java.util.Random;

public class FiniteSet implements Tree{
    int root;
    Tree left;
    Tree right;  

    
    // Constructors
    public FiniteSet(int root, Tree left, Tree right) {
	    this.root = root;
	    this.left = left;
	    this.right = right;
    }
    
    
     public FiniteSet(int root) {
         // Setting Properties
	    this.root = root;
	    this.left = empty();
	    this.right = empty();
    }

     
    // (empty) --> FiniteSet 
    // Returns fresh empty set
   public static Tree empty() {
       // Calling the helper
       return new FiniteSet_Empty();
    }
   

    // (cardinality t) --> integer
    // Returns the number of elements in t
    public int cardinality() {
	// Return the sum of the number of things in the left and right trees 
	// While counting the root as a thing we have now
	return 1 + left.cardinality() + right.cardinality();
	
    }


    // (isEmptyHuh t) --> boolean
    // Determines if t is empty
    public boolean isEmptyHuh() {
	// If it's a  FiniteSet (non-empty), then this function
	// is called and it can't be empty
	// So return false.
	return false;      
    }


    // (member t elt) --> boolean
    // t : FiniteSet
    // elt : integer
    // Determines if elt is in t
    public boolean member(int elt) {
	// If the root equals the element, then it's a member
	// of the tree and we are done and return true
	if (root ==  elt) {
	    return true;
	} else {
	    //Check if the element is less than the root; if so, check if
	    // the element is a member of the left tree
	    if (root > elt) {
		left.member(elt);
		    } 
	    // Otherwise, we check if the element is a member of the right
	    // tree
	    else {
		right.member(elt);
	    }
	}
        //If it's not a member of either tree -> return false
        return false;
    }
	

    // (add t elt) --> finite-set
    // t : finite-set
    // elt : integer
    // Returns a set containing elt and everything in t
    public FiniteSet add(int elt) {
	// Create a new Finite Set with this root & left & right trees
	FiniteSet finiteSet = new FiniteSet(this.root, this.left, this.right);
	// If the root is greater than the element, then add it to the left 
	// tree
	if (finiteSet.root > elt) {
	    finiteSet.left = finiteSet.left.add(elt);
	} 
        // If the root is less than the element, then add it to the 
        // right tree
        if (finiteSet.root < elt) {
	    finiteSet.right = finiteSet.right.add(elt);
        }
	// Return the tree when we're done
	return finiteSet;
    }

    
	// (remove t elt) → finite-set
	// t : finite-set
	// elt : integer
	// Returns a new FiniteSet without the element
 
    public Tree remove (int elt) {
	// If this element equals the root; then take out the root by unioning
        // the two children
	if (this.root  == elt) {
	    return this.left.union(this.right);
	} else { 
		if (this.root > elt){ 
		    // If the root is more than the element, 
                    // then return a new copied tree
		    //  that removes the element from the left tree
		    return new FiniteSet(this.root, this.left.remove(elt), 
                            this.right);
		} else {
                     // If the root is less than the element, 
                    // then return a new copied tree
		    //  that removes the element from the right tree
		    return new FiniteSet(this.root, this.left, 
                            this.right.remove(elt));
			     }
        }
    }


    // (union t u) --> finite-set
    // t : finite-set
     // u : finite-set
    // Returns a set containing everything in t and u
        public Tree union (Tree u) {
          // First create a new finiteSet
           FiniteSet finiteSet = new FiniteSet(this.root, this.left, 
                   this.right);
           // Union U with the unioned left & right with a copy this object; 
           // then add the root
           return u.union(finiteSet.left.union(finiteSet.right)).add(finiteSet.root);
	    }
       
        
    // (inter t u) --> finite-set
    // t: finite-set
    // u: finite-set
    // Returns a set containing everything that is in both t and u
        public Tree inter(Tree u) {
            // If the root of this object is a member of u
            if (u.member(this.root)) {
                 // Then create a new tree with that root and the intersection
                 // of the left tree and the intersection of the right tree
                return new FiniteSet(this.root, this.left.inter(u), 
                        this.right.inter(u));
            } else {
            // Otherwise don't add the root, and just return the union of
            // the left tree's intersection with u and the right tree's 
            // intersection with u
            return this.left.inter(u).union(this.right.inter(u));
        }
        }


    // (diff t u) --> finite-set
    // t : finite-set
    // u : finite-set
    // Returns a set containing everything in t that is not in u
        public Tree diff(Tree u) {
            // If the root of this object is a member of u, then..
            if (u.member(this.root)) {
                // Return the combined left, right, and u tree without
                // the said object
               return left.union(right.union(u.remove(this.root)));
            // Otherwise...
            } else 
                // Return the union of the difference of the left and the
                // difference right
                {
                return left.diff(u).union(right.diff(u));
            }
        }


    // (equal t u) --> boolean
    // t : finite-set
    // u : finite-set
    // Determines if t and u contain the same elements
	public Boolean equal (Tree u) {
            return this.inter(u) == this.union(u);
        }


    // (subset t u) --> boolean
    // t : finite-set
    // u : finite-set
    // Determines if t is a subset of u
        public Boolean subset (Tree u) {
	Boolean bool = false;
            if (u.member(this.root)) {
            bool = true;
        } else {
            this.left.subset(u);
            this.right.subset(u);
	    }
            return bool;
        }

   public void printAllElements() {
        String string = " ";
        string += this.root;
        this.left.printAllElements();
        this.right.printAllElements();
        System.out.print(string);
        }

                
    public static void main(String[] args) {
        // Testing our random number generator
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        
        // Seems to be working.. Now making some random trees
        rndTree(1).printAllElements();
        System.out.println();
        rndTree(2).printAllElements();
        System.out.println();
        rndTree(3).printAllElements();
        System.out.println();
        rndTree(4).printAllElements();
        System.out.println();
        rndTree(5).printAllElements();
        System.out.println();
        rndTree(6).printAllElements();
        System.out.println();
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // A COUPLE OF TESTS
       Tree mt = empty();
        System.out.println( "The length of it is.... " +
                            mt.cardinality() );
       FiniteSet l5 = (new FiniteSet (5, empty(), empty()));
       FiniteSet l6 = (new FiniteSet (6, empty(), empty()));
        System.out.println( "The length of it is.... " +
                            l5.cardinality() );

        System.out.println( "The length of mt after we remove 6 is... " +
                            mt.remove(6).cardinality() +
                            " should be 0");
        System.out.println( "The length of l5 after we remove 6 is... " +
                            l5.remove(6).cardinality() +
                            " should be 1" );
        System.out.println( "The length of l5 after we remove 5 is... " +
                            l5.remove(5).cardinality() +
                            " should be 0" );
        
       FiniteSet l7 = (new FiniteSet (7, empty(), empty()));
       FiniteSet l8 = (new FiniteSet (8, empty(), empty()));
        System.out.println( "This should have two elements" + l7.union(l8).cardinality());
        System.out.println( "This should have two elements" + l8.union(l7).cardinality());
        System.out.println("This should have size of 1 = " + l5.union(mt).cardinality());
        System.out.println("This should have a size of 1 = " + l6.union(mt).cardinality());
        System.out.println("This should have a size of 2 = " + l7.add(8).cardinality());
        System.out.println("This should have a size of 1 = " + mt.add(1).cardinality());
        System.out.println("This should be false = " + mt.member(1));
        System.out.println("This should be true = " + l7.member(7));
        System.out.println("This should be false = " + l8.member(7));
        System.out.println("This should be true = " + l6.member(6));
        
       FiniteSet l9 = (new FiniteSet (9, empty(), empty()));
       FiniteSet l10 = (new FiniteSet (10, empty(), empty()));
        
       // Inter 
       System.out.println("This should be 0 = " + l9.inter(l10).cardinality());
       System.out.println("This should be 1 = " + l9.inter(l9).cardinality());
       System.out.println("This should be 0 = " + mt.inter(mt).cardinality());
       System.out.println("This should be 0 = " + l9.inter(mt).cardinality());
       
       // Subset 
       System.out.println("This should be false = " + l9.subset(l10));
       System.out.println("This should be true = " + l9.subset(l9));
       System.out.println("This should be true = " + mt.subset(l9));
       System.out.println("This should be false = " + l9.subset(mt));
       System.out.println("This should be true = " + mt.subset(mt));
       
       // Equals 
       System.out.println("This should be false = " + l9.equals(l10));
       System.out.println("This should be true = " + l9.equals(l9));
       System.out.println("This should be false = " + l9.equals(mt));
       System.out.println("This should be true = " + mt.equals(l9));
       System.out.println("This should be true = " + mt.equals(mt));
       
       
       //DIFF
       System.out.println("This should be 1 = " + l9.diff(l10).cardinality());
    }
    
}
