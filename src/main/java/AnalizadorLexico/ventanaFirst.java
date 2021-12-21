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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ventanaFirst extends JFrame {

    private JTextField txt = new JTextField();
    private JTextField txt2 = new JTextField();
    private JTextField txt3 = new JTextField();
    private JTextField txtNum = new JTextField();
    private JTextField txt4 = new JTextField();
    private JTextField txtTerminal = new JTextField();
    private JTextField txt5 = new JTextField();
//    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();
    private JComboBox AFDop = new JComboBox();
    HashSet<String> Resultado = new HashSet<String>();
    HashSet<String> ResultadoFirst = new HashSet<String>();
    public String sigma;
    JTable tabla = new JTable();
    DescRegGram_Gram AnalizGram;
    int row, primero;
    String elementos;
    String nombreArchivo;
    int idAux = 0;
    ArrayList<String> guardado = new ArrayList<String>();
    String rutaArchivo = null;
    int bandera = 0;
    String follo;
    int contador = 0;
    int n = 3;
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
        //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Seleccionar Archivo del AFD");
        btn.setBounds(205, 60, 180, 25);
        btn.setFont(new java.awt.Font("arial", 1, 10));
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
        add(btn2);

        //TABLA
        JScrollPane miBarra = new JScrollPane(table);
        this.getContentPane().add(miBarra, null);
        dtm.removeRow(0);
        miBarra.setBounds(20, 190, 200, 200); //750
        miBarra.setVisible(true);
        table.setVisible(true);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(230, 190, 200, 200);
        add(scroll);
        DefaultTableModel model = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return String.class;
                }
            }
            boolean[] canEdit = new boolean[]{
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(model);
        model.addColumn("Add");
        model.addColumn("Elemento");
        model.addColumn("Terminal");

        JButton numElemento = new JButton("Add");
        numElemento.setBounds(160, 150, 60, 25);
        numElemento.setFont(new java.awt.Font("arial", 1, 10));
        add(numElemento);

//        txtNum.setBounds(20, 150, 55, 26);
//        add(txtNum);

        JButton ResetFirst = new JButton("Reset");
        ResetFirst.setBounds(230, 150, 70, 25);
        ResetFirst.setFont(new java.awt.Font("arial", 1, 10));
        add(ResetFirst);

        JButton first = new JButton("First");
        first.setBounds(350, 150, 80, 25);
        first.setFont(new java.awt.Font("arial", 1, 10));
        add(first);


        JButton ResetFollow = new JButton("Reset");
        ResetFollow.setBounds(690, 150, 70, 25);
        ResetFollow.setFont(new java.awt.Font("arial", 1, 10));
//        follow.addActionListener(this);
        add(ResetFollow);

        JButton follow = new JButton("Follow");
        follow.setBounds(873, 150, 70, 25);
        follow.setFont(new java.awt.Font("arial", 1, 10));
//        follow.addActionListener(this);
        add(follow);

        txt5.setBounds(775, 150, 100, 26);
        add(txt5);

        //TABLA
        JScrollPane miBarra2 = new JScrollPane(table2);
        this.getContentPane().add(miBarra2, null);
        dtm2.removeRow(0);
        miBarra2.setBounds(440, 190, 160, 200);
        miBarra2.setVisible(true);
        table2.setVisible(true);

        JScrollPane BarraFollow = new JScrollPane(tableFollow);
        this.getContentPane().add(BarraFollow, null);
        foll.removeRow(0);
        BarraFollow.setBounds(610, 190, 150, 200);
        BarraFollow.setVisible(true);
        tableFollow.setVisible(true);

        JScrollPane CalculaBarraFollow = new JScrollPane(CalculatableFollow);
        this.getContentPane().add(CalculaBarraFollow, null);
        Calculafoll.removeRow(0);
        CalculaBarraFollow.setBounds(775, 190, 170, 200);
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
                String[] renglones = new String[3];
                String[] NT = new String[1];

                if (AnalizGram.AnalizarGramatica()) {
                    System.out.println("Expresión sintácticamente correcta");
                    JOptionPane.showMessageDialog(null, "Expresión sintácticamente correcta");
//                  System.out.println(AnalizGram.Vn);
                    AnalizGram.IdentificarTerminales();
                    System.out.println("REGLAS :" + AnalizGram.Reglas());

                    System.out.println("No Terminales: " + AnalizGram.Vn);
                    System.out.println("Terminales: " + AnalizGram.Vt);
                    /*Agrega terminales a tabla*/
//              
                    //////////////////////////////////////////////////////////
                    for (String f : AnalizGram.Vt) {

                        renglones[1] = f;
                        renglones[2] = "Terminal";
                        Object[] newRow = {renglones[1], renglones[2]};
                        dtm.addRow(newRow);

                    }

                    for (String f : AnalizGram.Vn) {

                        renglones[1] = f;
                        renglones[2] = "No Terminal";
                        Object[] newRow = {renglones[1], renglones[2]};
                        dtm.addRow(newRow);

                    }

                    for (String nt : AnalizGram.Vn) {

                        NT[0] = nt;
                        Object[] newRoww = {NT[0]};

                        foll.addRow(newRoww);

                    }

                    /**
                     * ****************TABLA FIRST--ACCIONES*******************
                     */
                    tabla.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent me) {
                            JTable tableo = (JTable) me.getSource();
                            Point p = me.getPoint();
                            String e = "", t = "";
                            primero = tableo.rowAtPoint(p);
                            tableo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            txtTerminal.setText(String.valueOf(tableo.getValueAt(primero, 1)));

                        }
                    }
                    );
                    ////////////////////////////////////////////////////////////
                    numElemento.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            TableModel modelo1 = table.getModel();
                            int[] filas = table.getSelectedRows();
                            Object[] row = new Object[3];
                            DefaultTableModel modelo2 = (DefaultTableModel) tabla.getModel();
                            for (int i = 0; i < filas.length; i++) {
                                row[0] = Boolean.FALSE;
                                row[1] = modelo1.getValueAt(filas[i], 0);
                                row[2] = modelo1.getValueAt(filas[i], 1);
                                modelo2.addRow(row);
                            }

                        }
                    });
                    ///////////////////////////////////////////////////////////

                    first.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {

                            String reportes = "";
                            HashSet<String> Res = new HashSet<String>();
                            ClaseNodo nodo;
                            boolean Termina;
//
                            ArrayList<Boolean> Termi = new ArrayList<Boolean>();
                            ArrayList<String> Terminall = new ArrayList<String>();
                            ArrayList<String> Elemento = new ArrayList<String>();
                            List<ClaseNodo> Arreg = new ArrayList<ClaseNodo>();
                            String element = "", terminal = "";
                            JTextArea area = new JTextArea();
                            if (Seleccionados(0)) {
//
                                for (int i = 0; i < tabla.getRowCount(); i++) {
                                    boolean sel = (boolean) tabla.getValueAt(i, 0);
                                    if (sel) {
                                        element += tabla.getValueAt(i, 1);
                                        terminal += tabla.getValueAt(i, 2);

                                        Elemento.add(String.valueOf(tabla.getValueAt(i, 1)));
                                        Terminall.add(String.valueOf(tabla.getValueAt(i, 2)));
                                    }
                                }
                                System.out.println("Terminalllllll: " + Terminall.toString());
                                for (int j = 0; j < Terminall.size(); j++) {
                                    if (Terminall.get(j) == "Terminal") {
                                        Termina = true;
                                    } else {
                                        Termina = false;
                                    }
                                    Termi.add(Termina);
                                }
//                               
                                System.out.println("Terminallll: " + Termi.toString());
                                System.out.println("Tamaño de Terminall: " + Terminall.size());
                                System.out.println("Tamaño de Termina: " + Termi.size());
                                System.out.println("Elementos: " + Elemento.toString());
//                                System.out.println("Res: "+Res.toString());
                                System.out.println("Arreg: " + Arreg.toString());

                                for (int j = 0; j < Elemento.size(); j++) {

                                    Arreg.add(new ClaseNodo(Elemento.get(j), Termi.get(j)));

                                }
                                System.out.println("Arreglo: " + Arreg.size());
                                System.out.println("Arregloo: " + Arreg.toString());

                                HashSet<String> Resl = new HashSet<String>();
                                Res = AnalizGram.First(Arreg);
                                System.out.println("Resultadooooooooooooooo: " + Res.toString());
                                //////////////IMPRIMIR RESULTADO EN TABLA///////////////////////////
                                for (String f : Res) {

                                    renglones[1] = f;

                                    Object[] newRow = {renglones[1]};
                                    dtm2.addRow(newRow);

                                }

                                ////////////////////////////////////////////////////////////////////
//                              
                                System.out.println("Arreglo: " + Arreg.size());
                                System.out.println("Arregloo: " + Arreg.toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "seleccionae uno", "mensaje", JOptionPane.WARNING_MESSAGE);

                            }
                        }
                    });
                    ResetFirst.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            dtm2.setRowCount(0);
                            model.setRowCount(0);
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
                            }

                        }
                    });
                    ResetFollow.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            Calculafoll.setRowCount(0);
                        }
                    });

                } else {
                    JOptionPane.showMessageDialog(null, "Expresión sintácticamente incorrecta");
                    System.out.println("Expresión sintácticamente incorrecta.");

                }
            }
        });
    }

    public void agregar() {

        List<String> ejemploLista = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            ejemploLista.add(txt4.getText());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Listaaa: " + ejemploLista.get(i));
            System.out.println("size: " + ejemploLista.size());
        }
    }

    public boolean Seleccionados(int pos) {
        int contador = 0;
        boolean bandera = true;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            boolean seleccion = (boolean) tabla.getValueAt(i, pos);
            if (seleccion) {
                contador++;

            }
        }
        if (contador == 0) {
            bandera = false;
        }
        return bandera;
    }

    public void opciones() {
        ventanaFirst uno = new ventanaFirst();
        uno.setBounds(0, 0, 1000, 490);
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
