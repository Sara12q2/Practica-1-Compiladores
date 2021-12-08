
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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ventanaEvaluadorExpr extends JFrame implements ActionListener{
    
    private JTextField txt = new JTextField();
    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JTextField msn1 = new JTextField();
    private JTextField msn2 = new JTextField();
    private JComboBox AFDop = new JComboBox();
    int idAux=0;
    ArrayList<String> guardado = new ArrayList<String>();
    String rutaArchivo=null;
    int bandera=0;  
    EvaluadorExpr Evaluador;
        
    public ventanaEvaluadorExpr(){
        setLayout(null);
        
        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("Analizador Léxico");
        etiquetaTituloAux.setBounds(60,30,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar--------------------------------------------
        //ETIQUETA IDAsignado---------------------------------------------------
        JLabel etiquetaIdAsignado = new JLabel("ID del AFD");
        etiquetaIdAsignado.setBounds(70,70,300,20);
        etiquetaIdAsignado.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaIdAsignado);
        //**ETIQUETA IDAsignado-------------------------------------------------
        txt.setBounds(170,70, 100, 25);
        add(txt);
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(170, 110, 200, 25);
        btn.addActionListener(this);
        add(btn);
        //**FileChooser-------------------------------------------------------- 
        
        //ETIQUETA Cadena a utilizar--------------------------------------------
        JLabel expresion = new JLabel("Expresión a evaluar");
        expresion.setBounds(100,180,300,20);
        expresion.setFont(new java.awt.Font("arial", 1, 14));
        add(expresion);
        //JTextField Cadena a analizar
        cadena.setBounds(250,180, 200, 25);
        add(cadena);
        
        //JTextField Cadena a analizar
        msn1.setBounds(250,210, 200, 25);
        msn1.setEditable(false);
        add(msn1);
        //JTextField Cadena a analizar
        msn2.setBounds(250,240, 200, 25);
        msn2.setEditable(false);
        add(msn2);

        //Boton Analizar--------------------------------------------------------
        JButton btnAnalizar = new JButton("Evaluar");
        btnAnalizar.setBounds(350, 290, 100, 25);
        btnAnalizar.addActionListener(this);
        add(btnAnalizar);
        //**Boton Analizar--------------------------------------------------------
    } 
    
    
    public void opciones(){
       ventanaEvaluadorExpr uno = new ventanaEvaluadorExpr();
       uno.setBounds(0,0,600,400);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Evaluador Expresiones");
       uno.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\ivett\\Desktop\\Septimo semestre\\Compiladores\\Pruebas AFD");
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
        if( e.getActionCommand()=="Crear AFN"){
            cadenaConvertir = cadena.getText();
            idAFN = Integer.parseInt(idObtenido.getText());
            System.out.println("IdAFN: "+idAFN);
            System.out.println("Expresion: "+cadenaConvertir);
            try {
                ER_AFN conversion = new ER_AFN(cadenaConvertir,uno);
                conversion.IniConversion();
                resultado = conversion.result;
                JOptionPane.showMessageDialog(null, "AFN Creado");
            } catch (IOException ex) {
                Logger.getLogger(ventanaER_AFN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
    }   
    public static void main(String[] args) {
        ventanaEvaluadorExpr pr = new ventanaEvaluadorExpr();
        pr.opciones();
    }
}
