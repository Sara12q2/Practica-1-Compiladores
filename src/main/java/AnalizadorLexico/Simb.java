/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalizadorLexico;

    public class Simb{
    public String simbolo;
    public boolean esTerminal;
    public Simb(){
     simbolo="";
     esTerminal=false;
    }
    
   
    public Simb(String s, boolean cons){
        simbolo=s;
        esTerminal=cons;
    
    }
   
    

}