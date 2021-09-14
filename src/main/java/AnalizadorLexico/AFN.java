package AnalizadorLexico;


import AnalizadorLexico.Estado;
import AnalizadorLexico.SimbolosEspeciales;
import AnalizadorLexico.Transicion;
import static java.lang.System.in;
import java.util.HashSet;
import java.util.Stack;


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
    
    //BASICO---------------------------------------------------------------
    public AFN crearAFNBasico(char s){
        
        Transicion t;
        
        Estado estadoInicial, estadoFinal; //Crea 2 variables para los estados Inicial y Final
        estadoInicial=new Estado();
        estadoFinal=new Estado();
        t=new Transicion(s,estadoFinal);
        estadoInicial.getTrans().add(t); 
        estadoFinal.setEdoAcept(true);
        Alfabeto.add(String.valueOf(s));
        EdoIni=estadoInicial;
        EdosAFN.add(estadoInicial);
        EdosAFN.add(estadoFinal);
        EdosAcept.add(estadoFinal);
        seAgregoAFNUnionLexico=false;
        return this;
    }
    
    public AFN crearAFNBasico(char s1, char s2){
        char i;
        Transicion t;
        
        Estado estadoInicial, estadoFinal; //Crea 2 variables para los estados Inicial y Final
        estadoInicial=new Estado();
        estadoFinal=new Estado();
        t=new Transicion(s1,s2,estadoFinal);
        estadoInicial.getTrans().add(t); 
        estadoFinal.setEdoAcept(true);
        
        for(i=s1;i<=s2;i++)
        Alfabeto.add(String.valueOf(i));
        EdoIni=estadoInicial;
        EdosAFN.add(estadoInicial);
        EdosAFN.add(estadoFinal);
        EdosAcept.add(estadoFinal);
        seAgregoAFNUnionLexico=false;
        return this;
    }
//BASICO---------------------------------------------------------------
    
//UNION---------------------------------------------------------------
   public AFN UnirAFN(AFN f2){
    Estado estadoNIni=new Estado();
    Estado estadoNFin=new Estado();
    Transicion t1=new Transicion(SimbolosEspeciales.EPSILON,this.EdoIni);
    Transicion t2=new Transicion(SimbolosEspeciales.EPSILON,f2.EdoIni);
    estadoNIni.getTrans().add(t1);
    estadoNIni.getTrans().add(t2);
    for(Estado e : this.EdosAcept){
       e.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON, estadoNFin));
       e.setEdoAcept(false);
   }
    
    for(Estado e : f2.EdosAcept){
       e.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON, estadoNFin));
       e.setEdoAcept(false);
   }
    
    this.EdosAcept.clear();
    f2.EdosAcept.clear();
    this.EdoIni=estadoNIni;
    estadoNFin.setEdoAcept(true);
    this.EdosAcept.clear();
    this.EdoIni=estadoNIni;
    estadoNFin.setEdoAcept(true);
    this.EdosAcept.add(estadoNFin);
    
//    this.EdosAFN.Concat(f2.Alfabeto);
    return this;
    
   }
    
    
    
    
//UNION---------------------------------------------------------------

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
    
//* Cerradura Epsilon -----------------------------------------------------------------
    public HashSet<Estado>CerraduraEpsilon(Estado e){
        HashSet<Estado> R=new HashSet<Estado>();
        Stack<Estado>S= new Stack<Estado>();
        Estado aux, Edo;
        R.clear();
        S.clear();
        S.push(e);
        
        while(S.size()!=0){
            aux=S.pop();
            R.add(aux);
            for(Transicion t: aux.getTrans()){
                if((Edo=t.GetEdoTrans(SimbolosEspeciales.EPSILON))!=null){
                    if(!R.contains(Edo))
                        S.push(Edo);
                }
            
            }
        }
    
    return R;
    }
    
    
    public HashSet<Estado>CerraduraEpsilon(HashSet<Estado>ConjEdos){
        HashSet<Estado> R=new HashSet<Estado>();
        Stack<Estado>S= new Stack<Estado>();
        Estado aux, Edo;
        R.clear();
        S.clear();
        for(Estado e : ConjEdos)
        S.push(e);
        
        while(S.size()!=0){
            aux=S.pop();
            R.add(aux);
            for(Transicion t: aux.getTrans()){
                if((Edo=t.GetEdoTrans(SimbolosEspeciales.EPSILON))!=null){
                    if(!R.contains(Edo))
                        S.push(Edo);
                }
            
            }
        }
    
    return R;
    }
    
//* Cerrradura Epsilon-----------------------------------------------------------------

//* MOVER-----------------------------------------------------------------
    public HashSet<Estado>Mover(Estado Edo,char Simb){
        HashSet<Estado>C= new HashSet<Estado>();
        Estado Aux;
        C.clear();
        
        for(Transicion t: Edo.getTrans()){
            Aux=t.GetEdoTrans(Simb);
            if(Aux!=null)
                C.add(Aux);
        
        }
        return C;
    }
    
    public HashSet<Estado>Mover(HashSet<Estado>Edos,char Simb){
        HashSet<Estado>C= new HashSet<Estado>();
        Estado Aux;
        C.clear();
        
        for(Estado Edo: Edos)
            for(Transicion t : Edo.getTrans()){
            Aux=t.GetEdoTrans(Simb);
            if(Aux!=null)
                C.add(Aux);
        
            }
        return C;
    }
    
//* MOVER-----------------------------------------------------------------
    
//* Ir_A-----------------------------------------------------------------
   
//* Ir_A-----------------------------------------------------------------
    
//* Union Especial AFN's-----------------------------------------------------------------
   
//* Union Especial AFN's--------------------------------------------------------------
    
//* Convertir AFNaAFD-----------------------------------------------------------------
   
//* Convertir AFNaAFD--------------------------------------------------------------
    
            
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
