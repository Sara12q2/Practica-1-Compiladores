package AnalizadorLexico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ventanaProbarAnalizadorLexico extends JFrame implements ActionListener{
    private JTextField txt = new JTextField();
    private JTextField cadena = new JTextField();
    private JComboBox AFDop = new JComboBox();
    private JComboBox comboAux;
            int idAux=0;
            ArrayList<String> guardado = new ArrayList<String>();
            String rutaArchivo=null;
            int bandera=0;
        String[] columnNames = {"Token", "Lexema",};
        Object datos[][] = {
            {"", ""},
        };
        //TABLA
        DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        final JTable table = new JTable(dtm);  

        
    public ventanaProbarAnalizadorLexico(){
        setLayout(null);
        //TABLA
        JScrollPane miBarra = new JScrollPane(table);
        this.getContentPane().add(miBarra, null);
        dtm.removeRow(0);
        miBarra.setBounds(220, 255, 400, 200);
        miBarra.setVisible(true);
        table.setVisible(true);
        //**TABLA
        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("Cargar AFD desde archivo");
        etiquetaTituloAux.setBounds(60,30,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        //ETIQUETA IDAsignado-----------------------------------------------------------
        JLabel etiquetaIdAsignado = new JLabel("ID Asignado");
        etiquetaIdAsignado.setBounds(70,70,300,20);
        etiquetaIdAsignado.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaIdAsignado);
        //**ETIQUETA IDAsignado-----------------------------------------------------------
        txt.setBounds(170,70, 100, 25);
        add(txt);
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo");
        btn.setBounds(170, 110, 150, 25);
        btn.addActionListener(this);
        add(btn);
        //**FileChooser--------------------------------------------------------
        //ETIQUETA AFD a utilizar-----------------------------------------------------------
        JLabel etiquetaAFDUtilizar = new JLabel("AFD a utilizar");
        etiquetaAFDUtilizar.setBounds(100,170,300,20);
        etiquetaAFDUtilizar.setFont(new java.awt.Font("arial", 1, 14));

        AFDop.setBounds(220,170,200,20);
        for(AFD e : AFD.ConjAFDs){
            AFDop.addItem(String.valueOf(e.IdAFD));
            System.out.println("VALOROBTENIDO: "+e.IdAFD);
        } 
        add(AFDop);
        add(etiquetaAFDUtilizar);
        
        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
        //ETIQUETA Cadena a utilizar-----------------------------------------------------------
        JLabel etiquetaCadenaAnalizar = new JLabel("Cadena a analizar lexicamente");
        etiquetaCadenaAnalizar.setBounds(100,200,300,20);
        etiquetaCadenaAnalizar.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaCadenaAnalizar);
        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
        cadena.setBounds(330,200, 200, 25);
        add(cadena);
        //Boton Analizar-----------------------------------------------------------
        JButton btnAnalizar = new JButton("Analizar");
        btnAnalizar.setBounds(100, 255, 100, 25);
        btnAnalizar.addActionListener(this);
        add(btnAnalizar);
        //**Boton Analizar--------------------------------------------------------
        //Boton Guardar AFD-----------------------------------------------------------
        JButton btnGuardarAFD = new JButton("Guardar AFD");
        btnGuardarAFD.setBounds(470, 470, 150, 25);
        btnGuardarAFD.addActionListener(this);
        add(btnGuardarAFD);
        //**Boton Guardar AFD--------------------------------------------------------
    } 
    
    
    public void opciones(){
       ventanaProbarAnalizadorLexico uno = new ventanaProbarAnalizadorLexico();
       uno.setBounds(0,0,700,600);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Probar analizador léxico");
       uno.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser("C:\\Users\\ivett\\Desktop\\Septimo semestre\\Compiladores\\Pruebas AFD");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif"); 
        fileChooser.setFileFilter(imgFilter);
        int id = 0;
        int bandera = 0;
        int idAFN = 0;
        if( e.getActionCommand()=="Seleccionar Archivo" ){
            AFD uno = new AFD();
                bandera=1;
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
                Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            AFDop.addItem(String.valueOf(uno.IdAFD));
        }

        if( e.getActionCommand()=="Analizar" ){
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
        if( e.getActionCommand()=="Guardar AFD" ){
            PrintWriter printWriter = null;
            String textToBeWritten = "";
            for(int i=0;i<guardado.size();i++){
                textToBeWritten = textToBeWritten + guardado.get(i);
            }
            {
            try {
                printWriter = new PrintWriter("C:\\Users\\ivett\\Desktop\\Septimo semestre\\Compiladores\\Pruebas AFD\\AFD"+idAux+".txt");
            } catch (FileNotFoundException c) {
                System.out.println("Unable to locate the fileName: " + c.getMessage());
            }
            Objects.requireNonNull(printWriter).println(textToBeWritten);
            printWriter.close();
            }
        }
               
    }
    
    
    private void comboEmpresaMouseClicked(java.awt.event.MouseEvent evt) {                                          
         AFDop.removeAllItems();
         AFDop.addItem("Seleccione...");
    }
    
    
    
    public static void main(String[] args) {
        AFN prueba = new AFN();
        ventanaProbarAnalizadorLexico uno = new ventanaProbarAnalizadorLexico();
        uno.opciones();
        

    }
}
