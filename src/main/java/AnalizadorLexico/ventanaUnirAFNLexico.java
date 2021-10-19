
package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ventanaUnirAFNLexico  extends JFrame implements ActionListener{
    private JButton botonUnir;
    private JComboBox AFNop2, AFNop1;
    private JTextField id,text;
    private static AFN AFNAux2;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    private AFN AFNAux = new AFN();
    
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

//Titulos de la tabla
//        String [] titulos={"AFN´s","Seleccionar AFN","Token"};
//        //String [][] lexema={{"13","tal 3342"}};
//        DefaultTableModel mod= new DefaultTableModel(null,titulos);
//        JTable tabla= new JTable(mod);
//        JScrollPane scroll= new  JScrollPane(tabla);
//        
        JTable tabla=new JTable();
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll= new  JScrollPane(tabla);
        scroll.setBounds(30,80,400,200);
        add(scroll);
//        //jScrollPane.setViewportView(tabla);
        DefaultTableModel model = new DefaultTableModel(){};
        tabla.setModel(model);
////        for(int i=0; i <= 255 ;i++){
////            model.addColumn((char)i);
////        }
        model.addColumn("AFN´s");
        model.addColumn("Seleccionar AFN");
        model.addColumn("Token");
        int i=0;
          for(AFN id : ConjDeAFNs){
          //for(int i=0; i < ConjDeAFNs.size() ;i++){
              model.addRow(new Object[0]);
              
              int k=0;
              model.setValueAt("AFN"+String.valueOf(AFN1.getIdAFN(id)), i,k);
              i++;
          }
          
//*Etiqueta para el ID del AFN a generar
        JLabel Id;
        Id=new JLabel("Id del AFN resultante");
        Id.setBounds(30,300,150,20);
        add(Id);
//        Id.setFont(new java.awt.Font("arial", 1, 14));

//*Cuadro de texto para el ID del AFN a generar
        id=new JTextField();
        id.setBounds(150,300,80,20);
        add(id);
        String var3=id.getText();
        
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
      
    }  
     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaUnirAFNLexico uno = new ventanaUnirAFNLexico(AFN1);
        uno.opciones(AFN1);
         System.out.println("UAPL");
    } 
    
}
