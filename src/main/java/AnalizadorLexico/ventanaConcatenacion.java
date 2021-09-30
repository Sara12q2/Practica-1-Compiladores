package AnalizadorLexico;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Image;

public class ventanaConcatenacion extends JFrame implements ActionListener{
    private JButton boton;
    private JComboBox AFNop2, AFNop1;
    private static AFN AFNAux;
   
    public ventanaConcatenacion(AFN AFN1){
        AFNAux = AFN1;
        AFN1=new AFN();
        setLayout(null);
        
//ETIQUETA CONCATENAR-----------------------------------------------------------
        JLabel etiquetaConcatenar = new JLabel("Concatenar");
        etiquetaConcatenar.setBounds(30,25,100,30);
        etiquetaConcatenar.setFont(new java.awt.Font("arial", 1, 14));
        //MENU DESPLEGABLE
        AFNop1 = new JComboBox();
        AFNop1.setBounds(150,29,200,20);
        
         for(AFN e : AFN.ConjDeAFNs){
            
             System.out.println(e.IdAFN);
        } 
        
        
        for(AFN e : AFN.ConjDeAFNs){
            
            AFNop1.addItem("AFN "+String.valueOf(e.IdAFN));
        } 
        add(etiquetaConcatenar);
        add(AFNop1);
//*ETIQUETA CONCATENAR----------------------------------------------------------


//ETIQUETA CON------------------------------------------------------------------
        JLabel etiquetaCon = new JLabel("con");
        etiquetaCon.setBounds(380,25,100,30);
        etiquetaCon.setFont(new java.awt.Font("arial", 1, 14));
        //MENU DESPLEGABLE
        AFNop2 = new JComboBox();
        AFNop2.setBounds(440,29,200,20);
        for(AFN e : AFN.ConjDeAFNs){
            AFNop2.addItem("AFN "+String.valueOf(e.IdAFN));
        }
        add(etiquetaCon);
        add(AFNop2);
//ETIQUETA CON------------------------------------------------------------------

//IMAGEN------------------------------------------------------------------------
        JLabel etiquetaImagen = new JLabel();
        ImageIcon imgThisImg = new ImageIcon("Images/concatenar.png");
        etiquetaImagen.setIcon(imgThisImg);
        
        etiquetaImagen.setBounds(80,70,600,120);
        add(etiquetaImagen);
//*IMAGEN-----------------------------------------------------------------------

//BOTON-------------------------------------------------------------------------
    boton = new JButton("Concatenar AFN's");
    boton.setBounds(498,220,140,20);
    add(boton);
    boton.addActionListener(this);
//*BOTON------------------------------------------------------------------------
    }
    
    public void opciones(AFN AFN1){
       ventanaConcatenacion uno = new ventanaConcatenacion(AFN1);
       uno.setBounds(0,0,700,300);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Concatenación");
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==boton){
            int id1,id2;
            AFN AFNp = null,AFNs = null, AFNconcatenado = null;
            JOptionPane.showMessageDialog(null, "Operación realizada con exito");
            String cad1 = (String)AFNop1.getSelectedItem();
            String cad2 = (String)AFNop2.getSelectedItem();
            
            cad1 = cad1.replace("AFN","");
            cad2 = cad2.replace("AFN","");
                id1 = Integer.parseInt(cad1);
            id2 = Integer.parseInt(cad2);
            for(AFN a : AFN.ConjDeAFNs){
                if(AFNAux.getIdAFN(a)==id1){
                    AFNp = a;
                }
                if(AFNAux.getIdAFN(a)==id2){
                    AFNs = a;
                }
            }
            AFNconcatenado = AFNp.ConcAFN(AFNs);
            AFNAux.agregarAFNaLista(AFNconcatenado);
        }
    }   
}























