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
        float v; //Resultado de la evalucion
        String Postfijo="";    // resultado de la conversion a postfijo
        v = (float)0;
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
    
    
    boolean Ep( float v, String Post){
        int Token;
        float v2 = 0;
        String Post2 = "";
        Token = L.yylex();
        if(Token==10||Token==20){//+ o - //20 30
            if(T(v2, Post2)){
                v = v + (Token == 10 ? v2 : -v2);//20
                Post = Post + " "+Post2+" " +(Token==10?"+":"-");//20
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
    
    boolean Tp( float v,  String Post){
        int Token;
        float v2=0;
        String Post2 = "";
        Token = L.yylex();
        if(Token == 30 || Token==40){// * o / //40 50
            if(F( v2, Post2)){
                v = v*(Token==30?v2:1/v2);
                Post = Post +" "+Post2+" "+(Token == 30?"*":"/");//40
                if(Tp( v,  Post))
                    return true;
            }
        return false;
        }
        L.UndoToken();
        return true;
    }
    
    boolean F( float v, String Post){
        int Token;
        Token = L.yylex();
        switch(Token){
            case 50: //Parentesis izquierdo//60
                if(E( v,  Post)){
                    Token = L.yylex();
                    if(Token == 60) //Parentesis derecho//70
                        return true;
                }
                return false;
            case 10: //NUM
                v = Float.parseFloat(L.Lexema);
                
                return true;
        }
        return false;
    }   
  }

