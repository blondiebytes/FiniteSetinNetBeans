
package finitesets;

import static finitesets.FiniteSet.empty;
import java.util.Random;

public class Testers {
    
    public static int rndInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max-min)) + 1 + min;
    }
    
    public static Tree rndTree(int branchNumber) {
        if (branchNumber == 0) {
            return empty();
        } else {
            return new FiniteSet(rndInt(0, 50),rndTree(branchNumber-1), rndTree(branchNumber-1));
        }
    }
    
    }
  

