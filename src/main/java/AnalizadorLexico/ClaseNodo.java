/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalizadorLexico;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author ivette
 */
public class ClaseNodo {
  public String Simbolo;
  public boolean Terminal;
  
  public ClaseNodo(){
      Simbolo = "";
      Terminal = false;
  }
  public ClaseNodo(String Simb){
      Simbolo = Simb;
      Terminal = false;
  }
  public ClaseNodo(String Simb, boolean EsTerminal){
      Simbolo = Simb;
      Terminal =  EsTerminal;
  }
   public class ElemArreglo{
       public ClaseNodo InfSimbolo;
       public ArrayList<ClaseNodo> ListaLadoDerecho;
       public ElemArreglo(){
           InfSimbolo = new ClaseNodo();
           ListaLadoDerecho = new ArrayList<ClaseNodo>();
       }
   }
  
  
}

