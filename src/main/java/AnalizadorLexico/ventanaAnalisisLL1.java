
package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ventanaAnalisisLL1 extends JFrame {
    private JTextField txtGram = new JTextField();
    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JTextField msn1 = new JTextField();
    private JTextField msn2 = new JTextField();
    
    JTable tabla1=new JTable();
    JTable tabla2=new JTable();
    JTable tabla3=new JTable();
    JTable tabla4=new JTable();
    JTable tabla5=new JTable();
    
    int idAux=0;
    ArrayList<String> guardado = new ArrayList<String>();
    String rutaArchivo=null;
    String nombreArchivo=null;
    int bandera=0;  
    EvaluadorExpr Evaluador;
        
    public ventanaAnalisisLL1(){
        setLayout(null);
        
        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("Analisis LL1");
        etiquetaTituloAux.setBounds(60,30,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar--------------------------------------------
        //ETIQUETA Gram---------------------------------------------------
        JLabel Gram = new JLabel("Gramática");
        Gram.setBounds(70,70,300,20);
        Gram.setFont(new java.awt.Font("arial", 1, 14));
        add(Gram);
        //**ETIQUETA Gram-------------------------------------------------
        txtGram.setBounds(170,70, 500, 150);
        add(txtGram);
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(170, 110, 200, 25);
        //btn.addActionListener(this);
        add(btn);
        //**FileChooser-------------------------------------------------------- 
        
        //ETIQUETA Cadena a utilizar--------------------------------------------
//        JLabel expresion = new JLabel("Expresión a evaluar");
//        expresion.setBounds(100,180,300,20);
//        expresion.setFont(new java.awt.Font("arial", 1, 14));
//        add(expresion);
        //JTextField Cadena a analizar
        cadena.setBounds(250,180, 200, 25);
        add(cadena);
        
//        //JTextField Cadena a analizar
//        msn1.setBounds(250,210, 200, 25);
//        msn1.setEditable(false);
//        add(msn1);
//        //JTextField Cadena a analizar
//        msn2.setBounds(250,240, 200, 25);
//        msn2.setEditable(false);
//        add(msn2);

        //Boton Analizar--------------------------------------------------------
        JButton btnCrearTabla = new JButton("Crear Tabla");
        btnCrearTabla.setBounds(50, 290, 100, 25);
        add(btnCrearTabla);
        
        JButton btnAsigTokTer = new JButton("Asignar Tokens a terminales");
        btnAsigTokTer.setBounds(150, 290, 200, 30);
        add(btnAsigTokTer);
        
        JButton btnSelecAFDLex = new JButton("Seleccionar AFD Léxico");
        btnSelecAFDLex.setBounds(250, 290, 150, 25);
        add(btnSelecAFDLex);
        
        JButton btnAnalizSinSig = new JButton("Analizar sintácticamente sigma");
        btnAnalizSinSig.setBounds(250, 340, 200, 25);
        add(btnAnalizSinSig);
        
        JButton btnProLex = new JButton("Probar léxico");
        btnProLex.setBounds(350, 290, 150, 25);
        add(btnProLex); 
        
        
        //Tabla1----------------------------------------------------------------------
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrolluno= new  JScrollPane(tabla1);
        scrolluno.setBounds(30,310,100,200);
        add(scrolluno);
//        //jScrollPane.setViewportView(tabla);
        DefaultTableModel model1 = new DefaultTableModel(){
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
        tabla1.setModel(model1);
//Table columns
        model1.addColumn("AFN´s");
        model1.addColumn("No Terminal");
//*Tabla1-------------------------------

//Tabla2----------------------------------------------------------------------
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrolldos= new  JScrollPane(tabla2);
        scrolldos.setBounds(110,310,100,200);
        add(scrolldos);
//        //jScrollPane.setViewportView(tabla);
        DefaultTableModel model2 = new DefaultTableModel(){
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
        tabla2.setModel(model2);
//Table columns
        model2.addColumn("");
        model2.addColumn("Terminal");        
        model2.addColumn("Token");
//*Tabla2----------------------------------------------------------------------        
        

//Tabla3----------------------------------------------------------------------
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrolltres= new  JScrollPane(tabla2);
        scrolltres.setBounds(80,310,100,200);
        add(scrolltres);
//        //jScrollPane.setViewportView(tabla);
        DefaultTableModel model3 = new DefaultTableModel(){
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
        tabla3.setModel(model3);
////Table columns
        model2.addColumn("");
//        model2.addColumn("Terminal");        
//        model2.addColumn("Token");
//*Tabla3---------------------------------------------------------------------- 

    btn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser("C:\\laragon\\www\\Practica-1-Compiladores");
//        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif"); 
        fileChooser.setFileFilter(imgFilter);
        int idAFD = 0;
         AFD uno = new AFD();
            int result = fileChooser.showOpenDialog(ventanaAnalisisLL1.this);
            if (result != JFileChooser.CANCEL_OPTION) {
                File fileName = fileChooser.getSelectedFile();
                nombreArchivo = fileName.getName();
                System.out.println("nombre archivo: "+nombreArchivo);
                if ((fileName == null) || (fileName.getName().equals(""))) {
                    txtGram.setText("...");
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
                
                idAFD = Integer.parseInt(txtGram.getText());
                System.out.println("IdAFD: "+idAFD);
            try {
                uno.LeerAFDdeArchivo(rutaArchivo, idAFD);
            } catch (IOException ex) {
                System.out.println("ERROR");
                Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            AFDop.addItem(String.valueOf(uno.IdAFD));
        }    
    });   
    
    
    btnCrearTabla.addActionListener(new ActionListener() {
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
       ventanaAnalisisLL1 vEE = new ventanaAnalisisLL1();
       vEE.setBounds(0,0,900,800);
       vEE.setVisible(true);
       vEE.setLocationRelativeTo(null);
       vEE.setTitle("Analisis LL1");
       vEE.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    


    
    public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaAnalisisLL1 pr = new ventanaAnalisisLL1();
        pr.opciones();
    }
}
