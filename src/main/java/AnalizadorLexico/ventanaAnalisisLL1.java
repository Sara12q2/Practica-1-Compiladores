
package AnalizadorLexico;

import AnalizadorLexico.CrearTabla.AnalizadorLL1;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ventanaAnalisisLL1 extends JFrame implements ActionListener{
//    AnalizadorLL1 aux1;
    CrearTabla.AnalizadorLL1 aux1;
    
    private JTextField txtGram = new JTextField();
    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JTextField msn1 = new JTextField();
    private JTextField msn2 = new JTextField();    
    private JComboBox AFDop = new JComboBox();
    
    JTable tabla1=new JTable();
    JTable tabla2=new JTable();
    JTable tabla3=new JTable();
    JTable tabla4=new JTable();
    JTable tabla5=new JTable();
    
    String[] columnNames = {"Token", "Lexema",};
    Object datos[][] = {
        {"", ""},
    };
        //TABLA
    DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
    final JTable table = new JTable(dtm);  
    
    int idAux=0;
    ArrayList<String> guardado = new ArrayList<String>();
    String rutaArchivo=null;
    String nombreArchivo=null;
    int bandera=0;  
    EvaluadorExpr Evaluador;
        
    public ventanaAnalisisLL1(){
        setLayout(null);
        
        //ETIQUETA Titulo Auxiliar----------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("Analisis LL1");
        etiquetaTituloAux.setBounds(40,10,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar--------------------------------------------
        //ETIQUETA Gram---------------------------------------------------
        JLabel Gram = new JLabel("Gramática");
        Gram.setBounds(50,60,300,20);
        Gram.setFont(new java.awt.Font("arial", 1, 14));
        add(Gram);
        //**ETIQUETA Gram-------------------------------------------------
        txtGram.setBounds(170,60, 500, 140);
        add(txtGram);
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(170, 110, 200, 25);
        btn.addActionListener(this);
        add(btn);
        //**FileChooser-------------------------------------------------------- 
        
        //ETIQUETA Cadena a utilizar Sigma-----------------------------------------------------------
        JLabel etiquetaCadenaAnalizar = new JLabel("Sigma");
        etiquetaCadenaAnalizar.setBounds(100,220,300,20);
        etiquetaCadenaAnalizar.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaCadenaAnalizar);
        //**ETIQUETA Cadena a analizar Sigma-----------------------------------------------------------
        cadena.setBounds(330,220, 200, 25);
        add(cadena);


        //Boton Analizar--------------------------------------------------------
        JButton btnCrearTabla = new JButton("Crear Tabla");
        btnCrearTabla.setBounds(50, 290, 100, 25);        
        btnCrearTabla.addActionListener(this);
        add(btnCrearTabla);
        
        JButton btnAsigTokTer = new JButton("Asignar Tokens a terminales");
        btnAsigTokTer.setBounds(250, 290, 200, 30);
        add(btnAsigTokTer);        
        //FileChooser-----------------------------------------------------------
        JButton btnSelecAFDLex = new JButton("Seleccionar AFD Lexico");
        btnSelecAFDLex.setBounds(400, 290, 150, 25);
        btnSelecAFDLex.addActionListener(this);
        add(btnSelecAFDLex);
        //**FileChooser--------------------------------------------------------
        
        JButton btnAnalizSinSig = new JButton("Analizar sintácticamente sigma");
        btnAnalizSinSig.setBounds(450, 340, 200, 25);
        add(btnAnalizSinSig);

        JButton btnProLex = new JButton("Probar léxico");
        btnProLex.setBounds(550, 290, 150, 25);
        btnProLex.addActionListener(this);
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
        scrolldos.setBounds(150,310,100,200);
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
        JScrollPane scrolltres= new  JScrollPane(tabla3);
        scrolltres.setBounds(300,310,100,200);
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
        model3.addColumn("");
//        model2.addColumn("Terminal");        
//        model2.addColumn("Token");
//*Tabla3---------------------------------------------------------------------- 

//TABLA Probar Lexico
        JScrollPane miBarra = new JScrollPane(table);
        this.getContentPane().add(miBarra, null);
        dtm.removeRow(0);
        miBarra.setBounds(320, 350, 400, 200);
        miBarra.setVisible(true);
        table.setVisible(true);
//**TABLA Probar Lexico
  
} 
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("entre botones");
        JFileChooser fileChooser = new JFileChooser("C:\\laragon\\www\\Practica-1-Compiladores");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif"); 
        fileChooser.setFileFilter(imgFilter);
        int id = 0;
        int bandera = 0;
        int idAFN = 0;
        if( e.getActionCommand()=="Seleccionar AFD Lexico" ){
            System.out.println("lexico");
            AFD uno = new AFD();
                bandera=1;
                int result = fileChooser.showOpenDialog(this);
                if (result != JFileChooser.CANCEL_OPTION) {
                    File fileName = fileChooser.getSelectedFile();
                    rutaArchivo = fileName.getAbsolutePath();
                    System.out.println("ruta Archivo"+ rutaArchivo);
                }
                
            try {
                uno.LeerAFDdeArchivo(rutaArchivo, id);
            } catch (IOException ex) {
                System.out.println("ERROR");
                Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            AFDop.addItem(String.valueOf(uno.IdAFD));
        }

        if( e.getActionCommand()=="Probar léxico" ){
            String prueba;
            int token;
            String Lexema;
            String sigma,aux;
            int idAFD=0;
            aux = (String)AFDop.getSelectedItem();
            idAFD = Integer.parseInt(aux);
            idAux = idAFD;
            String[] ValoresRenglon = new String[2];
            AnalizadorLexico L;
            AFD AutFD = new AFD();
            sigma = cadena.getText();
            for(AFD f : AFD.ConjAFDs){
                if(f.IdAFD == idAFD){
                    AutFD = f;
                }
                try {
                    L = new AnalizadorLexico(sigma, AutFD);
                            while(true){
                                token = L.yylex();
                                Lexema = L.Lexema;
                                ValoresRenglon[0] = String.valueOf(token);
                                ValoresRenglon[1] = L.Lexema;
                                guardado.add(String.valueOf(ValoresRenglon[0]));
                                guardado.add(";");
                                guardado.add(String.valueOf(ValoresRenglon[1]));
                                guardado.add(";");
                                Object[] newRow = {ValoresRenglon[0],ValoresRenglon[1]};
//                                dtm.addRow(newRow);
                                L.UndoToken();
                                token = L.yylex();
                                Lexema = L.Lexema;
                                ValoresRenglon[0] = String.valueOf(token);
                                ValoresRenglon[1] = L.Lexema;
                                guardado.add(String.valueOf(ValoresRenglon[0]));
                                guardado.add(";");
                                guardado.add(String.valueOf(ValoresRenglon[1]));
                                guardado.add(";");
                                Object[] newRow2 = {ValoresRenglon[0],ValoresRenglon[1]};
                                System.out.println("RENGLON 0: "+ValoresRenglon[0]);
                                System.out.println("RENGLON 1: "+ValoresRenglon[1]);
                                dtm.addRow(newRow2);
                                if(token == SimbolosEspeciales.FIN)
                                    break;
                            }
                } catch (IOException ex) {
                    Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if( e.getActionCommand()=="Crear Tabla" ){
            String cadena=txtGram.getText();
            String arreglo[]= new String[10];
//            aux1 = AnalizadorLL1(cadena);
            CrearTabla aux2 = new CrearTabla();
            try {
                aux1 = aux2.new AnalizadorLL1(cadena);
                aux1.CrearTablaLL1();
            } catch (IOException ex) {
                Logger.getLogger(ventanaAnalisisLL1.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
            //E->T Ep; Ep->mas T Ep|menos T Ep|epsilon; T->F Tp; Tp->prod F Tp|div F Tp|epsilon; F->parI E parD|num;

            arreglo = aux1.vt2;
            System.out.println("arreglo"+ arreglo.toString());
//            for (String f :  AnalizGram.Vt) {
//
//                        renglones[1] = f;
//                        renglones[2] = "Terminal";
//                        Object[] newRow = {renglones[1], renglones[2]};
//                        dtm.addRow(newRow);
//
//                    }
        }
               
    }
    
    public void opciones(){
       ventanaAnalisisLL1 vEE = new ventanaAnalisisLL1();
       vEE.setBounds(0,0,1000,800);
       vEE.setVisible(true);
       vEE.setLocationRelativeTo(null);
       vEE.setTitle("Analisis LL1");
       vEE.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        ventanaAnalisisLL1 pr = new ventanaAnalisisLL1();
        pr.opciones();
    }
}
