package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;

public class ventanaMain extends JFrame {

    public JButton boton, botonAS, botonDR;
    public JTextField tf, tfAS, tfDR;
    public JComboBox combo;
    public JComboBox comboAS, comboDR;
    public JFrame v;

    public ventanaMain(){
        // Creacion de la ventana con los componentes
        //v = new JFrame();
        // v.getContentPane().setLayout(new FlowLayout());
        //v.pack();
        //v.setVisible(true);
        //v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(null);

        // Creacion del JTextField
        tf = new JTextField(50);
        add(tf);

        tfAS = new JTextField(50);
        add(tfAS);

        tfDR = new JTextField(50);
        add(tfDR);

        JLabel AFN = new JLabel("AFN´s");
        AFN.setBounds(20, 10, 150, 30);
        add(AFN);

        JLabel AS = new JLabel("Analísis Sintactico");
        AS.setBounds(230, 10, 150, 30);
        add(AS);

        JLabel Sub = new JLabel("Submenú");
        Sub.setBounds(390, 10, 150, 30);
        add(Sub);

        // Creacion del JComboBox y añadir los items.
        combo = new JComboBox();
        combo.setBounds(20, 35, 200, 20);
        combo.addItem("Basico");
        combo.addItem("Unir");
        combo.addItem("Concatenar");
        combo.addItem("Cerradura +");
        combo.addItem("Cerradura *");
        combo.addItem("Opcional");
        combo.addItem("ER->AFN");
        combo.addItem("Unión para Analizador Léxico");
        combo.addItem("Convertir AFN A AFD");
        combo.addItem("Analizar una cadena");
        combo.addItem("Probar analizador lexico");
        add(combo);

        comboAS = new JComboBox();
        comboAS.setBounds(230, 35, 150, 20);
        comboAS.addItem("Descenso Recursivo");
        comboAS.addItem("Descenso Rec Gram de Gram");
        comboAS.addItem("Análisis LL(1)");
        add(comboAS);

        comboDR = new JComboBox();
        comboDR.setBounds(390, 35, 150, 20);
        comboDR.addItem("Calculadora");
        comboDR.addItem("DescRegGram_Gram");
        add(comboDR);
        comboDR.setVisible(false);

        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText(combo.getSelectedItem().toString());
            }
        });

        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
        comboAS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfAS.setText(comboAS.getSelectedItem().toString());
            }
        });

        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
        comboDR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfDR.setText(comboDR.getSelectedItem().toString());
            }
        });

        /*BOTONES*/
        boton = new JButton("Seleccion AFN´s");
        boton.setBounds(20, 320, 160, 20);
        add(boton);
        botonAS = new JButton("Seleccion AS");
        botonAS.setBounds(230, 320, 150, 20);
        add(botonAS);
        botonDR = new JButton("Seleccion Submenú");
        botonDR.setBounds(390, 320, 150, 20);
        add(botonDR);
        botonDR.setVisible(false);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AFN AFN1 = new AFN();
                if (ae.getSource() == boton) { //aqui detecta si se hace un cambio en el JComboBox
                    String seleccion = (String) combo.getSelectedItem(); //Se hace una variable que contiene lo que dice la opcion seleccionada             
                    switch (seleccion) { //con el switch comparamos cada opcion posible y le damos una accion
                        case "Basico":
                            System.out.println("b4");
                            ventanaBasicoo uno = new ventanaBasicoo(AFN1);
                            uno.opciones(AFN1);
                            //hide();
                            break;
                        case "Unir":
                            ventanaUnion dos = new ventanaUnion(AFN1);
                            dos.opciones(AFN1);
                            //hide();
                            break;
                        case "Concatenar":
                            ventanaConcatenacion tres = new ventanaConcatenacion(AFN1);
                            tres.opciones(AFN1);

                            //hide();
                            break;
                        case "Cerradura +":

                            ventanaCerraduraTransitiva cuatro = new ventanaCerraduraTransitiva(AFN1);
                            cuatro.opciones(AFN1);
                            //hide();
                            break;
                        case "Cerradura *":

                            ventanaCerraduraKleen cinco = new ventanaCerraduraKleen(AFN1);
                            cinco.opciones(AFN1);
                            //hide();
                            break;
                        case "Opcional":
                            ventanaOpcion seis = new ventanaOpcion(AFN1);
                            seis.opciones(AFN1);

                            //hide();
                            break;
                        case "ER->AFN":
                            ventanaER_AFN siete = new ventanaER_AFN(AFN1);
                            siete.opciones(AFN1);
                            break;
                        case "Unión para Analizador Léxico":
                            ventanaUnirAFNLexico ocho = new ventanaUnirAFNLexico(AFN1);
                            ocho.opciones(AFN1);
                            //hide();
                            break;
                        case "Convertir AFN A AFD":
                            ventanaConvertirAFNaAFD nueve = new ventanaConvertirAFNaAFD(AFN1);
                            nueve.opciones(AFN1);
                            //hide();
                            break;
                        case "Analizar una cadena":
                            //AFN AFN1=new AFN();
                            ventanaEvaluadorExpr diez = new ventanaEvaluadorExpr();
                            diez.opciones();
                            //hide();
                            break;
                        case "Probar analizador lexico":
                            //AFN AFN1=new AFN();
                            ventanaProbarAnalizadorLexico once = new ventanaProbarAnalizadorLexico();
                            once.opciones();
                            // uno.opciones(AFN1);
                            //hide();
                            break;

                    }
                }

            }
        });

        botonAS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AFN AFN1 = new AFN();

                if (ae.getSource() == botonAS) { //aqui detecta si se hace un cambio en el JComboBox

                    String seleccion = (String) comboAS.getSelectedItem(); //Se hace una variable que contiene lo que dice la opcion seleccionada

                    switch (seleccion) { //con el switch comparamos cada opcion posible y le damos una accion

                        case "Descenso Recursivo":
                            comboDR.setVisible(true);
                            botonDR.setVisible(true);
                            //hide();
                            break;
                        case "Descenso Rec Gram de Gram":
                            ventanaFirst vF = new ventanaFirst();
                            vF.opciones();
                            //hide();
                            break;
                        case "Análisis LL(1)":
                            ventanaAnalisisLL1 va = new ventanaAnalisisLL1();
                            va.opciones();
                            //hide();
                            break;

                    }
                }

            }
        });

        botonDR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AFN AFN1 = new AFN();

                if (ae.getSource() == botonDR) { //aqui detecta si se hace un cambio en el JComboBox

                    String seleccion = (String) comboDR.getSelectedItem(); //Se hace una variable que contiene lo que dice la opcion seleccionada

                    switch (seleccion) { //con el switch comparamos cada opcion posible y le damos una accion

                        case "Calculadora":
                            ventanaEvaluadorExpr uno = new ventanaEvaluadorExpr();
                            uno.opciones();
                            //hide();
                            break;

                        case "DescRegGram_Gram":
                            ventanaFirst des = new ventanaFirst();
                            des.opciones();
                            //hide();
                            break;
                        case "Unir":

                            ventanaUnion dos = new ventanaUnion(AFN1);
                            dos.opciones(AFN1);
                            //hide();
                            break;
                        case "Concatenar":

                            ventanaConcatenacion tres = new ventanaConcatenacion(AFN1);
                            tres.opciones(AFN1);

                            //hide();
                            break;

                    }
                }

            }
        });
         
    
    }

    public void ventanaMenu() {
        ventanaMain obj = new ventanaMain();
        obj.setBounds(0, 0, 600, 400);
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        obj.setTitle("Menu");
        obj.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ventanaMain o = new ventanaMain();
        o.ventanaMenu();
    }

}
