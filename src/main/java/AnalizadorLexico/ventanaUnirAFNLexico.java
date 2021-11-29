
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
    //private static AFN AFNAux2;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<AFN> ConjDeAFNEs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
    private AFN AFNAux = new AFN();
    private AFN AFNObt = new AFN();    
    private AFN AFNAux2 = new AFN();
    private AFN AFNdef = new AFN();
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
    public AFN devolverAFN(int i){
        for(AFN id : AFN.ConjDeAFNs){
            if(AFNAux2.getIdAFN(id)==i){
                //metodo que busque el automata
                    //AFNAux = Obtener automata(tabla.getValueAt(i, 0).toString);
                AFNObt = id;    
            }
        }        
        return AFNObt;
    }; 
    
@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==botonUnir){            
            int id1,id2,idn,token,i;            
            int cantFilas = tabla.getRowCount();
            int conta=0;
            AFN AFNaunir,AFNUnidoL;
            AFNaunir=new AFN();
            AFNUnidoL = new AFN();
            String SAFN = "";
            String Scheckbox = "";
            String Stoken = "";
            
            System.out.println("cantFilas "+cantFilas);
            for(i = 0; i < cantFilas; i++){
                System.out.println("i: "+i);
                 if(tabla.getValueAt(i, 1).equals(true)) 
                 {  //colum 0= AFN column 1 =checkbox  column 2 = token
                    SAFN = tabla.getValueAt(i, 0).toString();    //fila seleccionada, y valor guardado en la variable "valor"
                    Scheckbox = tabla.getValueAt(i, 1).toString();
                    Stoken = tabla.getValueAt(i, 2).toString();
                    //System.out.println("AFN"+SAFN+" checkbox"+Scheckbox+" token"+Stoken);
                    id1 = Integer.parseInt(SAFN);
                    token = Integer.parseInt(Stoken);
                    
                    for(AFN id : AFN.ConjDeAFNs){
                        if(AFNAux.getIdAFN(id)==id1){
                            AFNObt = id;    
                        }
                    }   
//                        AFNAux2 = devolverAFN(id1);

                        //AFNAux.UnionEspecialAFNs(AFNAux2, token);
                        AFNUnidoL.UnionEspecialAFNs(AFNObt, token);
                        
                        //verificar si si lo obtiene AFNAux2
//                        for (AFN o: AFN.ConjDeAFNs) {
//                            System.out.println("UL Conj: "+o.IdAFN);
//                       }
//                        System.out.println("");
                        
//                    AFN.ConjDeAFNs.add(AFNAux);    
//                    AFN.ConjDeAFNs.remove(AFNAux2);

                    //AFN.ConjDeAFNs.add(AFNUnidoL);    
                    AFN.ConjDeAFNs.remove(AFNObt);
                 }
            }            
            //*AFNp.IdAFN = getValue
            String nId =  (String)tfid.getText();
            idn = Integer.parseInt(nId);
            System.out.println("IDEn: "+ idn);
            
//            AFNAux.setIdAFN(idn);
//            AFN.ConjDeAFNs.add(AFNAux);          
                        
            AFNUnidoL.setIdAFN(idn);
            AFN.ConjDeAFNs.add(AFNUnidoL);
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
                     
//////            AFNunido = AFNp.UnirAFN(AFNs);
////            AFN obj = new AFN();
////            obj.UnionEspecialAFNs(e, id2);
////            
////            AFN.ConjDeAFNs.add(AFNunido);
////            AFN.ConjDeAFNs.remove(AFNs);

     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaUnirAFNLexico uno = new ventanaUnirAFNLexico(AFN1);
        uno.opciones(AFN1);
         System.out.println("vUAFNl");
    }     

}
