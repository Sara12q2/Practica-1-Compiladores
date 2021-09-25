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
    public JButton boton;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private AFN AFNAux = new AFN();
    private JTextField tf;
    private JComboBox combo;
    private JFrame v;
    
    public ventanaMain(){
    setLayout(null);
   
    // Creacion del JTextField
    tf = new JTextField(20);

    // Creacion del JComboBox y añadir los items.
    combo = new JComboBox();
    combo.addItem("Básico");
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

    // Accion a realizar cuando el JComboBox cambia de item seleccionado.
    combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    tf.setText(combo.getSelectedItem().toString());
            }
    });

    // Creacion de la ventana con los componentes
    v = new JFrame();
    v.getContentPane().setLayout(new FlowLayout());
    v.getContentPane().add(combo);
    v.getContentPane().add(tf);
    v.pack();
    v.setVisible(true);
    v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    /*BOTONES*/            
    boton = new JButton("Aplicar Seleccion");
    boton.setBounds(380,320,140,20);
    add(boton);
    boton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
             if (ae.getSource()==combo) { //aqui detecta si se hace un cambio en el JComboBox
 
		 String seleccion=(String)combo.getSelectedItem(); //Se hace una variable que contiene lo que dice la opcion seleccionada
 
		 switch (seleccion){ //con el switch comparamos cada opcion posible y le damos una accion
		 case "Básico":
			AFN AFN1=new AFN();
                        ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        uno.opciones(AFN1);
                        //hide();
			break;
		 case "Unir":
			AFN AFN2=new AFN();
                        ventanaCerraduraTransitiva dos = new ventanaCerraduraTransitiva(AFN2);
                        dos.opciones(AFN2);
                        //hide();
			break;
		 case "Concatenar":
			AFN AFN3=new AFN();
                         ventanaConcatenacion tres = new ventanaConcatenacion(AFN3);
                        tres.opciones(AFN3);
                     
                        //hide();
			 break;
		 case "Cerradura +":
			AFN AFN4=new AFN();
                        ventanaCerraduraTransitiva cuatro = new ventanaCerraduraTransitiva(AFN4);
                        cuatro.opciones(AFN4);
                        //hide();
			 break;
                 case "Cerradura *":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        //uno.opciones(AFN1);
                        //hide();
			 break;
                  case "Opcional":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        //uno.opciones(AFN1);
                        //hide();
			 break;
                   case "ER->AFN":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        //uno.opciones(AFN1);
                        //hide();
			 break;
                  case "Unión para Analizador Léxico":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        //uno.opciones(AFN1);
                        //hide();
			 break;
                 case "Convertir AFN A AFD":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        //uno.opciones(AFN1);
                        //hide();
			 break;
                 case "Analizar una cadena":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                        //uno.opciones(AFN1);
                        //hide();
			 break;
                  case "Probar analizador lexico":
			//AFN AFN1=new AFN();
                        //ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
                       // uno.opciones(AFN1);
                        //hide();
			 break;
                       
		 }
	 }
           
        }
    });
        
    
    }
    
    public void ventanaMenu(){
       ventanaMain uno = new ventanaMain();
       uno.setBounds(0,0,600,400);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Main");
    }
    
 
}

