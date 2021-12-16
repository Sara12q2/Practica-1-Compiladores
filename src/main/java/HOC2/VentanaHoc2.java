package HOC2;


import AnalizadorLexico.AFD;
import AnalizadorLexico.AFN;
import AnalizadorLexico.ER_AFN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class VentanaHoc2 extends JFrame implements ActionListener{
    private JTextField txt = new JTextField();
    private JTextField cadena = new JTextField();
    private JTextField idObtenido = new JTextField();

        
    public VentanaHoc2(){
        setLayout(null);
        
        //ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        JLabel etiquetaTituloAux = new JLabel("Cadena a analizar léxicamente");
        etiquetaTituloAux.setBounds(60,30,300,20);
        etiquetaTituloAux.setFont(new java.awt.Font("arial", 1, 14));
        add(etiquetaTituloAux);
        //**ETIQUETA Titulo Auxiliar-----------------------------------------------------------
        txt.setBounds(60,60, 350, 100);
        add(txt);
       //FileChooser-----------------------------------------------------------
        JButton btn = new JButton("Analizar Lexicamente");
        btn.setBounds(60, 170, 160, 25);
        btn.addActionListener(this);
        add(btn);
        //**FileChooser-------------------------------------------------------- 
        //**ETIQUETA Cadena a analizar-----------------------------------------------------------
        cadena.setBounds(60,210, 350, 220);
        add(cadena);
        idObtenido.setBounds(450,210, 350, 220);
        add(idObtenido);
        //Boton Analizar-----------------------------------------------------------
        JButton btnAnalizarSintacticamente = new JButton("Analizar Sintacticamente");
        btnAnalizarSintacticamente.setBounds(450, 170, 180, 25);
        btnAnalizarSintacticamente.addActionListener(this);
        add(btnAnalizarSintacticamente);
        //**Boton Analizar--------------------------------------------------------
    } 
    
    public void opciones(){
       VentanaHoc2 uno = new VentanaHoc2();
       uno.setBounds(0,0,850,500);
       uno.setVisible(true);
       uno.setLocationRelativeTo(null);
       uno.setTitle("HOC2");
//       uno.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if( e.getActionCommand()=="Analizar Lexicamente" ){
                    Symbol simb;
        String Lexema = new String();
        String CadAux = new String();
        File ArchEntrada = new File("ArchEntrada.txt");
        PrintWriter escribir;
        idObtenido.setText("");
        cadena.setText("");

        try {
            escribir = new PrintWriter(ArchEntrada);
            escribir.print(txt.getText());
            escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaHoc2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        try {
            Reader lector;
            lector = new BufferedReader(new FileReader("ArchEntrada.txt"));
            
            AnalizadorLexico AnalizLexico = new AnalizadorLexico(lector);
            do{
                simb = AnalizLexico.next_token();
                CadAux = Integer.toString(simb.sym);
                Lexema = AnalizLexico.yytext();
                if(simb.sym == AnalizadorSintacticoSym.EOF)
                    CadAux = "Token: " + CadAux + "\tidentToken: FIN \n";
                else
                    switch(simb.sym){
                        case AnalizadorSintacticoSym.Enter:
                            CadAux = "Token: " + CadAux + "\tidentToken: Enter\t Lexema:" +Lexema;
                            break;
                        case AnalizadorSintacticoSym.NUM:
                            CadAux = "Token: " + CadAux + "\tidentToken: NUM\t Lexema:" +Lexema+"\n";
                            break;
                        case AnalizadorSintacticoSym.opSuma:
                            CadAux = "Token: " + CadAux + "\tidentToken: opSuma\t Lexema:" +Lexema+"\n";
                            break;
                        case AnalizadorSintacticoSym.opResta:
                            CadAux = "Token: " + CadAux + "\tidentToken: opResta\t Lexema:" +Lexema+"\n";
                            break;
                        case AnalizadorSintacticoSym.opProd:
                            CadAux = "Token: " + CadAux + "\tidentToken: opProd\t Lexema:" +Lexema+"\n";
                            break;
                        case AnalizadorSintacticoSym.opDiv:
                            CadAux = "Token: " + CadAux + "\tidentToken: opDiv\t Lexema:" +Lexema+"\n";
                            break;                      
                        case AnalizadorSintacticoSym.ParIzq:
                            CadAux = "Token: " + CadAux + "\tidentToken: ParIzq\t Lexema:" +Lexema+"\n";
                            break;                       
                        case AnalizadorSintacticoSym.ParDer:
                            CadAux = "Token: " + CadAux + "\tidentToken: ParDer\t Lexema:" +Lexema+"\n";
                            break;         
                        case AnalizadorSintacticoSym.error:
                            CadAux = "Token: " + CadAux + "\tidentToken: ERROR\t Lexema:" +Lexema+"\n";
                            break;                        
                        case AnalizadorSintacticoSym.VAR:
                            CadAux = "Token: " + CadAux + "\tidentToken: VAR\t Lexema:" +Lexema+"indice = "+ Integer.toString((Integer)simb.value)+"\n";
                            break;      
                        default:
                            CadAux = "Token: " + CadAux + "\tidentToken: OTRO\t Lexema:" +AnalizLexico.yytext()+"\n";
                    }
//                idObtenido.append(CadAux);
            }while(simb.sym!=AnalizadorSintacticoSym.EOF);
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(VentanaHoc2.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IOException ex) {
                Logger.getLogger(VentanaHoc2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if( e.getActionCommand()=="Analizar Sintacticamente" ){

        }
               
    }   
    public static void main(String[] args) {
        VentanaHoc2 uno = new VentanaHoc2();
        uno.opciones();
    }
}
