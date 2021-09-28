
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
    private JButton boton,botonAnalizar,botonGuardar;
    private JComboBox AFNop2, AFNop1;
    private JTextField id,text;
    private static AFN AFNAux;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
    public ventanaUnirAFNLexico(AFN AFN1){
    
        AFNAux = AFN1;
        setLayout(null);
        ConjDeAFNs = AFN1.getListaAFNs();
//Cuadro de texto ----------------------------------------------------------
        JLabel Id;
       
        
         JLabel text = new JLabel("Seleccione los AFN a unir  y asigne token");
        text.setBounds(30,10,300,30); 
        add(text);
      
        //Cuadro de texto para el ID del AFN
        Id=new JLabel("Id Asignado");
        Id.setBounds(30,50,100,20);
//        Id.setFont(new java.awt.Font("arial", 1, 14));
        id=new JTextField();
        id.setBounds(130,50,80,20);
        add(id);
        String var3=id.getText();
        
       
        add(Id);
//Cuadro de texto ----------------------------------------------------------

//Boton seleccionar----------------------------------------------------------
    boton = new JButton(" Seleccionar archivo");
    boton.setBounds(130,80,150,25);
    add(boton);
    boton.addActionListener(this);
//Boton seleccionar----------------------------------------------------------
 JLabel utilizar = new JLabel("AFN a utilizar");
       utilizar.setBounds(30,110,300,30); 
        add(utilizar);
 
        AFNop1 = new JComboBox();
        AFNop1.setBounds(130,115,200,20);
        for(AFN e : ConjDeAFNs){
            AFNop1.addItem("AFN"+String.valueOf(AFN1.getIdAFN(e)));
        } 
        add(text);
        add(AFNop1);
        
        JLabel cadena = new JLabel("Cadena a analizar lexicamente");
        cadena.setBounds(30,150,300,30); 
        add(cadena);
        
//        texto=new JTextField();
        text.setBounds(205,155,300,20);
        add(text);
        
        botonAnalizar = new JButton("Analizar");
        botonAnalizar.setBounds(500,155,100,20);
        add(botonAnalizar);
        botonAnalizar.addActionListener(this);
        
        //Crear Tabla para el token y lexema
        String [] token={"Token","Lexema"};
        String [][] lexema={{"13","tal 3342"}};
                                                       //Añadir nombre del token y lexema
        DefaultTableModel mod= new DefaultTableModel(lexema,token);
        JTable tabla= new JTable(mod);
        JScrollPane scroll= new  JScrollPane(tabla);
        scroll.setBounds(205,200,400,200);
        add(scroll);

        botonGuardar = new JButton("Guardar AFN");
        botonGuardar.setBounds(450,400,150,20);
        add(botonGuardar);
        botonGuardar.addActionListener(this);


    }
    
    public void opciones(AFN AFN1){
       ventanaUnirAFNLexico uno = new ventanaUnirAFNLexico(AFN1);
       uno.setBounds(0,0,700,600);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Converir AFN a AFD");
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
      
    }  
     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaUnirAFNLexico uno = new ventanaUnirAFNLexico(AFN1);
        uno.opciones(AFN1);
         System.out.println("");
    } 
    

    
}
