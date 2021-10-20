
package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.*;

public class ventanaUnirAFNLexico  extends JFrame implements ActionListener{
    private JButton botonUnir;
    private JComboBox AFNop2, AFNop1;
    private JTextField tfid,tftext;
    private static AFN AFNAux2;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<AFN> ConjDeAFNEs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
    private AFN AFNAux = new AFN();
    
    //Aqui se guardan los id obtenidos del checkbox
    private int []IdObtenido;
    //Aqui se guardan los tokens
    private int []Token;
    JTable tabla=new JTable();
    
    public ventanaUnirAFNLexico(AFN AFN1){
    
        AFNAux = AFN1;
        //AFNAux2 = AFN2;
        setLayout(null);
        ConjDeAFNs = AFNAux.getListaAFNs();
//Cuadro de texto ----------------------------------------------------------
        JLabel text = new JLabel("Seleccione los AFN a unir  y asigne los tokens");
        text.setBounds(30,10,300,30); 
        add(text);
//*Cuadro de texto ----------------------------------------------------------

//Boton seleccionar----------------------------------------------------------     
        AFNop1 = new JComboBox();
        AFNop1.setBounds(130,420,200,20);
        for(AFN e : ConjDeAFNs){
            AFNop1.addItem("AFN"+String.valueOf(AFN1.getIdAFN(e)));
        }
        add(AFNop1);
//Tabla----------------------------------------------------------------------
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll= new  JScrollPane(tabla);
        scroll.setBounds(30,80,400,200);
        add(scroll);
//        //jScrollPane.setViewportView(tabla);
        DefaultTableModel model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column)
                {
                    case 0:
                        return Integer.class;
                    case 1:
                        return Boolean.class;
                    case 2: 
                        return Integer.class; 
                    default:
                        return String.class;
                }
            }
            boolean[] canEdit = new boolean[]{
                    false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(model);
//Table columns
        model.addColumn("AFN´s");
        model.addColumn("Seleccionar AFN");
        model.addColumn("Token");

//Table content
          for(AFN id : ConjDeAFNs){
              Object[] newRow = {String.valueOf(AFN1.getIdAFN(id)), Boolean.FALSE, new Integer(0) };
              model.addRow(newRow);
          }
   
//*Etiqueta para el ID del AFN a generar
        JLabel Id;
        Id=new JLabel("Id del AFN resultante");
        Id.setBounds(30,300,150,20);
        add(Id);

//*Cuadro de texto para el ID del AFN a generar
        tfid=new JTextField();
        tfid.setBounds(150,300,80,20);
        add(tfid);
        String var3=tfid.getText();
        
//*Boton unir
        botonUnir = new JButton("Unir AFN");
        botonUnir.setBounds(400,300,150,20);
        add(botonUnir);
        botonUnir.addActionListener(this);    
    }  
    
    public void opciones(AFN AFN1){
       ventanaUnirAFNLexico uno = new ventanaUnirAFNLexico(AFN1);
       uno.setBounds(0,0,600,400);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Unir AFNs para Léxico");
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botonUnir){            
            int id1,id2,idn;
            int conta=0;
            AFN AFNp,AFNunido;
            AFNp=new AFN();
            Estado NuevoIni = new Estado();
            AFNp.EdoIni = NuevoIni;
            AFN AFNs = null;
            String valor = "";
            String valor1 = "";
            String valor2 = "";
            int cantFilas = tabla.getRowCount();
            System.out.println("cantFilas "+cantFilas);
            int i;
            for(i = 0; i < cantFilas; i++){
                 if(tabla.getValueAt(i, 1).equals(true)) 
                 {
                    //colum 0= AFN column 1 =checkbox  column 2 = token
                    valor = tabla.getValueAt(i, 0).toString();    //fila seleccionada, y valor guardado en la variable "valor"
                    valor1 = tabla.getValueAt(i, 1).toString();
                    valor2 = tabla.getValueAt(i, 2).toString();
////                        //metodo que busque el automata
////                        //AFNAux = Obtener automata(tabla.getValueAt(i, 0).toString);
////                        AFNAux.EdosAcept.token = tabla.getValueAt(i, 2).toString.parseInt();//Hacerlo con un foreach
////                        AFNAux.EdosAcept.Token1 = tabla.getValueAt(i,2).toString();
////
////                        for(AFNAux id : ConjDeAFNs){
////                            
////                        }
////                        NuevoIni.Trans1.add(new Transicion(Epsilon,AFNAux.EdoIni));
////                        //AFNp.Estados.addAll(AFNAux);
////                        AFNp.EdosAcept.addAll(AFNAux.EdosAcept);
////                        AFNp.Alfabeto.addAll(AFNAux.Alfabeto);
                            //AFNAux = null;
                       System.out.println("AFN"+valor+" checkbox"+valor1+" token"+valor2);
                 }
            }
            
            //AFNp.IdAFN = getValue
            String nId =  (String)tfid.getText();
            idn = Integer.parseInt(nId);
            System.out.println("IDEn: "+ idn);
            AFNp.setIdAFN(idn);
            JOptionPane.showMessageDialog(null, "Operación realizada con exito");
        }
    }
            
//            for(int i=0; i<IdObtenido.length; i++){
//                for(AFN id : ConjDeAFNs){
//                    if(id.IdAFN == IdObtenido[i] && conta==0){
//                        AFNp = id;
//                        conta++;
//                    }else{
//                        if(id.IdAFN == IdObtenido[i]){
//                            AFNs = id;
//                            conta++;
//                        }
//                    }
//                    if(conta==2){
//                        AFNp.UnionEspecialAFNs(AFNs, i);
//                    }
//                }
//            }
            
            
            
//            String cad1 = (String)AFN11.getSelectedItem();
//            String cad2 = (String)AFN2.getSelectedItem();
//            cad1 = cad1.replace("AFN","");
//            cad2 = cad2.replace("AFN","");
//            id1 = Integer.parseInt(cad1);
//            id2 = Integer.parseInt(cad2);
//            System.out.println("Id 1: "+id1);
//            System.out.println("Id 2: "+id2);
////            for(AFN a : AFN.ConjDeAFNs){
////                if(AFNAux.getIdAFN(a)==id1){
////                    AFNp = a;
////                }
////                if(AFNAux.getIdAFN(a)==id2){
////                    AFNs = a;
////                }
////            }
////            
//////            AFNunido = AFNp.UnirAFN(AFNs);
////            AFN obj = new AFN();
////            obj.UnionEspecialAFNs(e, id2);
////            
////            AFN.ConjDeAFNs.add(AFNunido);
////            AFN.ConjDeAFNs.remove(AFNs);
////            
////           


     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaUnirAFNLexico uno = new ventanaUnirAFNLexico(AFN1);
        uno.opciones(AFN1);
         System.out.println("vUAFNl");
    }     

}
