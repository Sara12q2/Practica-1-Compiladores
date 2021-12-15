package AnalizadorLexico;

//import java.awt.List;
import java.util.ArrayList;
import java.util.List;

public class ClaseNodo {

    public String Simbolo;
    public boolean Terminal;

    public ClaseNodo() {
        Simbolo = "";
        Terminal = false;
    }

    public ClaseNodo(String Simb) {
        Simbolo = Simb;
        Terminal = false;
    }

    public ClaseNodo(String Simb, boolean EsTerminal) {
        Simbolo = Simb;
        Terminal = EsTerminal;
        System.out.println("Nodo Simb: " + Simb);
    }
    
     

}

