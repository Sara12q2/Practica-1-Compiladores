
package AnalizadorLexico;

import java.util.HashSet;


public class ConjIj {
      public int j;
        public HashSet<Estado> ConjI;
        public int[] TransicionesAFD;

        public ConjIj(int CardAlf) {
            j = -1;
            ConjI = new HashSet<Estado>();
            ConjI.clear();
            TransicionesAFD = new int[CardAlf + 1];
            for (int k = 0; k <= CardAlf; k++) {
                TransicionesAFD[k] = -1;
            }

        }

    public HashSet<Estado> getConjI() {
        return ConjI;
    }

    public void setConjI(HashSet<Estado> ConjI) {
        this.ConjI = ConjI;
    }
        
    
}
