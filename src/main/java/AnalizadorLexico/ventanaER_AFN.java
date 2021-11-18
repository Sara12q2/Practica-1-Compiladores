package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ventanaER_AFN extends JFrame implements ActionListener{
    private JTextField txt = new JTextField();
    private JTextField cadena = new JTextField();
    private JComboBox AFDop = new JComboBox();
            int idAux=0;
            ArrayList<String> guardado = new ArrayList<String>();
            String rutaArchivo=null;
            int bandera=0;  

        
    public ventanaER_AFN(){
        setLayout(null);
        
        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("Analizador Léxico");
        etiquetaTituloAux.setBounds(60,30,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        //ETIQUETA IDAsignado-----------------------------------------------------------
        JLabel etiquetaIdAsignado = new JLabel("ID del AFD");
        etiquetaIdAsignado.setBounds(70,70,300,20);
        etiquetaIdAsignado.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaIdAsignado);
        //**ETIQUETA IDAsignado-----------------------------------------------------------
        txt.setBounds(170,70, 100, 25);
        add(txt);
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(170, 110, 200, 25);
        btn.addActionListener(this);
        add(btn);
        //**FileChooser-------------------------------------------------------- 
        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
        //ETIQUETA Cadena a utilizar-----------------------------------------------------------
        JLabel etiquetaCadenaAnalizar = new JLabel("Expresión regular");
        etiquetaCadenaAnalizar.setBounds(100,200,300,20);
        etiquetaCadenaAnalizar.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaCadenaAnalizar);
        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
        cadena.setBounds(250,200, 200, 25);
        add(cadena);
        //Boton Analizar-----------------------------------------------------------
        JButton btnAnalizar = new JButton("Crear AFN");
        btnAnalizar.setBounds(350, 255, 100, 25);
        btnAnalizar.addActionListener(this);
        add(btnAnalizar);
        //**Boton Analizar--------------------------------------------------------
    } 
    
    
    public void opciones(){
       ventanaER_AFN uno = new ventanaER_AFN();
       uno.setBounds(0,0,600,400);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Convertir ER a AFN");
       uno.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\ivett\\Desktop\\Septimo semestre\\Compiladores\\Pruebas AFD");
//        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif"); 
        fileChooser.setFileFilter(imgFilter);
        int id = 0;
        
        if( e.getActionCommand()=="Seleccionar Archivo" ){
            AFD uno = new AFD();

                int result = fileChooser.showOpenDialog(this);
                if (result != JFileChooser.CANCEL_OPTION) {
                    File fileName = fileChooser.getSelectedFile();
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txt.setText("...");
                } else {
                    rutaArchivo = fileName.getAbsolutePath();
                }
                }
                
                id = Integer.parseInt(txt.getText());
            try {
                uno.LeerAFDdeArchivo(rutaArchivo, id);
            } catch (IOException ex) {
                System.out.println("ERROR");
                Logger.getLogger(ventanaER_AFN.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            AFDop.addItem(String.valueOf(uno.IdAFD));
        }
        if( e.getActionCommand()=="Analizar" ){

        }
        if( e.getActionCommand()=="Guardar AFD" ){

        }
               
    }   
    public static void main(String[] args) {
        ventanaER_AFN uno = new ventanaER_AFN();
        uno.opciones();
        

    }
}
