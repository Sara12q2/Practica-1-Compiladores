
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

public class ventanaDescRecGramGram extends JFrame{
    private JTextField txt = new JTextField();
    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JTextField msn1 = new JTextField();
    private JTextField msn2 = new JTextField();
    private JComboBox AFDop = new JComboBox();
    int idAux=0;
    ArrayList<String> guardado = new ArrayList<String>();
    String rutaArchivo=null;
    String nombreArchivo=null;
    int bandera=0;  
    EvaluadorExpr Evaluador;
        
    public ventanaDescRecGramGram(){
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
        //btn.addActionListener(this);
        add(btn);
        //**FileChooser-------------------------------------------------------- 
        
        //ETIQUETA Cadena a utilizar--------------------------------------------
        JLabel expresion = new JLabel("Cadena a analizar sintácticamente");
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
        JButton btnAnalizar = new JButton("Analizar");
        btnAnalizar.setBounds(350, 290, 100, 25);
        add(btnAnalizar);
        //**Boton Analizar--------------------------------------------------------
        
    btn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser("C:\\laragon\\www\\Practica-1-Compiladores");
//        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif"); 
        fileChooser.setFileFilter(imgFilter);
        int idAFD = 0;
         AFD uno = new AFD();
            int result = fileChooser.showOpenDialog(ventanaDescRecGramGram.this);
            if (result != JFileChooser.CANCEL_OPTION) {
                File fileName = fileChooser.getSelectedFile();
                nombreArchivo = fileName.getName();
                System.out.println("nombre archivo: "+nombreArchivo);
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txt.setText("...");
                } else {
                    rutaArchivo = fileName.getAbsolutePath();
                        System.out.println("RUTA OBTENIDA: "+rutaArchivo);
                    try {
                        Evaluador = new EvaluadorExpr(nombreArchivo,-1);//nombre del archivo del AFD
                    } catch (IOException ex) {
                        Logger.getLogger(ventanaEvaluadorExpr.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                
                idAFD = Integer.parseInt(txt.getText());
                System.out.println("IdAFD: "+idAFD);
            try {
                uno.LeerAFDdeArchivo(rutaArchivo, idAFD);
            } catch (IOException ex) {
                System.out.println("ERROR");
                Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            AFDop.addItem(String.valueOf(uno.IdAFD));
        }    
    });   
    
    
    btnAnalizar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String aux;
            aux=cadena.getText();
            System.out.println("cadena a evaluar: "+aux);
            Evaluador.SetExpresion(aux);
            if(Evaluador.IniEval()){
                System.out.println("Expresión sintácticamente correcta."+Evaluador.result);
                String s=Float.toString(Evaluador.result);
                msn1.setText(s);
                msn2.setText(Evaluador.ExprPost);
            } else{
//                boolean rEIE=Evaluador.IniEval();
//                System.out.println("Evaluador.IniEval: " + rEIE);
                System.out.println("Expresión sintácticamente incorrecta.");
                msn1.setText("ERROR");
            }
        }
    });   
} 
    
    
    public void opciones(){
       ventanaDescRecGramGram vEE = new ventanaDescRecGramGram();
       vEE.setBounds(0,0,600,400);
       vEE.setVisible(true);
       vEE.setLocationRelativeTo(null);
       vEE.setTitle("Descenso Recursivo Gramatica de Gramaticas");
       vEE.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaDescRecGramGram pr = new ventanaDescRecGramGram();
        pr.opciones();
    }
}

