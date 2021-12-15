package AnalizadorLexico;

import AnalizadorLexico.ClaseNodo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;




public class DescRegGram_Gram {


    public String Gramatica;
    public AnalizadorLexico L;
    public ElemArreglo[] ArrReglas = new ElemArreglo[100];
    public int NumReglas = 0;

    HashSet<String> Vn = new HashSet<String>();  //conjunto de nodos no terminales
    HashSet<String> Vt = new HashSet<String>();  //conjunto de nodos terminales

    public DescRegGram_Gram(String sigma, String FileAFD, int IdentifAFD) throws IOException {
        Gramatica = sigma;
        L = new AnalizadorLexico(Gramatica, FileAFD, IdentifAFD);
        Vn.clear();
        Vt.clear();
    }

    public DescRegGram_Gram(String FileAFD, int IdentifAFD) throws IOException {
        L = new AnalizadorLexico(FileAFD, IdentifAFD);
        Vn.clear();
        Vt.clear();
    }

    public boolean SetGramatica(String sigma) {
        Gramatica = sigma;
        L.SetSigma(sigma);
        return true;
    }

    public boolean AnalizarGramatica() {
        int token;
        if (G()) {
            token = L.yylex();
            if (token == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean G() {
        if (ListaReglas()) {
            return true;
        }
        return false;
    }

    public boolean ListaReglas() {
        int token;
        if (Reglas()) {
            token = L.yylex();
            if (token == TokensGram_Gram.PC) {
                if (ListaReglasP()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean ListaReglasP() {
        int token;
        ClassEstadoAnalizadorLexico e;
        e = L.GetEdoAnalizLexico();
        if (Reglas()) {
            token = L.yylex();
            if (token == TokensGram_Gram.PC) {
                if (ListaReglasP()) {
                    return true;
                }
            }
            return false;
        }
        //epsilon
        L.SetEdoAnalizLexico(e);
        return true;
    }

//    boolean Reglas() {
//        int token;
//        String Simbolo = "";
////      new ClaseNodo(Simbolo,false);
//        if (LadoIzquierdo(Simbolo)) {
//            Vn.add(Simbolo);
//            System.out.println("Claseeeeeeeeeeeeeee: " + new ClaseNodo(Simbolo).toString());
//            System.out.println("Vn: " + Vn.toString());
//            token = L.yylex();
//            if (token == TokensGram_Gram.FLECHA) {
//                if (LadosDerechos(Simbolo)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    boolean Reglas() {
        
        int token;
        
        Simb sim=new Simb();
        String Simbolo="";
        
        
        if (LadoIzquierdo(sim)) {
            
            Vn.add(sim.simbolo);   
            System.out.println("Vn: " + Vn.toString());
            token = L.yylex();
            if (token == TokensGram_Gram.FLECHA) {
                if (LadosDerechos(sim.simbolo)) {
                    return true;
                }
            }
        }
        return false;
    }
    

    boolean LadoIzquierdo(Simb Simbolo) {
        int token;
        token = L.yylex();
        if (token == TokensGram_Gram.SIMBOLO) {
            Simbolo.simbolo = L.Lexema;
            Simbolo.esTerminal=false;
//            Vn.add(Simbolo);
//            System.out.println("Vnnnnnnnnnnnnnnn: " + Vn.toString());
//            System.out.println("Simboloooooooooooooooooooooooooooooooooo No TERMINAL: " + Simbolo);
            return true;
        }
        return false;
    }

    boolean LadosDerechos(String Simbolo) {
        if (LadoDerecho(Simbolo)) {
            if (LadosDerechosP(Simbolo)) {
                return true;
            }
        }
        return false;
    }

    boolean LadosDerechosP(String Simbolo) {
        int token;
        token = L.yylex();
        if (token == TokensGram_Gram.OR) {
            if (LadoDerecho(Simbolo)) {
                if (LadosDerechosP(Simbolo)) {
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    boolean LadoDerecho(String Simbolo) {
        ClaseNodo elem = new ClaseNodo();
        ArrayList<ClaseNodo> Lista = new ArrayList<ClaseNodo>();
        ArrayList[] ref = new ArrayList[1];
        
//        ref[0] = Lista;
        Lista.clear();
        if (SecSimbolos(Lista)) {
            ArrReglas[NumReglas]=new ElemArreglo();
            ArrReglas[NumReglas].InfSimbolo = new ClaseNodo(Simbolo, false);
            ArrReglas[NumReglas++].ListaLadoDerecho = Lista;

            return true;
        }
        return false;
    }

    boolean SecSimbolos(ArrayList Lista) {
        int token;
        ClaseNodo N;
        token = L.yylex();
        if (token == TokensGram_Gram.SIMBOLO) {
            N = new ClaseNodo(L.Lexema);
            if (SecSimbolosP(Lista)) {
                Lista.add(0, N);
                return true;
            }
        }
        return false;
    }

    boolean SecSimbolosP(ArrayList Lista) {
        int token;
        ClaseNodo N;
        token = L.yylex();
        if (token == TokensGram_Gram.SIMBOLO) {
            N = new ClaseNodo(L.Lexema);
            if (SecSimbolosP(Lista)) {
                Lista.add(0, N);
                return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    void IdentificarTerminales() {
        int i = 0;
        System.out.println("Numero de reglas: " + NumReglas);
        for (i = 0; i < NumReglas; i++) {

            for (ClaseNodo N : ArrReglas[i].ListaLadoDerecho) {
                if (!Vn.contains(N.Simbolo)) {
                    N.Terminal = true;
                    Vt.add(N.Simbolo);
                    ///////////IMPRIME////////////////
                    System.out.println("Vt: "+Vt);
//                    Iterator itr = Vt.iterator();
//                    while (itr.hasNext()) {
//                        System.out.println("Vt: " + itr.next());
//                    }
                    
                }
            }
        }
    }

    public HashSet<String> Follow(String SimbNoTerm) {
        HashSet<String> R = new HashSet<String>();
        HashSet<String> Aux = new HashSet<String>();
        List<ClaseNodo> ListaPost = new ArrayList<ClaseNodo>();
        R.clear();
        int i, j, k;
        if (ArrReglas[0].InfSimbolo.Simbolo.equals(SimbNoTerm)) {
            R.add("$");
        }
        for (i = 0; i < NumReglas; i++) {
            for (j = 0; j < ArrReglas[i].ListaLadoDerecho.size(); j++) {
                if (ArrReglas[i].ListaLadoDerecho.get(j).Simbolo.equals(SimbNoTerm)) {
                    ListaPost.clear();
                    //Se obtiene la lista que corresponden a los simbolos que estan despues de SimbNoTerm
                    for (k = j + 1; k < ArrReglas[i].ListaLadoDerecho.size(); k++) {
                        ListaPost.add(ArrReglas[i].ListaLadoDerecho.get(k));
                    }
                    //Si no hay mas simbolos despues de SimbTerm, se calcula el follow del lado izquierdo de la regla
                    if (ListaPost.isEmpty()) {
                        //Si el simbolo del lado izquierdo es igual al simbolo del que se quiere calcular el follow, se omite la regla
                        if (!ArrReglas[i].InfSimbolo.Simbolo.equals(SimbNoTerm)) {
                            R.addAll(Follow(ArrReglas[i].InfSimbolo.Simbolo));
                        }
                        break;
                    }
                    //Se calcula el first de la lista l despues de cada elemento j
                    Aux.clear();
                    Aux = First(ListaPost);
                    if (Aux.contains("epsilon")) {
                        Aux.remove("epsilon");
                        R.addAll(Aux);

                        if (!ArrReglas[i].InfSimbolo.Simbolo.equals(SimbNoTerm)) {
                            R.addAll(Follow(ArrReglas[i].InfSimbolo.Simbolo));
                        }
//                            R.addAll(Follow(ArrReglas[i].InfSimbolo.Simbolo));
                    } else {
                        R.addAll(Aux);
                    }
                }
            }

        }

//        System.out.println("REsultado: " + R.toString());
        return R;
    }

    public HashSet<String> First(List<ClaseNodo> l) { //calcula first de una secuencia de cada simbolo
        int i, j;
        ClaseNodo N = new ClaseNodo();
        HashSet<String> R = new HashSet<String>();
        R.clear();
        if (l.size() == 0) {

            return R;
        }
        for (j = 0; j < l.size(); j++) { //Recorre los nodos de la lista
            N = l.get(j);
//            l.set(j, l.get(j)); //recorre la lista como arreglo
            if (N.Terminal || N.Simbolo.equals("epsilon")) {
                R.add(N.Simbolo);

                return R;

            }
            //N no es terminal, se calcula el first de cada lado derecho  de este no terminal
            for (i = 0; i < NumReglas; i++) {
                if (ArrReglas[i].ListaLadoDerecho.get(0).Simbolo == N.Simbolo) {
                    continue;
                }

                if (ArrReglas[i].InfSimbolo.Simbolo.equals(N.Simbolo)) {
                    R.addAll(First(ArrReglas[i].ListaLadoDerecho));
                }

            }

            if (R.contains("epsilon")) {
                if (j == (l.size() - 1)) {
                    continue;
                }
                R.remove("epsilon");
            } else {
                break;
            }

        }
        return R;
    }
}
