package AnalizadorLexico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

    public ventanaProbarAnalizadorLexico(){
        setLayout(null);
        JTextField caja = new JTextField();
        String[] columnNames = {" ", "Token", "Lexema",};
        Object datos[][] = {
            {" ", "Token", "Lexema"},
            {-1,-1,-1,-1},
            {-1,-1,-1,-1},
            {-1,-1,-1,-1},
          };
        //TABLA
        DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
        final JTable table = new JTable(dtm);

        // Agregar nueva columna

        // Agregar nueva fila
        Object[] newRow = {-1, -1,-1};
        dtm.addRow(newRow);
        
       // table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        JScrollPane scrollPane = new JScrollPane(table);
        //getContentPane().add(scrollPane, BorderLayout.CENTER);
        table.setBounds(220, 260, 350, 100);
        add(table);
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
        btn.setBounds(170, 110, 100, 25);
        btn.addActionListener(this);
        add(btn);
        //**FileChooser--------------------------------------------------------
        //ETIQUETA AFD a utilizar-----------------------------------------------------------
        JLabel etiquetaAFDUtilizar = new JLabel("AFD a utilizar");
        etiquetaAFDUtilizar.setBounds(100,170,300,20);
        etiquetaAFDUtilizar.setFont(new java.awt.Font("arial", 1, 14));
        AFDop = new JComboBox();
        AFDop.setBounds(220,170,200,20);
        
            AFDop.addItem("AFN");
            AFDop.addItem("AFN1");
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
        btnGuardarAFD.setBounds(450, 400, 150, 25);
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

        int result = fileChooser.showOpenDialog(this);
        if (result != JFileChooser.CANCEL_OPTION) {
            File fileName = fileChooser.getSelectedFile();
        if ((fileName == null) || (fileName.getName().equals(""))) {
            txt.setText("...");
        } else {
            txt.setText(fileName.getAbsolutePath());
        }
        }
        
        int token;
        String Lexema;
        String[] ValoresRenglon = new String[2];
        AnalizadorLexico L;
        AFD AutFD = new AFD();
        for(AFD f : AFD.ConjAFDs){
//            if(f.IdAFD == NumAFD){
//                AutFD = f;
//                break;
//            }
//        L = new AnalizadorLexico(txt, AutFD);
//        while(true){
//            token = L.yylex();
//            Lexema = L.Lexema;
//            ValoresRenglon[0] = String.valueOf(token);
//            ValoresRenglon[1] = L.Lexema;
//            dataGridView.Rows.Add(ValoresRenglon);
//            L.UndoToken();
//            token = L.yylex();
//            Lexema = L.Lexema;
//            ValoresRenglon[0] = String.valueOf(token);
//            ValoresRenglon[1] = L.Lexema;
//            dataGridView1.Rows.Add(ValoresRenglon);
//            if(token == SimbolosEspeciales.FIN)
//                break;
//        }
        }
        
        
    }
    
    public static void main(String[] args) {
        ventanaProbarAnalizadorLexico uno = new ventanaProbarAnalizadorLexico();
        uno.opciones();
        

    }
}
