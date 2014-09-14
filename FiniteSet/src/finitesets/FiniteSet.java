
package finitesets;

/**
 *
 * @author kathrynhodge
 */
public class FiniteSet implements Tree{
    int root;
    Tree left;
    Tree right;  

    // Constructor
    public FiniteSet(int root, Tree left, Tree right) {
	    this.root = root;
	    this.left = left;
	    this.right = right;
    }

    // (empty) --> FiniteSet 
    // Returns fresh empty set
   public Tree empty() {
       return new FiniteSet_Empty();
    }

    // (cardinality t) --> integer
    // Returns the number of elements in t
    public int cardinality() {
	// Return the sum of the number of things in the left and right trees 
	// Counting the root as a thing we have now
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
	// of the tree  and we return true
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
        return false;
    }
	



    // (add t elt) --> finite-set
    // t : finite-set
    // elt : integer
    // Returns a set containing elt and everything in t
    public FiniteSet add(int elt) {
	// Create a new Finite Set with this root & left & right trees
	FiniteSet finiteSet = new FiniteSet(this.root, this.left, this.right);
	// if the root is greater than the element, then add it to the left 
	// tree
	if (finiteSet.root > elt) {
	    finiteSet.left = finiteSet.left.add(elt);
	    // if the root is less than the element, then add it to the right tree
	} 
        if (finiteSet.root < elt) {
	    finiteSet.right = finiteSet.right.add(elt);
        }
	// return the tree when we're done
	return finiteSet;
    }






	// (remove t elt) → finite-set
	// t : finite-set
	// elt : integer
	// Returns a new FiniteSet without the element
 
    public Tree remove (int elt) {
	// If this element equals the root; then take out the root by unioning  the two children
	if (this.root  == elt) {
	    return this.left.union(this.right);
	} else { 
		if (this.root > elt){ 
		    // If the root is bigger than the element, then return a new copied  tree
		    //  that removes the element  from the left tree
		    return new FiniteSet(this.root, this.left.remove(elt), this.right);
		} else {
		    return new FiniteSet(this.root, this.left, this.right.remove(elt));
			     }
        }
    }



    // (union t u) --> finite-set
    // t : finite-set
     // u : finite-set
    // Returns a set containing everything in t and u
        public Tree union (Tree u) {
         // Instinct is to iterate over the add function...
            // First create a new finiteSet
            FiniteSet finiteSet = new FiniteSet(this.root, this.left, this.right);
            // Union left and right
            Tree finiteSetLR =  finiteSet.left.union(finiteSet.right);
            // Union that with new tree
            Tree finiteSetLRU = finiteSetLR.union(u);
            // Add the root
            finiteSetLRU.add(finiteSet.root);
            return finiteSetLRU;
	    }
                
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
