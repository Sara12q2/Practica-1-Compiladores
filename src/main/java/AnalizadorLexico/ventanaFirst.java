package AnalizadorLexico;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

public class ventanaFirst extends JFrame {

    private JTextField txt = new JTextField();
    private JTextField txt2 = new JTextField();
    private JTextField txt3 = new JTextField();
    private JTextField txt4 = new JTextField();
    private JTextField txt5 = new JTextField();
//    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JComboBox AFDop = new JComboBox();
    HashSet<String> Resultado = new HashSet<String>();
     HashSet<String> ResultadoFirst = new HashSet<String>();
    public String sigma;
    DescRegGram_Gram AnalizGram;
    int row;
    String nombreArchivo;
    int idAux = 0;
    ArrayList<String> guardado = new ArrayList<String>();
    String rutaArchivo = null;
    int bandera = 0;
    String follo;
    String[] columnNames = {"Simbolo", "Terminal",};
    Object datos[][] = {
        {"", ""},};
    DefaultTableModel dtm = new DefaultTableModel(datos, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    final JTable table = new JTable(dtm);

    String[] columnNames2 = {"Simbolo"};
    Object datos2[][] = {
        {"", ""},};
    DefaultTableModel dtm2 = new DefaultTableModel(datos2, columnNames2) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    final JTable table2 = new JTable(dtm2);

    String[] columnFollow = {"Simbolo No Terminal"};
    Object datosFollow[][] = {
        {"", ""},};
    DefaultTableModel foll = new DefaultTableModel(datosFollow, columnFollow) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    final JTable tableFollow = new JTable(foll);

    String[] CalculacolumnFollow = {"Simbolo"};
    Object CalculadatosFollow[][] = {
        {"", ""},};
    DefaultTableModel Calculafoll = new DefaultTableModel(CalculadatosFollow, CalculacolumnFollow) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    final JTable CalculatableFollow = new JTable(Calculafoll);

    public ventanaFirst() {
        setLayout(null);

        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("First & Follow");
        etiquetaTituloAux.setBounds(20, 30, 300, 20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 11));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        //ETIQUETA IDAsignado-----------------------------------------------------------
        JLabel etiquetaIdAsignado = new JLabel("ID del AFD");
        etiquetaIdAsignado.setBounds(40, 60, 100, 15);
        etiquetaIdAsignado.setFont(new java.awt.Font("arial", 1, 11));
        add(etiquetaIdAsignado);
        //**ETIQUETA IDAsignado-----------------------------------------------------------
        txt.setBounds(100, 60, 100, 25);
        add(txt);
//        txt2.setBounds(300,30, 250, 25);
//        add(txt2);
//        
        //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(205, 60, 180, 25);
        btn.setFont(new java.awt.Font("arial", 1, 10));
//        btn.addActionListener(this);
        add(btn);

        JLabel etiquetaCadena = new JLabel("Cadena a analizar sintacticamente");
        etiquetaCadena.setBounds(20, 70, 300, 100);
        etiquetaCadena.setFont(new java.awt.Font("arial", 1, 11));
        add(etiquetaCadena);
        txt3.setBounds(210, 110, 280, 25);
        add(txt3);

        JButton btn2 = new JButton("Analizar");
        btn2.setBounds(495, 110, 80, 25);
        btn2.setFont(new java.awt.Font("arial", 1, 10));
//        btn2.addActionListener(this);
        add(btn2);

        //TABLA
        JScrollPane miBarra = new JScrollPane(table);
        this.getContentPane().add(miBarra, null);
        dtm.removeRow(0);
        miBarra.setBounds(20, 190, 200, 200);
        miBarra.setVisible(true);
        table.setVisible(true);

        JButton ResetFirst = new JButton("Reset");
        ResetFirst.setBounds(150, 150, 70, 25);
        ResetFirst.setFont(new java.awt.Font("arial", 1, 10));
        add(ResetFirst);

        JButton first = new JButton("First");
        first.setBounds(330, 150, 60, 25);
        first.setFont(new java.awt.Font("arial", 1, 10));
//        first.addActionListener(this);
        add(first);

        txt4.setBounds(230, 150, 100, 26);
        add(txt4);

        JButton ResetFollow = new JButton("Reset");
        ResetFollow.setBounds(475, 150, 70, 25);
        ResetFollow.setFont(new java.awt.Font("arial", 1, 10));
//        follow.addActionListener(this);
        add(ResetFollow);

        JButton follow = new JButton("Follow");
        follow.setBounds(660, 150, 70, 25);
        follow.setFont(new java.awt.Font("arial", 1, 10));
//        follow.addActionListener(this);
        add(follow);

        txt5.setBounds(560, 150, 100, 26);
        add(txt5);

        //TABLA
        JScrollPane miBarra2 = new JScrollPane(table2);
        this.getContentPane().add(miBarra2, null);
        dtm2.removeRow(0);
        miBarra2.setBounds(230, 190, 160, 200);
        miBarra2.setVisible(true);
        table2.setVisible(true);

        JScrollPane BarraFollow = new JScrollPane(tableFollow);
        this.getContentPane().add(BarraFollow, null);
        foll.removeRow(0);
        BarraFollow.setBounds(400, 190, 150, 200);
        BarraFollow.setVisible(true);
        tableFollow.setVisible(true);

        JScrollPane CalculaBarraFollow = new JScrollPane(CalculatableFollow);
        this.getContentPane().add(CalculaBarraFollow, null);
        Calculafoll.removeRow(0);
        CalculaBarraFollow.setBounds(560, 190, 170, 200);
        CalculaBarraFollow.setVisible(true);
        CalculatableFollow.setVisible(true);

        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser("C:\\Users\\LENOVO\\OneDrive\\Documentos\\NetBeansProjects\\Practica-1-Compiladores");

                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("All Files", "txt", "gif");
                fileChooser.setFileFilter(imgFilter);
                int idAFD = 0;
                AFD uno = new AFD();
                int result = fileChooser.showOpenDialog(ventanaFirst.this);
                if (result != JFileChooser.CANCEL_OPTION) {
                    File fileName = fileChooser.getSelectedFile();
                    nombreArchivo = fileName.getName();
                    System.out.println("nombre archivo: " + nombreArchivo);
                    if ((fileName == null) || (fileName.getName().equals(""))) {
                        txt.setText("...");
                    } else {
                        rutaArchivo = fileName.getAbsolutePath();
                        System.out.println("RUTA OBTENIDA: " + rutaArchivo);
                        try {

                            AnalizGram = new DescRegGram_Gram(nombreArchivo, -1);

                        } catch (IOException ex) {
                            Logger.getLogger(ventanaFirst.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                idAFD = Integer.parseInt(txt.getText());
                System.out.println("IdAFD: " + idAFD);
                try {
                    uno.LeerAFDdeArchivo(rutaArchivo, idAFD);
                } catch (IOException ex) {
                    System.out.println("ERROR");
                    Logger.getLogger(ventanaProbarAnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
                }

                AFDop.addItem(String.valueOf(uno.IdAFD));
            }
        }
        );

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cadena;
                cadena = txt3.getText();
                System.out.println("cadena a evaluar: " + cadena);
                AnalizGram.SetGramatica(cadena);
                String[] renglones = new String[2];

                if (AnalizGram.AnalizarGramatica()) {
                    System.out.println("Expresión sintácticamente correcta");
                    JOptionPane.showMessageDialog(null, "Expresión sintácticamente correcta");
//                  System.out.println(AnalizGram.Vn);
                    AnalizGram.IdentificarTerminales();
                    System.out.println("REGLAS :" + AnalizGram.Reglas());

                    System.out.println("No Terminales: " + AnalizGram.Vn);
                    System.out.println("Terminales: " + AnalizGram.Vt);
                    /*Agrega terminales a tabla*/
                    for (String f : AnalizGram.Vt) {

                        renglones[0] = f;
                        renglones[1] = "Terminal";
                        Object[] newRow = {renglones[0], renglones[1]};
                        dtm.addRow(newRow);

                    }
//                    table.isCellEditable(renglones[0], NORMAL);
                    /*Agrega no terminales a la tabla*/
                    for (String f : AnalizGram.Vn) {

                        renglones[0] = f;
                        renglones[1] = "No Terminal";
                        Object[] newRow = {renglones[0], renglones[1]};
                        dtm.addRow(newRow);
                        foll.addRow(newRow);

                    }
//                    

                    /**
                     * ****************TABLA FIRST--ACCIONES*******************
                     */
                    table.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent me) {
                            JTable tableo = (JTable) me.getSource();
                            Point p = me.getPoint();
                            boolean terminal;
                            String primerElement;
                            int row = tableo.rowAtPoint(p);
                            
                            txt4.setText(String.valueOf(tableo.getValueAt(row, 0))+" "+String.valueOf(tableo.getValueAt(row, 1)));
                            primerElement=String.valueOf(tableo.getValueAt(row, 0));
                            System.out.println("primer Element: "+primerElement);
                            System.out.println("Terminal? "+String.valueOf(tableo.getValueAt(row, 1)));
                            if(String.valueOf(tableo.getValueAt(row, 1))=="Terminal"){
                                
                                terminal=true;
                                
                            }else{
                                 
                                terminal=false;
                            }
                            
                            List<ClaseNodo> Arr = new ArrayList<ClaseNodo>();
                            String[] ArrReglas = new String[10];
                           

                            ClaseNodo a, b;
                            a = new ClaseNodo(primerElement, terminal);
                            
//                            String sim = "Ep";
//                            boolean ter = false;
//                            String simb = "T";
//                            boolean terb = false;
//                            HashSet<String> Result = new HashSet<String>();
                            HashSet<String> numeros = new HashSet<String>();
                            String [] renglonFirst=new String[1];
//                            a = new ClaseNodo(String.valueOf(tableo.getValueAt(row, 0)), ter);
//                            b = new ClaseNodo(String.valueOf(tableo.getValueAt(row, 0)), ter);
//                            
//                            b = new ClaseNodo(simb, terb);
//                            System.out.println("pruebaaaaaaaa: " + String.valueOf(tableo.getValueAt(row, 0)) + ter);

                            Arr.add(a);
//                            Arr.add(b);

                            numeros = AnalizGram.First(Arr);
                            Iterator itr = numeros.iterator();
                            while (itr.hasNext()) {
                                System.out.println("First Result: " + itr.next());
                            }
                            
                            for(String one: numeros){
                                renglonFirst[0]=one;
                                Object[] newRow ={renglonFirst[0]};
                                dtm2.addRow(newRow);
                            }
                            
                        }
                    });
                    ///////////////////////////////////////////////////////////
                    first.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            boolean terminal;
                            String primerElement;
                            String cadena = txt4.getText();
                            System.out.println("Texto obtenido: " + cadena);
//                            
//                             primerElement=String.valueOf(tableo.getValueAt(row, 0));
//                            System.out.println("primer Element: "+primerElement);
//                            System.out.println("Terminal? "+String.valueOf(tableo.getValueAt(row, 1)));
//                            if(String.valueOf(tableo.getValueAt(row, 1))=="Terminal"){
//                                
//                                terminal=true;
//                                
//                            }else{
//                                 
//                                terminal=false;
//                            }
//                           
//                            List<ClaseNodo> Arr = new ArrayList<ClaseNodo>();
//                            String[] ArrReglas = new String[10];
//                           
//
//                            ClaseNodo a, b;
//                            a = new ClaseNodo(primerElement, terminal);
//                            
////                            String sim = "Ep";
////                            boolean ter = false;
////                            String simb = "T";
////                            boolean terb = false;
////                            HashSet<String> Result = new HashSet<String>();
//                            HashSet<String> numeros = new HashSet<String>();
////                            a = new ClaseNodo(String.valueOf(tableo.getValueAt(row, 0)), ter);
////                            b = new ClaseNodo(String.valueOf(tableo.getValueAt(row, 0)), ter);
////                            
////                            b = new ClaseNodo(simb, terb);
////                            System.out.println("pruebaaaaaaaa: " + String.valueOf(tableo.getValueAt(row, 0)) + ter);
//
//                            Arr.add(a);
////                            Arr.add(b);
//
//                            numeros = AnalizGram.First(Arr);
//                            Iterator itr = numeros.iterator();
//                            while (itr.hasNext()) {
//                                System.out.println("First Result: " + itr.next());
//                            }
//
////                                    Resultado=AnalizGram.Follow(cadena);
////                                    for(String fg: Resultado){
//                                        System.out.println("Followw: "+fg);
//                                    
//                                    }
//                                    System.out.println("Calculaaaaaaaaaaa");
////                            Resultado = txt4.getText();  
                        }
                    });
                    ResetFirst.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            dtm2.setRowCount(0);
                        }
                    });

                    /**
                     * ****************TABLA
                     * FOLLOW--ACCIONES*******************
                     */
                    tableFollow.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {
                            JTable tablep = (JTable) me.getSource();
                            Point p = me.getPoint();
                            row = tablep.rowAtPoint(p);

                            txt5.setText(String.valueOf(tablep.getValueAt(row, 0)));
//                            txt5.removeActionListener(AFDop); //INVESTIGAR FUNCIONAMIENTO

                        }
                    });
                    follow.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {

                            follo = txt5.getText();
                            System.out.println("Texto obtenido: " + follo);
                            
                            Resultado = AnalizGram.Follow(follo);  //Determina el follow del simbolo

                            String[] renglonFollow = new String[1];
                            System.out.println("Follow calculado: " + Resultado);

                            for (String r : Resultado) {

                                //Agrega el resultado de follow a la tabla
                                renglonFollow[0] = r;

                                Object[] newRow = {renglonFollow[0]};
                                Calculafoll.addRow(newRow);
//                                System.out.println("NeRow: " + newRow.length);
                            }

                        }
                    });
//                    row = 0;
//                    System.out.println("Rowwwwwwwwww: " + row);
                    ResetFollow.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            Calculafoll.setRowCount(0);
 }
                    });
//                    row = 0;

                } else {
                    JOptionPane.showMessageDialog(null, "Expresión sintácticamente incorrecta");
                    System.out.println("Expresión sintácticamente incorrecta.");

                }
            }
        });
    }

    public void opciones() {
        ventanaFirst uno = new ventanaFirst();
        uno.setBounds(0, 0, 770, 490);
        uno.setVisible(true);
        uno.setLocationRelativeTo(null);
        uno.setTitle("First and Follow");
        uno.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ventanaFirst uno = new ventanaFirst();
        uno.opciones();
    }

}
