
package AnalizadorLexico;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
     
public class ventanaConvertirAFNaAFD extends JFrame implements ActionListener{
    private JButton boton;
    private JComboBox AFNop2, AFNop1;
    private JTextField infe,supe,id;
    private static AFN AFNAux;
    private static AFD AFDAux;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
     
     public ventanaConvertirAFNaAFD(AFN AFN1){
        AFNAux = AFN1;
        setLayout(null);
        ConjDeAFNs = AFN1.getListaAFNs();
//Cuadro de texto & Combox----------------------------------------------------------
        JLabel Id,afd;
        
         JLabel etiquetaConvertir = new JLabel("AFN a convertir a AFD");
        etiquetaConvertir.setBounds(30,25,300,30);
//        etiquetaConvertir.setFont(new java.awt.Font("arial", 1, 14));
        //Menu desplegable de los AFN's
        AFNop1 = new JComboBox();
        AFNop1.setBounds(230,29,200,20);
        for(AFN e : ConjDeAFNs){
            AFNop1.addItem("AFN "+String.valueOf(AFN1.getIdAFN(e)));
        } 
        add(etiquetaConvertir);
        add(AFNop1);
      
      
        //Cuadro de texto para el ID del AFN
        Id=new JLabel("Id AFN");
        Id.setBounds(30,70,80,20);
//        Id.setFont(new java.awt.Font("arial", 1, 14));
        id=new JTextField();
        id.setBounds(230,70,80,20);
        add(id);
        String var3=id.getText();
        
        add(Id);
//Cuadro de texto & Combox----------------------------------------------------------
       //Crear Tabla para el token y lexema
        String [] token={"",""};   //EEjemplos--Modificarrr
        String [][] lexema={{"",""}};
                                                       //Añadir nombre del token y lexema
        DefaultTableModel mod= new DefaultTableModel(lexema,token);
        JTable tabla= new JTable(mod);
        JScrollPane scroll= new  JScrollPane(tabla);
        scroll.setBounds(30,180,400,200);
        add(scroll);
//Boton Convertir y guardar----------------------------------------------------------
    boton = new JButton(" Convertir y guardar");
    boton.setBounds(230,100,150,25);
    add(boton);
    boton.addActionListener(this);

//Boton Convertir y guardar----------------------------------------------------------
      
////FORMAR TABLA------------------------------------------------------------------------
        afd=new JLabel("AFD");
        afd.setBounds(30,150,80,20);
        add(afd);

       

       





//*FORMAR TABLA-----------------------------------------------------------------------


    }
    
    public void opciones(AFN AFN1){
       ventanaConvertirAFNaAFD uno = new ventanaConvertirAFNaAFD(AFN1);
       uno.setBounds(0,0,500,500);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Converir AFN a AFD");
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
        
          //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==boton){
            try {
                int ide,id1;
                AFN AFNconvert = null;
                AFD AFDconv=null;
                String cad1 = (String)AFNop1.getSelectedItem();
                cad1 = cad1.replace("AFN","");
                id1 = Integer.parseInt(cad1);
                String nId=  (String)id.getText();
//            System.out.println(nId);
                for (AFN a : ConjDeAFNs) {
                    if (AFNAux.getIdAFN(a) == id1) {
                            AFNconvert = a;
                    }
                }
                AFDconv = AFNconvert.ConvAFNaAFD();
                AFDconv.GuardarAFDenArchivo("AFD.txt");
                AFD.ConjAFDs.add(AFDconv);
                ide = Integer.parseInt(nId);
                AFDAux.setIdAFD(ide);
            } catch (IOException ex) {
                Logger.getLogger(ventanaConvertirAFNaAFD.class.getName()).log(Level.SEVERE, null, ex);
            }


           
        }
      
    }  
     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaConvertirAFNaAFD uno = new ventanaConvertirAFNaAFD(AFN1);
        uno.opciones(AFN1);
         System.out.println("");
    } 
}











