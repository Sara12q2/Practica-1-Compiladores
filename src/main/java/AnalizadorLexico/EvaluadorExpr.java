//METODOS ASOCIADOS A LAS REGLAS GRAMATICALES
package AnalizadorLexico;

import java.io.IOException;

public class EvaluadorExpr {
 String Expresion;
 public float result;
 //RESULTADO DE CONVERSION DE INFIJO A POSTFIJO
 public String ExprPost;
 public AnalizadorLexico L;
    
    public EvaluadorExpr(String sigma, AFD AutFD) throws IOException{
        Expresion = sigma;
        L = new AnalizadorLexico(Expresion, AutFD);
    }
    
    public EvaluadorExpr(String sigma, String FileAFD,int IdentifAFD) throws IOException{
        Expresion = sigma;
        L = new AnalizadorLexico(Expresion, FileAFD,IdentifAFD);
    }
    
    public EvaluadorExpr(String FileAFD,int IdentifAFD) throws IOException{
        L = new AnalizadorLexico(Expresion, FileAFD,IdentifAFD);
    }
    
    public void SetExpresion(String sigma){
        Expresion = sigma;
        L.SetSigma(sigma);
    }
    
    public boolean IniEval(){
        int Token;
        float v;
        String Postfijo="";
        v = (float)0;
        if(E(ref v, ref Postfijo)){
            Token = L.yylex();
            if(Token == 0){
                this.result = v;
                this.ExprPost = Postfijo;
                return true;
            }
        }
        return false;
    }
    
    boolean Ep(ref float v, ref String Post){
        int Token;
        float v2 = 0;
        String Post2 = "";
        Token = L.yylex();
        if(Token==10||Token==20){//+ o -
            if(T(ref v2, ref Post2)){
                v = v + (Token == 10 ? v2 : -v2);
                Post = Post + " "+Post2+" " +(Token==10?"+":"-");
                if(Ep(ref v, ref Post))
                    return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    
    boolean T(ref V, ref Post){
        if(F(ref V, ref Post))
            if(Tp(ref v, ref Post))
                return true;
        return false;
    }
    
    boolean Tp(ref float v, ref String Post){
        int Token;
        float v2=0;
        String Post2 = "";
        Token = L.yylex();
        if(Token == 30 || Token==40){// * o /
            if(F(ref v2, ref Post2)){
                v = v*(Token==30?v2:1/v2);
                Post = Post +" "+Post2+" "+(Token == 30?"*":"/");
                if(Tp(ref v, ref Post))
                    return true;
            }
        return false;
        }
        L.UndoToken();
        return true;
    }
    
    boolean F(ref float v, ref String Post){
        int Token;
        Token = L.yylex();
        switch(Token){
            case 50: //Parentesis izquierdo
                if(E(ref v, ref Post)){
                    Token = L.yylex();
                    if(Token == 60) //Parentesis derecho
                        return true;
                }
                return false;
            case 70: //NUM
                v = float.Parse(L.Lexema);
                return true;
        }
        return false;
    }   
  }
}
