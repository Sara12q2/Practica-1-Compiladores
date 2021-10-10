package AnalizadorLexico;


import AnalizadorLexico.Estado;
import AnalizadorLexico.SimbolosEspeciales;
import AnalizadorLexico.Transicion;
import static java.lang.System.in;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class AFN {
    //Clase para cada uno de los conjuntos de estados del AFN que se va a ir creando
    public class ConjIj{
        //Indice del conjunto Ij
        public int j;
        //Conjunto de estados que forman a Ij
        public HashSet<Estado> ConjI;
        //Marcar las transiciones del conjunto Ij
        public int[] TransicionesAFD;
        
        //Constructor de ConjIj
            //Recibe la cardinalidad del alfabeto (Cuantos elementos tiene el alfabeto)
        public ConjIj(int CardAlf){
            //No tiene un indice asociado aún
            j=-1;
            //Conjunto de los estados
            ConjI = new HashSet<Estado>();
            ConjI.clear();
            //Arreglo de enteros del tamaño del alfabeto +1 (estado de aceptacion o -1)
            TransicionesAFD = new int[CardAlf+1];
            //Llenamos el arreglo con -1
            for(int k=0; k<= CardAlf; k++){
                TransicionesAFD[k] = -1;
            }
                    
        }
        
        //SETTERS Y GETTERS-----------------------------------------------------
            public void setJ (int newJ){
                this.j = newJ;
            }
            
            public void setConjI (HashSet<Estado> ConjIdado){
                this.ConjI = ConjIdado;
            }
        //*SETTERS Y GETTERS----------------------------------------------------
        
        
    }
    
  
    
    //CONJUNTO DE AUTOMATAS ASOCIADOS A LAS ER
    public static HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    Estado EdoIni;
    HashSet<Estado> EdosAFN = new HashSet<Estado>();
    HashSet<Estado> EdosAcept = new HashSet<Estado>();
    HashSet<String> Alfabeto = new HashSet<String>();
     HashSet<Estado> ConjI= new HashSet<Estado>();
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
        System.out.println("s1 "+s1);
        System.out.println("s2 "+s2);
        char i;
        Transicion t;
        
        Estado estadoInicial, estadoFinal; //Crea 2 variables para los estados Inicial y Final
        estadoInicial=new Estado();
        estadoFinal=new Estado();
        t=new Transicion(s1,s2,estadoFinal);
        estadoInicial.getTrans().add(t); // _ = e1.Trans.Add(t);
        estadoFinal.setEdoAcept(true);   // e2.EdoAcetpt = true
        
        for(i=s1;i<=s2;i++)
        {
            Alfabeto.add(String.valueOf(i));
        }
        EdoIni=estadoInicial;
        EdosAFN.add(estadoInicial);  //_ = EdosAFN.Add(e1)
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
    
    this.EdosAFN.containsAll(f2.Alfabeto);
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
//        this.EdosAFN.UnirAFN(f2.EdosAFN);
//        this.Alfabeto.UnionWith(f2.Alfabeto);
//EQUIVALENCIA
          this.EdosAFN.addAll(f2.EdosAFN);
          this.Alfabeto.addAll(f2.Alfabeto);
        UnirAFN(f2);
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

//CERRADURA DE KLEEN
        public AFN CerrKleen(){
    //Se crea un nuevo edo inicial y un nuevo edo de aceptación
        Estado e_ini = new Estado();
        Estado e_fin = new Estado();
    //AGREGANDO TRANSICION EPSILON DEL NUEVO EDO INICIAL AL ANTIGUO (EdoIn)
        e_ini.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON,EdoIni));
        e_ini.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON,e_fin));
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
//*CERRADURA DE KLEEN
    
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
     public HashSet<Estado>Ir_A(HashSet<Estado>Edos, char Simb){
        HashSet<Estado>C=new HashSet<Estado>();
       
        HashSet<Estado>Aux=new HashSet<Estado>();
        C.clear();
        Aux=this.Mover(Edos, Simb);
        for (Estado e : Aux){
            C=this.CerraduraEpsilon(e);
        
        }

        return C;
    }   
    
    
   
//* Ir_A-----------------------------------------------------------------
    
//* Union Especial AFN's-----------------------------------------------------------------
   public void UnionEspecialAFNs(AFN f, int Token){
       Estado e;
       if(!this.seAgregoAFNUnionLexico){
           this.EdosAFN.clear();
           this.EdosAFN.clear();
           this.Alfabeto.clear();
           e= new Estado();
           e.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON,f.EdoIni));
           this.EdoIni=e;
           this.EdosAFN.add(e);
           this.seAgregoAFNUnionLexico=true;
       }else{
           this.EdoIni.getTrans().add(new Transicion(SimbolosEspeciales.EPSILON,f.EdoIni));
           for(Estado EdoAcep: f.EdosAcept)
               EdoAcep.getToken();
           this.EdosAcept.addAll(f.EdosAcept);
           this.EdosAFN.addAll(f.EdosAFN);
           this.Alfabeto.addAll(f.Alfabeto);
          
       }
   }
//* Union Especial AFN's--------------------------------------------------------------
   
//*Indice Caracter--------------------------------------------------------------
   private int IndiceCaracter(char[] ArregloAlfabeto, char c){
       int i;
       for(i=0; i<ArregloAlfabeto.length;i++){
           if(ArregloAlfabeto[i]==c){
               return -1;
           }
       }
       return -1;
   }
//*Indice Caracter--------------------------------------------------------------
    
//* Convertir AFNaAFD-----------------------------------------------------------------
   public AFD ConvAFNaAFD(){
       int CardAlfabeto, NumEdosAFD;
       int i,j,r;
       //ARREGLO EN DONDE SE GUARDARÁ EL ALFABETO
       char[] ArrAlfabeto;
       //Ij = Indice, conjunto de estados y el arreglo de transiciones
       ConjIj Ij, Ik=null;
       boolean existe;
   
       //Conjunto de estados para guardar temporalmente el resultado del Ir_A
       HashSet<Estado>ConjAux=new HashSet<Estado>();
       //Conjunto de Ij's 
       HashSet<ConjIj>EdosAFD=new  HashSet<ConjIj>();
       //Estados sin analizar tipo Ij
       Queue<ConjIj>EdosSinAnalizar=new LinkedList<ConjIj>();
       
       EdosAFD.clear();
       EdosSinAnalizar.clear();
       
       //Obteniendo el numero de elementos en el alfabeto
       CardAlfabeto=Alfabeto.size();
       ArrAlfabeto=new char[CardAlfabeto];
       i=0;
       //Metemos cada simbolo del alfabeto en el arreglo
       for(String c: Alfabeto)
           ArrAlfabeto[i++]=c.charAt(0);
       //Contador del  estados del AFD
       j=0;
       
       
//SECCION MODIFICADA------------------------------------------------------------       
       Ij=new ConjIj(CardAlfabeto);
       Ij.setConjI(CerraduraEpsilon(this.EdoIni));
       Ij.setJ(j);
//*SECCION MODIFICADA-----------------------------------------------------------              

       EdosAFD.add(Ij);
       EdosSinAnalizar.add(Ij);
       j++;
       while (EdosSinAnalizar.size() != 0) { //Mientras se tenga estados sin analizar
           Ij = EdosSinAnalizar.remove(); //Calcula Ir_A del Ij con cada simbolo del alfabeto

           for (char c : ArrAlfabeto) {
               Ik = new ConjIj(CardAlfabeto);
               //MODIFICADO
               Ik.setConjI(Ir_A(Ij.ConjI,c));
               //*MODIFICADO
           if (Ik.ConjI.isEmpty()) {
               continue;
           }
           existe = false;

           for (ConjIj I : EdosAFD) {
               if (I.ConjI.equals(Ik.ConjI)) {
                   existe = true;
                     r = IndiceCaracter(ArrAlfabeto, c);
                     Ij.TransicionesAFD[r] = I.j;
                   break;
               }
           }
               if (!existe) {
                   Ik.j = j;
                   r = IndiceCaracter(ArrAlfabeto, c);
                   Ij.TransicionesAFD[r] = Ik.j;
                   EdosAFD.add(Ik);
                   EdosSinAnalizar.remove(Ik);
                   j++;
               }
           }
       }
       NumEdosAFD=j;
       for(ConjIj I: EdosAFD){
           ConjAux.clear();
           ConjAux.addAll(I.ConjI);
           ConjAux.addAll(this.EdosAcept);
           if(ConjAux.size()!=0)
               for(Estado EdoAcept : ConjAux){
//                   I.TransicionesAFD[CardAlfabeto]=EdoAcept.Token;
                   break;
               
               }
           else
               I.TransicionesAFD[CardAlfabeto]=-1;
       }
       AFD AutFD=new AFD();
            CardAlfabeto=CardAlfabeto;
   
        
         
        AutFD.TablaAFD= new int[EdosAFD.size()][257];  //Llena la tabla del AFD
        for(i=0; i<EdosAFD.size(); i++)
            for( j=0; j<257; j++)
        //REVISAR EQUIVALENCIA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                AutFD.TablaAFD[i][j]=-1;
        AutFD.ArrAlfabeto=new String[AutFD.CardAlfabeto];

        i=0;
        for(char c: ArrAlfabeto) //Caracteres del alfabeto en un arreglo
        AutFD.ArrAlfabeto[i++]=String.valueOf(c);
        AutFD.NumEstados=NumEdosAFD;
        AutFD.TransicionesAFD=new int[EdosAFD.size()][CardAlfabeto+1];
        
        for(ConjIj I :  EdosAFD){  //Pone las transiciones en el arreglo
            for(int columna=0; columna<=CardAlfabeto; columna++ ){
                AutFD.TransicionesAFD[I.j][columna]=I.TransicionesAFD[columna];
                if(columna!=CardAlfabeto)
                //REVISAR CONVERSION DE AutFD.ArrAlfabeto[columna]
                    AutFD.TablaAFD[I.j][Integer.parseInt(AutFD.ArrAlfabeto[columna])]=I.TransicionesAFD[columna];
                    if(columna!=CardAlfabeto)
                //REVISAR CONVERSION DE AutFD.ArrAlfabeto[columna]        
                        AutFD.TablaAFD[I.j][Integer.parseInt(AutFD.ArrAlfabeto[columna])]=I.TransicionesAFD[columna];
                    else
                        AutFD.TablaAFD[I.j][256]=I.TransicionesAFD[columna];
        }
        }
            AutFD.NumEstados=EdosAFD.size();
            return AutFD;
       
   
   }
   
//* Convertir AFNaAFD--------------------------------------------------------------
    
     
            
//METODOS SET Y GET-----------------------------------------------------------            
    public HashSet<AFN> getListaAFNs(){
        return ConjDeAFNs;
    }
    
    public int getIdAFN(AFN a){
        return a.IdAFN;
    }

    public void setIdAFN(int IdAFN) {
        this.IdAFN = IdAFN;
    }
    
    
    public void agregarAFNaLista(AFN a){
        ConjDeAFNs.add(a);
    }
    
//*METODOS SET Y GET----------------------------------------------------------    
}
