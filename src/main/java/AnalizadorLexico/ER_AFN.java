package AnalizadorLexico;
import java.io.IOException;
        
public class ER_AFN {
    
    //IMPLEMENTAR DESCENSO RECURSIVO PARA LAS E.R
   String ExprRegular;
   //RESULTADO DEL ANÁLISIS
   public AFN result;
   public AnalizadorLexico L;
   //SIGMA = CADENA A ANALIZAR
   public ER_AFN(String sigma, AFD AutFD) throws IOException{
       ExprRegular = sigma;
       L = new AnalizadorLexico(ExprRegular, AutFD);
   }
   public ER_AFN(String sigma, String FileAFD, int IdentifAFD) throws IOException{
       ExprRegular = sigma;
       L = new AnalizadorLexico(ExprRegular, FileAFD,IdentifAFD);
   }
   public ER_AFN(String FileAFD, int IdentifAFD) throws IOException{
       L = new AnalizadorLexico(FileAFD,IdentifAFD);
   }
   public void SetExpresion(String sigma){
       ExprRegular = sigma;
       L.SetSigma(sigma);
   }
  //inicia la obtencion del automata 
  //USA EQUIVALENCIA 
   public boolean IniConversion(){
       int Token;
//      AFN[] ref = new AFN[1];    
      AFN f = new AFN();
//      ref[0] = f;
       if(E(f)){
           Token = L.yylex();
           if(Token==0){
               this.result = f;
               return true;
           }
       }
       return false;
   }
   //USA EQUIVALENCIA
   public boolean E(AFN f){
//       AFN[] ref = new AFN[1];
//       ref[0] = f;
       if(T(f)){
           if(Ep(f)){
               return true;
           }
       }
        return false;
   }
   
   public boolean Ep(AFN f){
       int Token;
       AFN f2 = new AFN();
       Token = L.yylex();
       if(Token==10){ //OR
           if(T(f2)){
               f.UnirAFN(f2);
               if(Ep(f));
                return true;
           }
           return false;
       }
       L.UndoToken();
       return true;
   }
   
   boolean T(AFN f){
       if(C(f))
           if(Tp(f))
               return true;
       return false;
   }
   
   public boolean Tp(AFN f){
       int Token;
       AFN f2 = new AFN();
       
       Token = L.yylex();
       if(Token==20) // concatenacion
       {
           if(C(f2)){
               f.ConcAFN(f2);
               if(Tp(f))
                   return true;
           }
           return false;
       }
       L.UndoToken();
       return true;
   }
   
   public boolean C(AFN f){
       if(F(f))
           if(Cp(f))
               return true;
       return false;
   }
   
   public boolean Cp(AFN f){
       int Token;
       Token = L.yylex();
       switch(Token){
           case 30: //CERRADURA TRANSITIVA
               f.CerrPos();
               if(Cp(f))
                   return true;
               return false;
           case 40: //CERRADURA KLEEN
               f.CerrKleen();
               if(Cp(f)){
                   return true;
               }
               return false;
           case 50: //OPCIONAL
               f.Opcional();
               if(Cp(f))
                   return true;
               return false;
       }
       L.UndoToken();
       return true;
   }
   
   public boolean F(AFN f){
       int Token;
       char simbolo1, simbolo2;
       Token = L.yylex();
       switch(Token){
           case 60: //PAR_IZQ
               if(E(f)){
                   Token = L.yylex();
                   if(Token==70) //PAR_DER
                       return true;
               }
               return false;
           case 80: //CORCHETE IZQ
               Token = L.yylex();
               if(Token==110){//SIMBOLO
                   simbolo1 = (L.Lexema.charAt(0)=='\\')?L.Lexema.charAt(1):L.Lexema.charAt(0);
                   Token = L.yylex();
                   if(Token == 100){ //GUION
                       Token = L.yylex();
                       if(Token == 110){ //SIMBOLO
                           simbolo2 = (L.Lexema.charAt(0) == '\\')?L.Lexema.charAt(1): L.Lexema.charAt(0);
                           Token  =L.yylex();
                           if(Token==90){ //CORCHETE DERECHO
//                               f = new AFN();
                                System.out.println("Simbolo1: "+simbolo1+" Simbolo2"+simbolo2);
                               f.crearAFNBasico(simbolo1,simbolo2);
                               return true;
                           }
                       }
                   }
               }
               return false;
           case 110: //SIMBOLO
               simbolo1 = (L.Lexema.charAt(0)== '\\')?L.Lexema.charAt(1) : L.Lexema.charAt(0);
//               f = new AFN();
               f.crearAFNBasico(simbolo1);
               return true;
       }
       return false;
   }
   
}


