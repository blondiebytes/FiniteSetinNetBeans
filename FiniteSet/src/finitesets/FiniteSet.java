
package finitesets;
//import static finitesets.Testers.checkTree_add_cardinality;
import static finitesets.Testers.checkTree_add_member;
import static finitesets.Testers.checkTree_cardinality_remove;
import static finitesets.Testers.checkTree_diff_inter_empty_equal;
import static finitesets.Testers.checkTree_empty_isEmptyHuh;
import static finitesets.Testers.checkTree_equal_union_inter;
import static finitesets.Testers.checkTree_inter_empty;
import static finitesets.Testers.checkTree_isEmptyHuh_cardinality;
import static finitesets.Testers.checkTree_member_union;
import static finitesets.Testers.checkTree_remove_equal_add;
import static finitesets.Testers.checkTree_subset_diff;
import static finitesets.Testers.checkTree_union_subset;
import static finitesets.Testers.rndInt;
import static finitesets.Testers.rndTree;

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
        if (root == elt) {
            return true;
        } else {
            if (root > elt) {
                return this.left.member(elt);
            } else {
                return this.right.member(elt);
            }
        }
    }
	

    // (add t elt) --> finite-set
    // t : finite-set
    // elt : integer
    // Returns a set containing elt and everything in t
    public FiniteSet add(int elt) {
	// If the root is greater than the element, then add it to the left 
        // tree
        if (root == elt) {
            return this;
        } else {
            if (root > elt) {
                return new FiniteSet(this.root, this.left.add(elt), this.right);
            } 
            // If the root is less than the element, then add it to the 
            // right tree
            else {
                return new FiniteSet(this.root, this.left, this.right.add(elt));
            }
        }
    }

    
	// (remove t elt) → finite-set
	// t : finite-set
	// elt : integer
	// Returns a new FiniteSet without the element; we assume FiniteSets 
        // don't contain duplicates
    public Tree remove (int elt) {
	// If this element equals the root; then take out the root by unioning
        // the two children
	if (this.root  == elt) {
            // Turning two trees into one tree without the element
	    return this.left.union(this.right);
        } else { if (this.root > elt) {
            return new FiniteSet(this.root, this.left.remove(elt), this.right);
            } else {
           return new FiniteSet(this.root, this.left.remove(elt), 
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
            // Remove the root of this tree from our input tree u
            // U - {root}
             Tree removed = u.remove(root);
            // Remove the left and the right of this tree from our input
            // tree u
            // U - {left U right)
            return (left.union(right)).diff(removed);
            }


    // (equal t u) --> boolean
    // t : finite-set
    // u : finite-set
    // Determines if t and u contain the same elements
	public Boolean equal (Tree u) {
        return (this.subset(u) && u.subset(this));
          
//  return this.inter(u) == this.union(u);
        }


    // (subset t u) --> boolean
    // t : finite-set
    // u : finite-set
    // Determines if t is a subset of u
        public Boolean subset (Tree u) {
            return (u.member(this.root) && this.left.subset(u) 
                    && this.right.subset(u));
        }
        
        public String toString() {
            return "(" + this.root + " " + left.toString() + 
                    " " + right.toString() + ")";
        }
                
    public static void main(String[] args) {
        // Testing our random number generator
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
        System.out.println("random int = "+ rndInt(1, 50));
       
        
        System.out.println();
         System.out.println("Testing for Empty() & IsEmptyHuh?");
         System.out.println();
         for (int i = 0; i < 50; i++) {
             int randomInt = rndInt(0,1);
             checkTree_empty_isEmptyHuh(randomInt);
         }

         System.out.println();
         System.out.println("Testing: IsEmptyHuh? & Cardinality");
         System.out.println();
//        // Testing for Cardinality & IsEmptyHuh
          for (int i = 0; i < 50; i++) {
            int len = rndInt(0, 10);
            Tree l = rndTree(len);
            checkTree_isEmptyHuh_cardinality(l);
            
        }
          
         System.out.println();
        System.out.println("Testing: Cardinality & Remove");
         System.out.println();
        
       // Testing Cardinality & Remove
        for ( int i = 0; i < 50; i ++ ) {
            int elt = rndInt(0, 10);
            int len = rndInt(0, 10);
            Tree l = rndTree(len);
            checkTree_cardinality_remove( l, elt );
        }
//
         System.out.println();
        System.out.println("Testing: Remove (EQUAL) &  Add");
         System.out.println();
        
       // Testing Remove & Add
        for ( int i = 0; i < 50; i ++ ) {
            int elt = rndInt(0, 10);
            int len = rndInt(0, 10);
            Tree l = rndTree(len);
            checkTree_remove_equal_add( l, elt );
        }
       
        
        System.out.println();
        System.out.println("Testing: Add & Member");
        System.out.println();
        
        // Testing for Add & Member 
        // member (add t x) y = true <-> x = y \/ member t y = true
          for ( int i = 0; i < 50; i ++ ) {
            int elt = rndInt(0, 10);
            int elt2 = rndInt(0, 10);
            int len = rndInt(0, 10);
            Tree l = rndTree(len);
            checkTree_add_member( l, elt, elt2);
        }
          
        System.out.println();
        System.out.println("Testing: Member & Union");
        System.out.println();
          
          // Testing for Union & Member
        // member (union s s') x = true <-> member s x = true \/ member s' x = true
          for (int i = 0; i < 50; i++) {
              int elt = rndInt(0, 10);
              int len = rndInt(0,10);
              int len2 = rndInt(0, 10);
              Tree l = rndTree(len);
              Tree r = rndTree(len2);
              checkTree_member_union(l,r, elt);
          }
          
        System.out.println();
        System.out.println("Testing: Union & Subset");
        System.out.println();
          
        // Testing Union & Subset 
             for (int i = 0; i < 50; i++) {
              int len = rndInt(0,10);
              int len2 = rndInt(0, 10);
              Tree l = rndTree(len);
              Tree r = rndTree(len2);
              checkTree_union_subset(l,r);
          }
        
 
        System.out.println();
        System.out.println("Testing: Subset & Diff");
        System.out.println();
          
        // Testing Subset & Diff 
             for (int i = 0; i < 50; i++) {
              int len = rndInt(0,10);
              int len2 = rndInt(0, 10);
              Tree l = rndTree(len);
              Tree r = rndTree(len2);
              checkTree_subset_diff(l, r);
          }
        
        System.out.println();
        System.out.println("Testing: Diff (EMPTY) & Equal");
        System.out.println();
          
        // Testing: Equal (UNION) & Inter
             for (int i = 0; i < 50; i++) {
                 int len = rndInt(0,10);
              int len2 = rndInt(0, 10);
              Tree l = rndTree(len);
              Tree r = rndTree(len2);
               //Adding a random Number so we can get all the cases
                int randomNumber = rndInt(0,4);
                    if (randomNumber == 3) {
                            checkTree_diff_inter_empty_equal (l, l);
                                }
              checkTree_diff_inter_empty_equal (l, r);
          } 
      
        System.out.println();
        System.out.println("Testing: Equal (UNION) & Inter");
        System.out.println();
          
        // Testing: Equal (UNION) & Inter
             for (int i = 0; i < 50; i++) {
                 int len = rndInt(0,10);
              int len2 = rndInt(0, 10);
              Tree l = rndTree(len);
              Tree r = rndTree(len2);
               //Adding a random Number so we can get all the cases
                int randomNumber = rndInt(0,4);
                    if (randomNumber == 3) {
                            checkTree_equal_union_inter(l, l);
                                }
              checkTree_equal_union_inter(l, r);
          } 
             
             
            
        System.out.println();
        System.out.println("Testing Inter & Empty()");
        System.out.println();
          
        // Testing Inter & Empty() 
             for (int i = 0; i < 50; i++) {
              int len = rndInt(0,10);
              Tree l = rndTree(len);
              checkTree_inter_empty(l);
          }
        
        
        
        
        // Need to test cardinality, isEmptyHuh?, member, add, remove, union
        // inter, diff, equal, subset
        // Use properties to test these: 
   
        // Another Property: Union
           // if x is a member of the inter of A & B, then x is a member of A 
        // and x is a member of B
        
         // Another Property: Union
           // if x is a member of the union of A & B, then x is a member of A 
        // or x is a member of B
        
        // Complement
           /// B.diff(A) = B - A; --> B removing the elements of A
        // or x is in A.diff(B) iff x is a member of A and not of B
        
        //Adding and then removing the same element
        // get back the same tree
        
        // Testing union
        // forall x, y, s,
        // x.union(y).subset(s) = x.subet(s) && y,subset(s)
        
        // Testing union -> take cardinality before and after and test
        //forall xy
        // P(x.union(y).cardinality() <= x.cardinality() + y.cardinality();
        // Union can't double anything inside ->
        
        // Adding in intersection
        // |S U T| = |S| + |T| - |S inter T|
        
        // Look at CMPU 145 Set Theory Notes
        // If A is a subset of B, then every member of A is in B
        // Use this in a test
        // A C B => Every root in every tree of A is a member of B iff A C B
        
        // Define a standard
        // Say why it's good 
        // Then prove your program does this -> excerpt lines of code
        
        // If member then remove == get rid of duplicates
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        // A COUPLE OF TESTS
       Tree mt = empty();
//        System.out.println( "The length of it is.... " +
//                            mt.cardinality() );
       FiniteSet l5 = (new FiniteSet (5, empty(), empty()));
       FiniteSet l6 = (new FiniteSet (6, empty(), empty()));
//        System.out.println( "The length of it is.... " +
//                            l5.cardinality() );
//
//        System.out.println( "The length of mt after we remove 6 is... " +
//                            mt.remove(6).cardinality() +
//                            " should be 0");
//        System.out.println( "The length of l5 after we remove 6 is... " +
//                            l5.remove(6).cardinality() +
//                            " should be 1" );
//        System.out.println( "The length of l5 after we remove 5 is... " +
//                            l5.remove(5).cardinality() +
//                            " should be 0" );
//        
       FiniteSet l7 = (new FiniteSet (7, empty(), empty()));
       FiniteSet l8 = (new FiniteSet (8, empty(), empty()));
//        System.out.println( "This should have two elements" + l7.union(l8).cardinality());
//        System.out.println( "This should have two elements" + l8.union(l7).cardinality());
//        System.out.println("This should have size of 1 = " + l5.union(mt).cardinality());
//        System.out.println("This should have a size of 1 = " + l6.union(mt).cardinality());
//        System.out.println("This should have a size of 2 = " + l7.add(8).cardinality());
//        System.out.println("This should have a size of 1 = " + mt.add(1).cardinality());
//        System.out.println("This should be false = " + mt.member(1));
//        System.out.println("This should be true = " + l7.member(7));
//        System.out.println("This should be false = " + l8.member(7));
//        System.out.println("This should be true = " + l6.member(6));
//        
       FiniteSet l9 = (new FiniteSet (9, empty(), empty()));
       FiniteSet l10 = (new FiniteSet (10, empty(), empty()));
//        
//       // Inter 
//       System.out.println("This should be 0 = " + l9.inter(l10).cardinality());
//       System.out.println("This should be 1 = " + l9.inter(l9).cardinality());
//       System.out.println("This should be 0 = " + mt.inter(mt).cardinality());
//       System.out.println("This should be 0 = " + l9.inter(mt).cardinality());
//       
//       // Subset 
//       System.out.println("This should be false = " + l9.subset(l10));
//       System.out.println("This should be true = " + l9.subset(l9));
//       System.out.println("This should be true = " + mt.subset(l9));
//       System.out.println("This should be false = " + l9.subset(mt));
//       System.out.println("This should be true = " + mt.subset(mt));
//       
//       // Equals 
//       System.out.println("This should be false = " + l9.equals(l10));
//       System.out.println("This should be true = " + l9.equals(l9));
//       System.out.println("This should be false = " + l9.equals(mt));
//       System.out.println("This should be false = " + mt.equals(l9));
//       System.out.println("This should be true = " + mt.equals(mt));
//      
//       
//       
}
//    
    //      
//        System.out.println();
//        System.out.println("Testing: Cardinality & Add");
//         System.out.println();
////        
////        // Testing Cardinality & Add
//        for (int i = 0; i < 50; i++) {
//            int elt = rndInt(0, 10);
//            int len = rndInt(0, 10);
//            Tree l = rndTree(len);
//            checkTree_cardinality_add(l, elt);
//            
//        }
////       
    
    
//        System.out.println();
//        System.out.println("Testing Cardinality & Union");
//        System.out.println();
//          for ( int i = 0; i < 50; i ++ ) {
//            int len = rndInt(0, 10);
//            int len2 = rndInt(0, 10);
//            Tree l = rndTree(len);
//            Tree r = rndTree(len2);
//            checkTree_union_cardinality( l, r);
//        }
//         
        
        
//        System.out.println();
//        System.out.println("Basic Testing for Add and Member");
//        System.out.println();
         
//         // Testing Add & Member
//         for ( int i = 0; i < 50; i ++ ) {
//            int elt = rndInt(0, 10);
//            int len = rndInt(0, 10);
//            Tree l = rndTree(len);
//            checkTree_add_member( l, elt );
//        }
}
