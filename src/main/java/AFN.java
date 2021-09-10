
import AnalizadorLexico.Estado;
import AnalizadorLexico.Transicion;
import java.util.HashSet;


public class AFN {
    
    //CONJUNTO DE AUTOMATAS ASOCIADOS A LAS ER
    public static HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    Estado EdoIni;
    HashSet<Estado> EdosAFN = new HashSet<Estado>();
    HashSet<Estado> EdosAcept = new HashSet<Estado>();
    //EMPLEADA AL UNIR LOS N AUTOMATAS
    boolean seAgregoAFNUnionLexico;
    public int IdAFN;
    
    //CONSTRUCTOR
    public AFN(){
        IdAFN = 0;
        EdoIni = null;
        EdosAFN.clear();
        EdosAcept.clear();
        seAgregoAFNUnionLexico = false;
    }
    
    //METODOS

    
            
            
            
            
            
            
            
    
}
