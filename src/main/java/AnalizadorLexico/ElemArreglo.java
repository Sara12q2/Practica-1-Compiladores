package AnalizadorLexico;
import java.util.ArrayList;

public class ElemArreglo {
       public ClaseNodo InfSimbolo;
       public ArrayList<ClaseNodo> ListaLadoDerecho;
       public ElemArreglo(){
           InfSimbolo = new ClaseNodo();
           ListaLadoDerecho = new ArrayList<ClaseNodo>();
       }
   }
   
