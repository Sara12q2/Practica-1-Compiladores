
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
    public JTextField tf;
    public JComboBox combo;
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
    // Creacion del JComboBox y añadir los items.
    combo = new JComboBox();
    combo.setBounds(260,29,200,20);
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

    // Accion a realizar cuando el JComboBox cambia de item seleccionado.
    combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    tf.setText(combo.getSelectedItem().toString());
            }
    });

    
    /*BOTONES*/            
    boton = new JButton("Aplicar Seleccion");
    boton.setBounds(380,320,140,20);
    add(boton);
    boton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
            AFN AFN1=new AFN();
            
            
             if (ae.getSource()==boton) { //aqui detecta si se hace un cambio en el JComboBox
 
		 String seleccion=(String)combo.getSelectedItem(); //Se hace una variable que contiene lo que dice la opcion seleccionada
 
		 switch (seleccion){ //con el switch comparamos cada opcion posible y le damos una accion
	
                     case "Basico":
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
       ventanaMain obj = new ventanaMain();
       obj.setBounds(0,0,600,400);
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
