//METODOS ASOCIADOS A LAS REGLAS GRAMATICALES
package AnalizadorLexico;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

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
        L = new AnalizadorLexico(Expresion, FileAFD, IdentifAFD);
    }
    
    public EvaluadorExpr(String FileAFD,int IdentifAFD) throws IOException{
        L = new AnalizadorLexico(FileAFD, IdentifAFD);
    }
    
    public void SetExpresion(String sigma){
        Expresion = sigma;
        L.SetSigma(sigma);
    }
    
    public boolean IniEval(){
        int Token;
        float v=0; //Resultado de la evalucion
        String Postfijo="";    // resultado de la conversion a postfijo
//        AtomicReference<v> refF = new AtomicReference<v>();
//        AtomicReference<Postfijo> refF = new AtomicReference<Postfijo>();
        if(E(v, Postfijo)){
            Token = L.yylex();
            if(Token == 0){
                this.result = v;
                this.ExprPost = Postfijo;
                return true;
            }
        }
        return false;
    }
    
    boolean E(float v, String Post){
        if(T(v,  Post))
            if(Ep( v,  Post))
                return true;
        return false;
    }
    
    
    boolean Ep(float v, String Post){
        int Token;
        float v2 = 0;
        String Post2 = "";
        Token = L.yylex();
        if(Token==10||Token==20){//+ o - 
            if(T(v2, Post2)){
                v = v + (Token == 20 ? v2 : -v2);//a=10 d=20
                Post = Post + " "+Post2+" " +(Token==20? "+" : "-");//a=10 d=20
                if(Ep( v, Post))
                    return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    
    boolean T(float v, String Post){
        if(F( v, Post))
            if(Tp( v,  Post))
                return true;
        return false;
    }
    
    boolean Tp(float v,  String Post){
        int Token;
        float v2=0;
        String Post2 = "";
        Token = L.yylex();
        if(Token == 30 || Token==40){// * o / 
            if(F( v2, Post2)){
                v = v*(Token==40 ? v2:1/v2);
                Post = Post +" "+Post2+" "+(Token == 40?"*":"/");//40
                if(Tp( v,  Post))
                    return true;
            }
        return false;
        }
        L.UndoToken();
        return true;
    }
    
    boolean F(float v, String Post){
        int Token;
        Token = L.yylex();
        switch(Token){
            case 50: //Parentesis izquierdo
                if(E( v,  Post)){
                    Token = L.yylex();
                    if(Token == 60) //Parentesis derecho
                        return true;
                }
                return false;
            case 70: //NUM
                v = Float.parseFloat(L.Lexema);
                Post =L.Lexema;
                return true;
        }
        return false;
    }   
  }

