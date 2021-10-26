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


public class ventanaUnion  extends JFrame implements ActionListener{
    JTextField texto;
    private JButton boton;
    private JComboBox AFN11, AFN2;
    private static AFN AFNAux;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    
     
     public ventanaUnion(AFN AFN1){
        AFNAux = AFN1;
        setLayout(null);
        ConjDeAFNs = AFN1.getListaAFNs();

        JLabel etiquetaUnir = new JLabel("Unir");
        etiquetaUnir.setBounds(30,25,100,30);
        etiquetaUnir.setFont(new java.awt.Font("arial", 1, 14));
        //MENU DESPLEGABLE
        AFN11 = new JComboBox();
        AFN11.setBounds(150,29,200,20);
        for(AFN e : AFN.ConjDeAFNs){
            AFN11.addItem("AFN"+String.valueOf(e.IdAFN));
        }  
        add(etiquetaUnir);
        add(AFN11);
        
        JLabel etiquetaCon = new JLabel("con");
        etiquetaCon.setBounds(380,25,100,30);
        etiquetaCon.setFont(new java.awt.Font("arial", 1, 14));
        //MENU DESPLEGABLE
        AFN2 = new JComboBox();
        AFN2.setBounds(440,29,200,20);
        for(AFN e :AFN.ConjDeAFNs){
            AFN2.addItem("AFN"+String.valueOf(e.IdAFN));
        }
        add(etiquetaCon);
        add(AFN2);

         boton = new JButton("Unir AFN's");
         boton.setBounds(498, 220, 140, 20);
         add(boton);
         boton.addActionListener(this);
    
 }
public void opciones(AFN AFN1){
       ventanaUnion uno = new ventanaUnion(AFN1);
       uno.setBounds(0,0,700,300);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Unión");
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==boton){
            int id1,id2;
            AFN AFNp,AFNunido;
            AFNp=new AFN();
            AFN AFNs = null;
            
            JOptionPane.showMessageDialog(null, "Operación realizada con exito");
            String cad1 = (String)AFN11.getSelectedItem();
            String cad2 = (String)AFN2.getSelectedItem();
            cad1 = cad1.replace("AFN","");
            cad2 = cad2.replace("AFN","");
            id1 = Integer.parseInt(cad1);
            id2 = Integer.parseInt(cad2);
            System.out.println("Id 1: "+id1);
            System.out.println("Id 2: "+id2);
            
            for(AFN a : AFN.ConjDeAFNs){
                if(AFNAux.getIdAFN(a)==id1){
                    AFNp = a;
                   
                }
                if(AFNAux.getIdAFN(a)==id2){
                    AFNs = a;
                }
            }
            
            AFNunido = AFNp.UnirAFN(AFNs);
            AFN.ConjDeAFNs.add(AFNunido);
            AFN.ConjDeAFNs.remove(AFNs);
            
           

        }
    }   
    /*
     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaUnion uno = new ventanaUnion(AFN1);
        uno.opciones(AFN1);
    } 
*/   
}
