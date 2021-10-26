package AnalizadorLexico;

import java.util.Stack;

public class ClassEstadoAnalizadorLexico {
    public int token, EdoActual,EdoTransicion;
    public String Lexema;
    public boolean PasoPorEdoAcept;
    public int IniLexema, FinLexema, IndiceCaracterActual;
    public char CaracterActual;
    public Stack<Integer> Pila = new Stack<Integer>();
    public ClassEstadoAnalizadorLexico(){
        token = 0;
        EdoActual = 0;
        EdoTransicion = -1;
        Lexema = "";
        PasoPorEdoAcept  = false;
        IniLexema = -1;
        FinLexema = -1;
        IndiceCaracterActual = -1;
        CaracterActual = ' ';
        Pila.clear();
    }
    
}
