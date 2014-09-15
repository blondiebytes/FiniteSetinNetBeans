
package finitesets;

import static finitesets.FiniteSet.empty;

public class FiniteSet_Empty implements Tree {
    
    public void FiniteSet_Empty() {
    }
    
    public int cardinality() {
	return 0;
    }
    
    public boolean isEmptyHuh() {
	// If it's a FiniteSet_Empty, then it's understood to be empty
	// Therefore, when this function is called, it's empty. 
        return true; 
    }
    
    public boolean member(int elt) {
	// If the set is empty, then nothing can be a member of it.
	return false;
    }

    public Tree remove (int elt) {
        // We can't remove anything from an empty tree
        // So return the same object
	return this;
    }

    public Tree add(int elt) {
        // The empty set plus an element is a new tree
        // with just that element
        return new FiniteSet(elt);
    }
    
    public Tree union(Tree u) {
        // The empty set plus a new tree is just the new tree
        return u;
    }
    
    public Tree inter(Tree u) {
        // The empty set can't have anything in common
        // So return an empty set
		return empty();  
        }
    
    public Boolean equal (Tree u) {
        // If they are both empty -> both have the same size
        // Then they are the same
        return u.cardinality() == this.cardinality();
    }
    
    public Tree diff(Tree u) {
        // Everything in u isn't in an empty set
        return u;
    }
    
    public Boolean subset (Tree u) {
        // The empty set is a subset of everything
        return true;
    }
    
    public void printAllElements() {
        System.out.print("E");
    }
}
