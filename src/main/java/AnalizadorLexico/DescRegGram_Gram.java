package AnalizadorLexico;

import java.io.IOException;

public class DescRegGram_Gram {
    public String Gramatica;
    public AnalizadorLexico L;
    public DescRegGram_Gram(String sigma, String FileAFD, int IdentifAFD) throws IOException{
        Gramatica  = sigma;
        L = new AnalizadorLexico(Gramatica, FileAFD, IdentifAFD);
    }
    public DescRegGram_Gram(String FileAFD,int IdentifAFD) throws IOException{
        L = new AnalizadorLexico(FileAFD,IdentifAFD);
    }
    public boolean SetGramatica(String sigma){
        Gramatica  = sigma;
        L.SetSigma(sigma);
        return true;
    }
    public boolean AnalizarGrámatica(){
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
        if(LadoIzquierdo()){
            token = L.yylex();
            if(token == TokensGram_Gram.FLECHA)
                if(LadosDerechos())
                    return true;
        }
        return false;
    }
    boolean LadoIzquierdo(){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO)
            return true;
        return false;
    }
    boolean LadosDerechos(){
        if(LadoDerecho())
            if(LadosDerechosP())
                return true;
        return false;
    }
    boolean LadosDerechosP(){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.OR){
            if(LadosDerechosP())
                return true;
            return false;
        }
        L.UndoToken();
        return true;
    }
    boolean LadoDerecho(){
        if(SecSimbolos())
            return true;
        return false;
    }
    boolean SecSimbolos(){
        int token; 
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
            if(SecSimbolosP())
                return true;
        }
        return false;
    }
    boolean SecSimbolosP(){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.SIMBOLO){
            if(SecSimbolosP())
                return true;
            return false;
        }
        L.UndoToken();
        return true;
    }
}
