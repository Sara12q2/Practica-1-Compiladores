package AnalizadorLexico;

import java.io.IOException;
import java.util.Stack;

public class AnalizadorLexico {
    int token, EdoActual, EdoTransicion;
    //CADENA A ANALIZAR, EN EL CONSTRUCTOR SE PUEDE INICIALIZAR
    String CadenaSigma;
    //SUBCADENA
    public String Lexema;
    //INDICA SI PASE POR EL ESTADO DE ACEPTACION (BANDERA)
    boolean PasoPorEdoAcept;
    int IniLexema, FinLexema, IndiceCaracterActual;
    char CaracterActual;
//EQUIVALENCIA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    Stack<Integer> Pila = new Stack<Integer>();
//EQUIVALENCIA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    AFD AutomataFD;
    public AnalizadorLexico(){
        CadenaSigma = "";
        PasoPorEdoAcept = false;
        IniLexema = FinLexema= -1;
        IndiceCaracterActual = -1;
        token = -1;
        Pila.clear();
        AutomataFD = null;
    }
    public AnalizadorLexico(String sigma,String FileAFD, int IdAFD) throws IOException{
        AutomataFD = new AFD();
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD.LeerAFDdeArchivo(FileAFD, IdAFD);      
    }
    
        public AnalizadorLexico(String sigma,String FileAFD) throws IOException{
        AutomataFD = new AFD();
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD.LeerAFDdeArchivo(FileAFD, -1);      
    }
    
    
      public AnalizadorLexico(String FileAFD, int IdAFD) throws IOException{
        AutomataFD = new AFD();
        CadenaSigma = "";
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD.LeerAFDdeArchivo(FileAFD, IdAFD);      
    }
    public AnalizadorLexico(String sigma, AFD AutFD) throws IOException{
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD = AutFD;      
    }
    
    public void SetSigma(String sigma){
        //INICIALIZANDO
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
    }
    
    //INICIO DEL ALGORITMO
    public int yylex(){
        while(true){
        Pila.push(IndiceCaracterActual);
        //YA TERMINE CON LA CADENA??
        if(IndiceCaracterActual >= CadenaSigma.length()){
            Lexema = "";
        //AL TERMINAR DE REVISAR LA CADENA SE DEVUELVE UN 0
            return SimbolosEspeciales.FIN;
        }
        IniLexema = IndiceCaracterActual;
        EdoActual = 0;
        PasoPorEdoAcept = false;
        FinLexema = -1;
        token = -1;
        //QUEDAN CARACTERES POR ANALIZAR??
        while(IndiceCaracterActual < CadenaSigma.length()){
//EQUIVALENCIA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            CaracterActual = CadenaSigma.charAt(IndiceCaracterActual);
//**EQUIVALENCIA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            EdoTransicion  = AutomataFD.TablaAFD[EdoActual][CaracterActual];
            if(EdoTransicion != -1){
                //ESTAMOS EN UN ESTADO DE ACEPTACION???
                if(AutomataFD.TablaAFD[EdoTransicion][256]!=-1){
                    PasoPorEdoAcept = true;
                    token = AutomataFD.TablaAFD[EdoTransicion][256];
                    FinLexema = IndiceCaracterActual;
                    System.out.println("Estado de aceptacion: "+CaracterActual);
                }
                IndiceCaracterActual++;
                EdoActual = EdoTransicion;
                continue;
            }
            break;
        }// NO HAY ESTADO DE ACEPTACION
        if(!PasoPorEdoAcept){
            System.out.println("Entro a no hay estado de aceptacion");
            IndiceCaracterActual = IniLexema + 1;          
            Lexema = CadenaSigma.substring(IniLexema,1);   
            token = 2000;
            return token; //ERROR
        }
        //NO HAY TRANSICION CON EL CARACTER ACTUAL PERO YA SE HABIA PASADO POR UN EDO DE ACEPTACION
        System.out.println("IniLexema : "+IniLexema+" FinLexema: "+FinLexema);
        Lexema = CadenaSigma.substring(IniLexema,FinLexema+1);
        IndiceCaracterActual = FinLexema + 1;
        if(token == SimbolosEspeciales.OMITIR)
            continue;
        else
            return token;
        }   
    }
 
    public boolean UndoToken(){
        if(Pila.size()==0)
            return false;
        IndiceCaracterActual = Pila.pop();
            return true;
        
    }  
    
//SECCIÓN TERCER PARCIAL    
    public ClassEstadoAnalizadorLexico GetEdoAnalizLexico(){
        ClassEstadoAnalizadorLexico EdoActualAnaliz = new ClassEstadoAnalizadorLexico();
        EdoActualAnaliz.CaracterActual = CaracterActual;
        EdoActualAnaliz.EdoActual = EdoActual;
        EdoActualAnaliz.EdoTransicion  = EdoTransicion;
        EdoActualAnaliz.FinLexema = FinLexema;
        EdoActualAnaliz.IndiceCaracterActual = IndiceCaracterActual;
        EdoActualAnaliz.IniLexema  = IniLexema;
        EdoActualAnaliz.Lexema = Lexema;
        EdoActualAnaliz.PasoPorEdoAcept = PasoPorEdoAcept;
        EdoActualAnaliz.token = token;
        EdoActualAnaliz.Pila = Pila;
        return EdoActualAnaliz;
    }
    
    public boolean SetEdoAnalizLexico(ClassEstadoAnalizadorLexico e){
        CaracterActual = e.CaracterActual;
        EdoActual = e.EdoActual;
        EdoTransicion = e.EdoTransicion;
        FinLexema = e.FinLexema;
        IndiceCaracterActual = e.IndiceCaracterActual;
        IniLexema = e.IniLexema;
        Lexema = e.Lexema;
        PasoPorEdoAcept  = e.PasoPorEdoAcept;
        token = e.token;
        Pila = e.Pila;
        return true;
    }

    
}





