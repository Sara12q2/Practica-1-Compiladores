package AnalizadorLexico;


import AnalizadorLexico.Estado;
import AnalizadorLexico.SimbolosEspeciales;
import AnalizadorLexico.Transicion;
import static java.lang.System.in;
import java.util.HashSet;


public class AFN {
    
    //CONJUNTO DE AUTOMATAS ASOCIADOS A LAS ER
    public static HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    Estado EdoIni;
    HashSet<Estado> EdosAFN = new HashSet<Estado>();
    HashSet<Estado> EdosAcept = new HashSet<Estado>();
    HashSet<String> Alfabeto = new HashSet<String>();
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

//CONCATENACION---------------------------------------------------------------
    public AFN ConcAFN(AFN f2){
        //FUSIONANDO EL EDO DE ACEPTACION DEL THIS Y EL INICIAL DE F2, CONSERVAMOS EL EDO DE ACEPTACION DE THIS
        for(Transicion t:f2.EdoIni.getTrans()){
            for(Estado e : this.EdosAcept){
                e.getTrans().add(t);
            // e DEJA DE SER UN ESTADO DE ACEPTACION
                e.setEdoAcept(false);
            }
        //ELIMINANDO EL EDO INICIAL DE F2 DE LA LISTA DE ESTADOS DE F2
        f2.EdosAFN.remove(f2.EdoIni);
        //ACTUALIZANDO EL AUTOMATA NUEVO QUE RESULTO DE CONCATENAR
        this.EdosAcept = f2.EdosAcept;
       // this.EdosAFN.UnionWith(f2.EdosAFN);
        //this.Alfabeto.UnionWith(f2.Alfabeto);
        }
        return this;
    }
//*CONCATENACION-------------------------------------------------------------
    
//CERRADURA TRANSITIVA -------------------------------------------------------    
    public AFN CerrPos(){
        //CREANDO UN NUEVO EDO DE ACEPTACION Y UN NUEVO EDO INICIAL
        Estado e_ini = new Estado();
        Estado e_fin = new Estado();
    //AGREGANDO TRANSICION EPSILON DEL NUEVO EDO INICIAL AL ANTIGUO (EdoIn)
    e_ini.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON,EdoIni));
    for(Estado e: EdosAcept){
       //!!! Transicion del antiguo EDO DE ACEPTACION AL EDO FINAL
       e.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON, e_fin));
       //!!! TRANSICIOPN DEL ANTIGUO EDO DE ACEPTACION AL ANTIGUO EDO INICIAL
       e.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON, EdoIni));
       //!!! QUITANDOLE LA CARACTERISTICA DE EDO DE ACEPTACION A E
       e.setEdoAcept(false);
       
    }
    //ASIGNANDO EL NUEVO EDO DE ACEPTACION
    EdoIni = e_ini;
    e_fin.setEdoAcept(true);
    EdosAcept.clear();
    //AGREGANDO LOS NUEVOS ESTADOS
    EdosAcept.add(e_fin);
    EdosAFN.add(e_ini);
    EdosAFN.add(e_fin);
    return this;
    }
    
//* CERRADURA TRANSITIVA------------------------------------------------------
            
//OPCIONAL -------------------------------------------------------------------
    public AFN Opcional(){
    //CREANDO UN NUEVO EDO DE ACEPTACION Y UN NUEVO EDO INICIAL
        Estado e_ini = new Estado();
        Estado e_fin = new Estado();
    //AGREGANDO TRANSICION EPSILON DEL NUEVO EDO INICIAL AL ANTIGUO (EdoIn)
    e_ini.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON,EdoIni));
    e_ini.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON, e_fin));
    for(Estado e: EdosAcept){
       e.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON, e_fin));
       e.setEdoAcept(false);  
    }
    //ASIGNANDO EL NUEVO EDO DE ACEPTACION
    EdoIni = e_ini;
    e_fin.setEdoAcept(true);
    EdosAcept.clear();
    EdosAcept.add(e_fin);
    EdosAFN.add(e_ini);
    EdosAFN.add(e_fin);
    return this;
    } 
//* OPCIONAL -----------------------------------------------------------------
            
            
//METODOS SET Y GET-----------------------------------------------------------            
    public HashSet<AFN> getListaAFNs(){
        return ConjDeAFNs;
    }
    
    public int getIdAFN(AFN a){
        return a.IdAFN;
    }
    
    public void agregarAFNaLista(AFN a){
        ConjDeAFNs.add(a);
    }
//*METODOS SET Y GET----------------------------------------------------------    
}
