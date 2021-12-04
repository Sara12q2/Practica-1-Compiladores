package AnalizadorLexico;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class ventanaConvertirAFNaAFD extends JFrame implements ActionListener {

    private JButton boton;
    private JComboBox AFNop2, AFNop1;
    private JTextField infe, supe, id;
    private static AFN AFNAux;
    private static AFD afd;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    private HashSet<String> alfabeto = new HashSet<String>();

    File archivo;
    FileReader fr;
    BufferedReader bw;
//    String[] token = {"xxxxxxxx", "aaaaaaaaa"};   //EEjemplos--Modificarrr
    String[] token={"NULL", "SOH","STX","ETX","EOT","ENQ","ACK","BEL","BS","HT","LF","VT","FF","CR","SO","SI","DLE","DC1","DC2","DC3","DC4","NAK","SYN","ETB","CAN","EM","SUB","ESC","FS","GS","RS","US",
        "espacio","!", " '' ", "#", "$", "%", "&", "'","(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?",
        "@","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","[","\'","]","^","_","`","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
        "p","q","r","s","t","u","v","w","x","y","z","{.","|","}","~","DEL","Ç","ü","é","â","ä","à","å","ç","ê","ë","è","ï","î","ì","Ä","Å","É","æ","Æ","ô","ö","ò","û","ù","ÿ","Ö","Ü","ø","£","Ø","×","ƒ","á","í",
        "ó","ú","ñ","Ñ","ª","º","¿","®","¬","½","¼","¡","«","»","░","▒","▓","│","┤","Á","Â","À","©","╣","║","╗","╝","¢","¥","┐","└","┴","┬","├","─","┼","ã","Ã","╚","╔","╩","╦","╠","═","╬","¤","ð","Ð","Ê","Ë","È","ı","Í","Î","Ï","┘","┌","█","▄","¦","Ì","▀","Ó","ß","Ô","Ò","õ","Õ","µ","þ",
            "Þ","Ú","Û","Ù","ý","Ý","¯","´","≡","±","‗","¾","¶","§","÷","¸","°","¨","·","¹","³","²","■","nbsp","TOKEN"

    };;
    String[][] lexema = {{"", ""}};
    //Añadir nombre del token y lexema
    DefaultTableModel mod = new DefaultTableModel(lexema, token);
    JTable tabla = new JTable(mod);

    public ventanaConvertirAFNaAFD(AFN AFN1) {
        AFNAux = AFN1;
        setLayout(null);
        ConjDeAFNs = AFN1.getListaAFNs();
//Cuadro de texto & Combox----------------------------------------------------------
        JLabel Id, afd;

        JLabel etiquetaConvertir = new JLabel("AFN a convertir a AFD");
        etiquetaConvertir.setBounds(30, 25, 300, 30);
//        etiquetaConvertir.setFont(new java.awt.Font("arial", 1, 14));
        //Menu desplegable de los AFN's
        AFNop1 = new JComboBox();
        AFNop1.setBounds(230, 29, 200, 20);
        for (AFN e : AFN.ConjDeAFNs) {
            AFNop1.addItem("AFN " + String.valueOf(e.IdAFN));
        }
        add(etiquetaConvertir);
        add(AFNop1);

        //Cuadro de texto para el ID del AFD
        Id = new JLabel("Id AFD");
        Id.setBounds(30, 70, 80, 20);
//        Id.setFont(new java.awt.Font("arial", 1, 14));
        id = new JTextField();
        id.setBounds(230, 70, 80, 20);
        add(id);
        String var3 = id.getText();

        add(Id);
//Cuadro de texto & Combox----------------------------------------------------------
        //Crear Tabla para el token y lexema
//        String [] token={"",""};   //EEjemplos--Modificarrr
//        String [][] lexema={{"",""}};
//                                                       //Añadir nombre del token y lexema
//        DefaultTableModel mod= new DefaultTableModel(lexema,token);
//        JTable tabla= new JTable(mod);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 180, 400, 200);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.doLayout();
        add(scroll);
        
//         JScrollPane miBarra = new JScrollPane(tabla);
//        this.getContentPane().add(scroll, null);
//        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
////        dtm.removeRow(0);
//        miBarra.setBounds(30, 180, 400, 200);
//        miBarra.setVisible(true);
//        tabla.setVisible(true);
//Boton Convertir y guardar----------------------------------------------------------
        boton = new JButton(" Convertir y guardar");
        boton.setBounds(230, 100, 150, 25);
        add(boton);
        boton.addActionListener(this);

//Boton Convertir y guardar----------------------------------------------------------
////FORMAR TABLA------------------------------------------------------------------------
        afd = new JLabel("AFD");
        afd.setBounds(30, 150, 80, 20);
        add(afd);

//*FORMAR TABLA-----------------------------------------------------------------------
    }

    public void opciones(AFN AFN1) {
        ventanaConvertirAFNaAFD uno = new ventanaConvertirAFNaAFD(AFN1);
        uno.setBounds(0, 0, 500, 500);
        uno.setVisible(true);
        uno.setLocationRelativeTo(null);
        uno.setTitle("Converir AFN a AFD");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == boton) {
            int ide, id1;
            AFN AFNconvert;
            AFNconvert = new AFN();
            AFD AFDconv;
            AFD AFNp;
            AFNp = new AFD();
            String cad1 = (String) AFNop1.getSelectedItem();
            cad1 = cad1.replace("AFN ", "");
            System.out.println("Cad:" + cad1);
            id1 = Integer.parseInt(cad1);
//            //tomando el id del select item
            System.out.println("IDE select item: " + id1);
            //Añadiendo un id para el AFD
            String nId = (String) id.getText();
            ide = Integer.parseInt(nId);
//            
            AFNp.setIdAFD(ide);
            System.out.println("IDE nuevo AFD: " + ide);

            for (AFN a : AFN.ConjDeAFNs) {
                if (AFNAux.getIdAFN(a) == id1) {
                    AFNconvert = a;
                }

            }
//               alfabeto= AFNconvert.Alfabeto;

            AFDconv = AFNconvert.ConvAFNaAFD();
            AFD.ConjAFDs.add(AFDconv);
            try {
                AFDconv.GuardarAFDenArchivo("AFD.txt");

//                AFNp.setIdAFD(ide); //Agrega el id al AFD nuevo
//            try {                
//                AFDconv.GuardarAFDenArchivo("AFD.txt");
//            } catch (IOException ex) {
//                Logger.getLogger(ventanaConvertirAFNaAFD.class.getName()).log(Level.SEVERE, null, ex);
//            }
//                AFD.ConjAFDs.add(AFDconv);
                
                archivo = new File("AFD.txt");
                fr = new FileReader(archivo);
                bw = new BufferedReader(fr);

                tabla.getModel();
                Object[] linea = bw.lines().toArray();
                for (int i = 0; i < linea.length; i++) {
                    String[] fila = linea[i].toString().split(";");
                    mod.addRow(fila);
//                    
//                    System.out.println("gila: "+fila);
//                    bw.close();
//                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ventanaConvertirAFNaAFD.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void main(String[] args) {
        AFN AFN1 = new AFN();
        ventanaConvertirAFNaAFD uno = new ventanaConvertirAFNaAFD(AFN1);
        uno.opciones(AFN1);
        System.out.println("");
    }
}
