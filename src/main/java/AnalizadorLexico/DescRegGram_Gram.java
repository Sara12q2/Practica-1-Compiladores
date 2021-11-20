package AnalizadorLexico;

import AnalizadorLexico.ClaseNodo.ElemArreglo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class DescRegGram_Gram {
    public String Gramatica;
    public AnalizadorLexico L;
    public ElemArreglo[] ArrReglas = new ElemArreglo[100];
    public int NumReglas = 0;
    
    HashSet<ClaseNodo> Vn = new HashSet<ClaseNodo>();
    HashSet<ClaseNodo> Vt = new HashSet<ClaseNodo>();

    public DescRegGram_Gram(String sigma, String FileAFD, int IdentifAFD) throws IOException{
        Gramatica  = sigma;
        L = new AnalizadorLexico(Gramatica, FileAFD, IdentifAFD);
        Vn.clear();
        Vt.clear();
    }
    public DescRegGram_Gram(String FileAFD,int IdentifAFD) throws IOException{
        L = new AnalizadorLexico(FileAFD,IdentifAFD);
        Vn.clear();
        Vt.clear();
    }
    
    public boolean SetGramatica(String sigma){
        Gramatica = sigma;
        L.SetSigma(sigma);
        return true;
    }
    
    public boolean AnalizarGramatica(String sigma){
        int token;
        if(G()){
           token = L.yylex();
           if(token == 0){
               return true;
           }
        }
        return true;
    }
    
    public boolean G(){
      if(ListaReglas())
        return true;
      return false;
    }
    
    public boolean ListaReglas(){
        int token;
        if(Reglas()){
            token = L.yylex();
            if(token == TokensGram_Gram.PC)
                if(ListaReglasP())
                    return true;
        }
        return false;
    }
    
    boolean ListaReglasP(){
        int token;
        ClassEstadoAnalizadorLexico e;
        e = L.GetEdoAnalizLexico();
        if(Reglas()){
            token = L.yylex();
            if(token == TokensGram_Gram.PC){
                if(ListaReglasP())
                    return true;
            }
            return false;
        }
        //epsilon
        L.SetEdoAnalizLexico(e);
        return true;
    }
    boolean Reglas(){
        int token;
        String Simbolo = "";
        if(LadoIzquierdo(ref Simbolo)){
            Vn.add(new ClaseNodo(Simbolo, false));
            token = L.yylex();
            if(token == TokensGram_Gram.FLECHA)
                if(LadosDerechos(Simbolo))
                    return true;
        }
        return false;
    }
    boolean LadoIzquierdo(ref String Simbolo){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
            Simbolo = L.Lexema;
            return true;
        }
        return false;
    }
    boolean LadosDerechos(String Simbolo){
        if(LadoDerecho())
            if(LadosDerechosP())
                return true;
        return false;
    }
    boolean LadosDerechosP(){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.OR){
            if(LadoDerechos(Simbolo))
                if(LadosDerechosP(Simbolo))
                return true;
            return false;
        }
        L.UndoToken();
        return true;
    }
    boolean LadoDerecho(String Simbolo){
        ArrayList<ClaseNodo> Lista = new ArrayList<ClaseNodo>();
        Lista.clear();
        if(SecSimbolos(ref Lista)){
            ArrReglas[NumReglas] = new ElemArreglo();
            ArrReglas[NumReglas].InfSimbolo = new ClaseNodo(Simbolo);
            ArrReglas[NumReglas++].ListaLadoDerecho = Lista;
            return true;
        }
        return false;
    }
    boolean SecSimbolos(ref ArrayList<ClaseNodo> Lista){
        int token; 
        ClaseNodo N;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
            N = new ClaseNodo(L.Lexema);
            if(SecSimbolosP(ref Lista)){
                Lista.add(0,N);
                return true;
            }
        }
        return false;
    }
    boolean SecSimbolosP(ref ArrayList<ClaseNodo> Lista){
        int token;
        ClaseNodo N;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
           N = new ClaseNodo(L.Lexema);
            if(SecSimbolosP(ref Lista)){
                Lista.add(0,N);
                return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
}
