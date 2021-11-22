package AnalizadorLexico;

import AnalizadorLexico.ClaseNodo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

public class DescRegGram_Gram {
    public String Gramatica;
    public AnalizadorLexico L;
    public ElemArreglo[] ArrReglas = new ElemArreglo[100];
    public int NumReglas = 0;
    
    HashSet<String> Vn = new HashSet<String>();
    HashSet<String> Vt = new HashSet<String>();

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
        AtomicReference<String> refSimbolo = new AtomicReference<String>();
        refSimbolo.set(Simbolo);
        if(LadoIzquierdo(refSimbolo)){
            Vn.add(Simbolo);
            token = L.yylex();
            if(token == TokensGram_Gram.FLECHA)
                if(LadosDerechos(Simbolo))
                    return true;
        }
        return false;
    }
    
    boolean LadoIzquierdo(AtomicReference<String> refSimbolo){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
            refSimbolo.set(L.Lexema);
            return true;
        }
        return false;
    }
    boolean LadosDerechos(String Simbolo){
        if(LadoDerecho(Simbolo))
            if(LadosDerechosP(Simbolo))
                return true;
        return false;
    }
    boolean LadosDerechosP(String Simbolo){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.OR){
            if(LadoDerecho(Simbolo))
                if(LadosDerechosP(Simbolo))
                return true;
            return false;
        }
        L.UndoToken();
        return true;
    }
    boolean LadoDerecho(String Simbolo){
        ClaseNodo elem = new ClaseNodo();
        ArrayList<ClaseNodo> Lista = new ArrayList<ClaseNodo>();
        AtomicReference<ArrayList> refLista = new AtomicReference<ArrayList>();
        refLista.set(Lista);
        Lista.clear();
        if(SecSimbolos(refLista)){
            ArrReglas[NumReglas] = new ElemArreglo();
            ArrReglas[NumReglas].InfSimbolo = new ClaseNodo(Simbolo);
            ArrReglas[NumReglas++].ListaLadoDerecho = Lista;
            return true;
        }
        return false;
    }
    boolean SecSimbolos(AtomicReference<ArrayList> refLista){
        int token; 
        ClaseNodo N;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
            N = new ClaseNodo(L.Lexema);
            if(SecSimbolosP(refLista)){
                refLista.get().add(0,N);
                return true;
            }
        }
        return false;
    }
    boolean SecSimbolosP(AtomicReference<ArrayList> refLista){
        int token;
        ClaseNodo N;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
           N = new ClaseNodo(L.Lexema);
            if(SecSimbolosP(refLista)){
                refLista.get().add(0,N);
                return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    void IdentificarTerminales(){
        int i=0;
        for(i=0; i<NumReglas; i++){
            for(ClaseNodo N : ArrReglas[i].ListaLadoDerecho){
                if(!Vn.contains(N.Simbolo)){
                    N.Terminal = true;
                    Vt.add(N.Simbolo);
                }
            }
        }
    }
}
