
package finitesets;


public interface Tree {
    public int cardinality();
    public  boolean isEmptyHuh();
    public  boolean member(int elt); 
    public Tree remove (int elt);
    public Tree add(int elt);
    public Tree union(Tree u);
    public Tree inter(Tree u);
    public Tree diff(Tree u);
    public Boolean equal (Tree u);
    public Boolean subset (Tree u);
           
    
}
