package AnalizadorLexico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
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
    private JComboBox AFDop;
    private JComboBox comboAux;
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
        JTextField caja = new JTextField();
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
        AFDop = new JComboBox();
        AFDop.setBounds(220,170,200,20);
         for(AFN e : AFN.ConjDeAFNs){            
             System.out.println(e.IdAFN);
        } 
        for(AFN e : AFN.ConjDeAFNs){
            AFDop.addItem("AFN "+String.valueOf(e.IdAFN));
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"); 
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
                Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
//        comboAux = new JComboBox();
//        comboAux.setBounds(320,170,200,20);    
//        for(AFN d : AFN.ConjDeAFNs){
//            comboAux.addItem("AFN "+String.valueOf(d.IdAFN));
//        } 
//         add(comboAux);      
        }
        if( e.getActionCommand()=="Analizar" ){
            int token;
            String Lexema;
            String sigma;
            String[] ValoresRenglon = new String[2];
            AnalizadorLexico L;
            AFD AutFD = new AFD();
            sigma = cadena.getText();
            System.out.println("Sigma: "+sigma);
            for(AFD f : AFD.ConjAFDs){
                if(f.IdAFD == id){
                    AutFD = f;
                    break;
                }
                try {
                    L = new AnalizadorLexico(sigma, AutFD);
                            while(true){
                                token = L.yylex();
                                Lexema = L.Lexema;
                                ValoresRenglon[0] = String.valueOf(token);
                                ValoresRenglon[1] = L.Lexema;
                                System.out.println("Valor 1: "+ValoresRenglon[0]);
                                System.out.println("Valor 2: "+ValoresRenglon[1]);
                                Object[] newRow = {ValoresRenglon[0],ValoresRenglon[1]};
                                dtm.addRow(newRow);
                    //            dataGridView.Rows.Add(ValoresRenglon);
                                L.UndoToken();
                                token = L.yylex();
                                Lexema = L.Lexema;
                                ValoresRenglon[0] = String.valueOf(token);
                                ValoresRenglon[1] = L.Lexema;
                                System.out.println("Valor 1: "+ValoresRenglon[0]);
                                System.out.println("Valor 2: "+ValoresRenglon[1]);
                                Object[] newRow2 = {ValoresRenglon[0],ValoresRenglon[1]};
                                dtm.addRow(newRow2);
                    //            dataGridView1.Rows.Add(ValoresRenglon);
                                if(token == SimbolosEspeciales.FIN)
                                    break;
                            }
                } catch (IOException ex) {
                    Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if( e.getActionCommand()=="Guardar AFD" )
            System.out.println( "Se ha pulsado el boton B3" );
               
    }
    
    
    private void comboEmpresaMouseClicked(java.awt.event.MouseEvent evt) {                                          
         AFDop.removeAllItems();
         AFDop.addItem("Seleccione...");
    }
    
    
    
    public static void main(String[] args) {
        ventanaProbarAnalizadorLexico uno = new ventanaProbarAnalizadorLexico();
        uno.opciones();
        

    }
}
