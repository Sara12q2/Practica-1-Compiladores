
package AnalizadorLexico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.sql.DriverManager.println;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ventanaBasicoo extends JFrame implements ActionListener{
    private JButton boton;
    private JComboBox AFNop2, AFNop1;
    private JTextField infe,supe,id;
    private static AFN AFNAux;
    private static Transicion t;
    private char s;
    private HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
     
     public ventanaBasicoo(AFN AFN1){
        AFNAux = AFN1;
        setLayout(null);
        ConjDeAFNs = AFN1.getListaAFNs();
//ETIQUETA UNIR----------------------------------------------------------
        JLabel Inf,Sup,Id;
        Inf=new JLabel("Caract.Inf");
        Inf.setBounds(20,20,80,20);
        
        infe=new JTextField();
        infe.setBounds(100,20,80,20);
        add(infe);
        infe.setText(infe.getText());
      
        
        
        Sup=new JLabel("Caract.Sup");
        Sup.setBounds(200,20,80,20);
        
        supe=new JTextField();
        supe.setBounds(280,20,80,20);
        add(supe);
         String var2=supe.getText();
        
        Id=new JLabel("Id AFN");
        Id.setBounds(400,20,80,20);
        
        id=new JTextField();
        id.setBounds(470,20,80,20);
        add(id);
        String var3=id.getText();
        
        add(Inf);
        add(Sup);
        add(Id);



////IMAGEN------------------------------------------------------------------------
        JLabel etiquetaImagen = new JLabel();
        ImageIcon imgThisImg = new ImageIcon("Images/ImageBasico.png");
        etiquetaImagen.setIcon(imgThisImg);
        
        etiquetaImagen.setBounds(80,70,600,120);
        add(etiquetaImagen);
//*IMAGEN-----------------------------------------------------------------------

//BOTON-------------------------------------------------------------------------
    boton = new JButton(" Crear AFN");
    boton.setBounds(250,207,140,50);
    add(boton);
    boton.addActionListener(this);
//*BOTON------------------------------------------------------------------------
    }
     
    
public void opciones(AFN AFN1){
       ventanaBasicoo uno = new ventanaBasicoo(AFN1);
       uno.setBounds(0,0,610,300);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("Básico");
       
    }
    
@Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==boton){
            int inf,sup,ide,id1 = 0,po;
            AFN AFNp=null,AFNcreado = null,a = null;
            Transicion in,su,trans;
            JOptionPane.showMessageDialog(null, "Operación realizada con exito");
            char cI = infe.getText().charAt(0);
            char cS = supe.getText().charAt(0);
            String nId=  (String)id.getText();
            System.out.println(nId);
            System.out.println(cI);
            System.out.println(cS);

            ide = Integer.parseInt(nId);
           
                AFNAux.setIdAFN(ide);
                
                po=AFNAux.IdAFN;
//                ConjDeAFNs.add(po);
//                AFNAux.agregarAFNaLista(AFNAux.IdAFN);
             
            
                }
    }   
    /*
     public static void main(String[] args) {
         AFN AFN1=new AFN();
        ventanaBasicoo uno = new ventanaBasicoo(AFN1);
        uno.opciones(AFN1);
         System.out.println("");
    } 
*/
}


    

