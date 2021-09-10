package AnalizadorLexico;

public class Transicion {
    //2 Simbolos para tener la opcion de manejar rangos
    private char SimbInf1;
    private char SimbSup1;
    private Estado Edo;
    //CONSTRUCTOR PARA UN SOLO SIMBOLO
    public Transicion(char simb, Estado e){
            SimbInf1 = simb;
            SimbSup1 = simb;
            Edo = e;
    }
    //CONSTRUCTOR PARA RANGO DE CARACTERES
    public Transicion(char simb1, char simb2, Estado e){
            SimbInf1 = simb1;
            SimbSup1 = simb2;
            Edo = e;
    }
    //CONSTRUCTOR PARA DEFAULT
     public Transicion(){
            Edo = null;
    }
     
    //METODOS SET
     public void SetTransicion(char s1,char s2, Estado e){
         SimbInf1 = s1;
         SimbSup1 = s2;
         Edo = e;
     }
    
     public void SetTransicion(char s, Estado e){
         SimbInf1 = s;
         SimbSup1 = s;
         Edo = e;
         return;
     }
     public char getSimbInf(){
        return SimbInf1; 
     }
     
     public void setSimbInf(char value){
         SimbInf1 = value;
     }
     
     public char getSimbSup(){ 
        return SimbSup1; 
     }
     
     public void setSimbSup(char value){
         SimbSup1 = value;
     }
     
     //METODO QUE DEVUELVE EL ESTADO HACIA EL QUE SE HACE LA TRANSICION CON CIERTO SIMBOLO
     public Estado GetEdoTrans(char s){
         if(SimbInf1 <= s && s <= SimbSup1)
             return Edo;
         return null;
     }  
    
}
