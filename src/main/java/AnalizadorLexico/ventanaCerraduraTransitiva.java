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

public class ventanaCerraduraTransitiva extends JFrame implements ActionListener{
    private JButton boton;
    private JComboBox AFNop1;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private AFN AFNAux = new AFN();
    
    public ventanaCerraduraTransitiva(AFN AFN1){
        setLayout(null);
        AFNAux = AFN1;
        ConjDeAFNs = AFN1.getListaAFNs();
//ETIQUETA -------------------------------------------------------------------
        JLabel etiquetaCerradura = new JLabel("Cerradura+");
        etiquetaCerradura.setBounds(140,25,100,30);
        etiquetaCerradura.setFont(new java.awt.Font("arial", 1, 14));
        //MENU DESPLEGABLE
        AFNop1 = new JComboBox();
        AFNop1.setBounds(260,29,200,20);
        for(AFN e : ConjDeAFNs){
            AFNop1.addItem("AFN"+String.valueOf(AFN1.getIdAFN(e)));
        }
        add(etiquetaCerradura);
        add(AFNop1);
//*ETIQUETA---------------------------------------------------------------------

//IMAGEN------------------------------------------------------------------------
        JLabel etiquetaImagen = new JLabel();
        ImageIcon imgThisImg = new ImageIcon("Images/cerraduraTransitiva.png");
        etiquetaImagen.setIcon(imgThisImg);
        
        etiquetaImagen.setBounds(90,70,600,220);
        add(etiquetaImagen);
//*IMAGEN-----------------------------------------------------------------------

//BOTON-------------------------------------------------------------------------
    boton = new JButton("Aplicar cerradura+");
    boton.setBounds(380,320,140,20);
    add(boton);
    boton.addActionListener(this);
//*BOTON------------------------------------------------------------------------
    }
    
    public void opciones(AFN AFN1){
       ventanaCerraduraTransitiva uno = new ventanaCerraduraTransitiva(AFN1);
       uno.setBounds(0,0,600,400);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Cerradura transitiva");
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==boton){
            int id1;
            AFN AFNp = null, AFNcerraduraT = null;
            String cad1 = (String)AFNop1.getSelectedItem();
            JOptionPane.showMessageDialog(null, "Operación realizada con exito");
            cad1 = cad1.replace("AFN","");
            id1 = Integer.parseInt(cad1);
            for(AFN a : ConjDeAFNs){
                if(AFNAux.getIdAFN(a)==id1){
                    AFNp = a;
                }
            }           
                AFNp.CerrPos();    
            for(AFN a : AFN.ConjDeAFNs){
                for(Estado b : a.EdosAFN){
                    System.out.println("AFN perteneciente: "+a.getIdAFN(a));
                  
                }
            }
        }
    }   
}
