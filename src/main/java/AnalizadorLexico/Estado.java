package AnalizadorLexico;

import java.util.HashSet;

public class Estado {
    static int ContadorIdEstado = 0;
    private int idEstado1;
    private boolean EdoAcept1;
    private int Token1;
    //CONJUNTO DE TRANSICIONES
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
    //CONSTRUCTORES
    public Estado(){
        setEdoAcept(false);
        setToken(-1);
        setIdEstado(ContadorIdEstado++);
        Trans1.clear();   
    }
    
   //METODOS SET Y GET
   public int getIdEstado(){
       return idEstado1;
       } 
    
   public void setIdEstado(int value){
       idEstado1 = value;
   }
   
   public boolean getEdoAcept(){
      return EdoAcept1;
   }
   
   public void setEdoAcept(boolean value){
      EdoAcept1 = value;
   }
   
   public int getToken(){
       return Token1;
   }
   
   public void setToken(int value){
       Token1 = value;
   }
   
   public HashSet<Transicion> getTrans(){
       return Trans1;
   } 
   
   public void setTrans(HashSet<Transicion> value){
       Trans1 = value;
   }   
}
