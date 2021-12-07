package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ventanaFirst extends JFrame implements ActionListener{
    private JTextField txt = new JTextField();
    private JTextField txt2 = new JTextField();
    private JTextField txt3 = new JTextField();
    private JTextField txt4 = new JTextField();
    private JTextField txt5 = new JTextField();
    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JComboBox AFDop = new JComboBox();
            int idAux=0;
            ArrayList<String> guardado = new ArrayList<String>();
            String rutaArchivo=null;
            int bandera=0;  
            
    String[] columnNames = {"Simbolo", "Terminal",};
        Object datos[][] = {
            {"", ""},
        };
         DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        final JTable table = new JTable(dtm);
        
     String[] columnFollow = {"Simbolo"};
        Object datosFollow[][] = {
            {"", ""},
        };
         DefaultTableModel foll = new DefaultTableModel(datosFollow, columnFollow);
        final JTable tableFollow = new JTable(foll);
        
        
     String[] CalculacolumnFollow = {"Simbolo"};
        Object CalculadatosFollow[][] = {
            {"", ""},
        };
         DefaultTableModel Calculafoll = new DefaultTableModel(CalculadatosFollow, CalculacolumnFollow);
        final JTable CalculatableFollow = new JTable(Calculafoll);

        
    public ventanaFirst(){
        setLayout(null);
        
        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("First & Follow");
        etiquetaTituloAux.setBounds(20,30,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 11));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        //ETIQUETA IDAsignado-----------------------------------------------------------
        JLabel etiquetaIdAsignado = new JLabel("ID del AFD");
        etiquetaIdAsignado.setBounds(40,60,100,15);
        etiquetaIdAsignado.setFont(new java.awt.Font("arial", 1, 11));
        add(etiquetaIdAsignado);
        //**ETIQUETA IDAsignado-----------------------------------------------------------
        txt.setBounds(100,60, 100, 25);
        add(txt);
//        txt2.setBounds(300,30, 250, 25);
//        add(txt2);
//        
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(20, 100, 180, 25);
        btn.setFont(new java.awt.Font("arial",1,10));
        btn.addActionListener(this);
        add(btn);
        
        JLabel etiquetaCadena = new JLabel("Cadena a analizar sintacticamente");
        etiquetaCadena.setBounds(20,100,300,100);
        etiquetaCadena.setFont(new java.awt.Font("arial", 1, 11));
        add(etiquetaCadena);
        txt3.setBounds(210,140, 280, 25);
        add(txt3);
        
        JButton btn2 = new JButton("Analizar");
        btn2.setBounds(280, 170, 80, 25);
        btn2.setFont(new java.awt.Font("arial",1,10));
        btn2.addActionListener(this);
        add(btn2);
        
        
        
         
        //TABLA
        JScrollPane miBarra = new JScrollPane(table);
        this.getContentPane().add(miBarra, null);
        dtm.removeRow(0);
        miBarra.setBounds(20, 220, 200, 200);
        miBarra.setVisible(true);
        table.setVisible(true);
        
        JButton first = new JButton("First");
        first.setBounds(330, 210, 60, 25);
        first.setFont(new java.awt.Font("arial",1,10));
        first.addActionListener(this);
        add(first);
        
        txt4.setBounds(230,210, 100, 26);
        add(txt4);
        
        JButton follow = new JButton("Follow");
        follow.setBounds(660, 210, 70, 25);
        follow.setFont(new java.awt.Font("arial",1,10));
        follow.addActionListener(this);
        add(follow);
        
        txt5.setBounds(560,210, 100, 26);
        add(txt5);
        
        
        String[] columnNames2 = {"Simbolo"};
        Object datos2[][] = {
            {"", ""},
        };
         DefaultTableModel dtm2 = new DefaultTableModel(datos2, columnNames2);
        final JTable table2 = new JTable(dtm2);
        
         
        //TABLA
        JScrollPane miBarra2 = new JScrollPane(table2);
        this.getContentPane().add(miBarra2, null);
        dtm2.removeRow(0);
        miBarra2.setBounds(230, 240, 160, 200);
        miBarra2.setVisible(true);
        table2.setVisible(true);
        
        JScrollPane BarraFollow= new JScrollPane(tableFollow);
        this.getContentPane().add(BarraFollow, null);
        foll.removeRow(0);
        BarraFollow.setBounds(400, 220, 150, 200);
        BarraFollow.setVisible(true);
        tableFollow.setVisible(true);
        
         JScrollPane CalculaBarraFollow= new JScrollPane(CalculatableFollow);
        this.getContentPane().add(CalculaBarraFollow, null);
        Calculafoll.removeRow(0);
        CalculaBarraFollow.setBounds(560, 240, 170, 200);
        CalculaBarraFollow.setVisible(true);
        CalculatableFollow.setVisible(true);
        
        
        
        
        //**FileChooser-------------------------------------------------------- 
        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
        //ETIQUETA Cadena a utilizar-----------------------------------------------------------
//        JLabel expresion = new JLabel("Expresión regular");
//        expresion.setBounds(100,180,300,20);
//        expresion.setFont(new java.awt.Font("arial", 1, 14));
//        add(expresion);
//        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
//        cadena.setBounds(250,180, 200, 25);
//        add(cadena);
//        //ETIQUETA ID AFN-----------------------------------------------------------
//        JLabel idAFN = new JLabel("ID AFN");
//        idAFN.setBounds(100,220,300,20);
//        idAFN.setFont(new java.awt.Font("arial", 1, 14));
//        add(idAFN);
//        //**ETIQUETA ID AFN-----------------------------------------------------------
//        idObtenido.setBounds(250,220, 200, 25);
//        add(idObtenido);
//        //Boton Analizar-----------------------------------------------------------
//        JButton btnAnalizar = new JButton("Crear AFN");
//        btnAnalizar.setBounds(350, 255, 100, 25);
//        btnAnalizar.addActionListener(this);
//        add(btnAnalizar);
        //**Boton Analizar--------------------------------------------------------
    } 
    
    
    public void opciones(){
       ventanaFirst uno = new ventanaFirst();
       uno.setBounds(0,0,770,490);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("First and Follow");
       uno.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\LENOVO\\OneDrive\\Documentos\\MATERIAS\\Compiladores");
//        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif"); 
        fileChooser.setFileFilter(imgFilter);
        int idAFD = 0;
        int idAFN = 0;
        AFD uno = new AFD();
        AFN resultado = new AFN();
        String cadenaConvertir = new String();
        if( e.getActionCommand()=="Seleccionar Archivo del AFD" ){

                int result = fileChooser.showOpenDialog(this);
                if (result != JFileChooser.CANCEL_OPTION) {
                    File fileName = fileChooser.getSelectedFile();
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txt.setText("...");
                } else {
                    rutaArchivo = fileName.getAbsolutePath();
                    System.out.println("RUTA OBTENIDA: "+rutaArchivo);
                }
                }
                
                idAFD = Integer.parseInt(txt.getText());
                System.out.println("IdAFD: "+idAFD);
            try {
                uno.LeerAFDdeArchivo(rutaArchivo, idAFD);
            } catch (IOException ex) {
                System.out.println("ERROR");
                Logger.getLogger(ventanaER_AFN.class.getName()).log(Level.SEVERE, null, ex);
            }            
            AFDop.addItem(String.valueOf(uno.IdAFD));
        }
        
        if( e.getActionCommand()=="Analizar" ){
            //txt3= fiel que contiene el texto 
           String cadena;
           String[] renglones = new String[2];
           cadena=txt3.getText();
           System.out.println("Cadena a analizar: "+cadena);
           String partes[]=cadena.split(";");
           String div[]={};
            System.out.println("PARTES: "+ Arrays.asList(partes));
            
            for (int i = 0; i < partes.length; i++) {
                System.out.println("PARTE 1: "+partes[i]);
                div=partes[i].split("->");
                System.out.println("division: "+Arrays.asList(div));
                 System.out.println("DIVIISON: "+div[0]);
                
                 
                 
//                renglones[0]=div[0];
//                renglones[1]="No terminal";
                
                renglones[0]=div[1];
                renglones[1]="Terminal";
                
                 Object[] newRow = {renglones[0],renglones[1]};
                                dtm.addRow(newRow);
            }
            
            
//            if(cadena.contains("Ep")){
//                renglones[0]="Ep";
//                renglones[1]="No terminal";
//                
//                Object[] newRow = {renglones[0],renglones[1]};
//                                dtm.addRow(newRow);
//            
//            }
        }
        
        
    }   
    public static void main(String[] args) {
        ventanaFirst uno = new ventanaFirst();
        uno.opciones();
    }
}
