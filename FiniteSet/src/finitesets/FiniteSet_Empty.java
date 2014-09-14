
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
	return this;
    }

    public Tree add(int elt) {
        return new FiniteSet(elt);
    }
    
    public Tree union(Tree u) {
        return u;
    }
    
    public Tree inter(Tree u) {
		return empty();  
        }
}
