package AnalizadorLexico;

import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;
import java.sql.Array;
import java.util.HashSet;
import java.util.Stack;
import java.util.stream.IntStream;

public class CrearTabla {
DescRegGram_Gram AnalizGram;
    class SimbTerm {

        public String Simbolo;
        public int ValToken;

        public SimbTerm(String Simb, int Tok) {
            Simbolo = Simb;
            ValToken = Tok;
        }

        public SimbTerm() {
            Simbolo = "";
            ValToken = -1;
        }

    }

    class AnalizadorLL1 {

        DescRegGram_Gram DescRegG;
        AnalizadorLexico LexGram;
        public String Gram;
        public String Sigma;
        public int[][] TablaLL1;
        public SimbTerm[] Vt;
        public String[] Vtaux;
        public String[] Vn;
        public String[] vt2;
        public String[] Vt3;
//       const String ArchAFDLexiGramGram = "RUTA";
        String ArchAFDLexiGramGram = "C:\\laragon\\www\\Practica-1-Compiladores\\AFDGramGram.txt";
        public AnalizadorLL1(String CadGramatica, String ArchAFDLexic) throws IOException {
            Gram = CadGramatica;
            DescRegG = new DescRegGram_Gram(CadGramatica, ArchAFDLexiGramGram, 0);
            LexGram = new AnalizadorLexico(ArchAFDLexiGramGram, 1);
        }

        public AnalizadorLL1(String CadGramatica) throws IOException {
            Gram = CadGramatica;
            DescRegG = new DescRegGram_Gram(CadGramatica, ArchAFDLexiGramGram, 0);
        }
        
        public void SetLexico(String ArchAFDLexic) throws IOException {
            LexGram = new AnalizadorLexico(ArchAFDLexic, 1);
        }

        public void CrearTablaLL1() {
            int j;
            HashSet<String> ResultFirst = new HashSet<String>();
            HashSet<String> ResultFollow = new HashSet<String>();
//             
            DescRegG.AnalizarGramatica();
            DescRegG.IdentificarTerminales();
            Vtaux = new String[DescRegG.Vt.size()];
            Vt = new SimbTerm[DescRegG.Vt.size()+1]; //Numero de columnas de la tabla LL1
            vt2 = new String[DescRegG.Vt.size() + 1]; //Arreglo de terminales
            Vn = new String[DescRegG.Vn.size() + 1]; //Filas Tabla
            System.out.println("Vt: "+Vt);
            System.out.println("VT2: "+vt2.toString()+"lenght :"+vt2.length);
            j = 0;
            //Llenando de terminales
//             DescRegG.IdentificarTerminales();
            for (String s : DescRegG.Vt) {
                Vt[j] = new SimbTerm(s, -1); //Aún no hay token
                vt2[j++] = s; 
            }

            Vt[j] = new SimbTerm("$", -1);
             System.out.println("Vt sizee: "+vt2.length);
            vt2[j++] = "$";
            System.out.println("Vt size: "+vt2.length);
            j = 0;
            for (String s : DescRegG.Vn) {
                Vn[j++] = s;
            }
            Vn[j++] = "$";

            TablaLL1 = new int[DescRegG.Vn.size() + 1][DescRegG.Vt.size() + 1];
            for (int k = 0; k <= DescRegG.Vn.size(); k++) {
                for (int l = 0; l <= DescRegG.Vt.size(); l++) {
                    
                    TablaLL1[k][l] = -1;
                }
            }
            int renglon;
            int columna;
            //Se analiza cada una de las reglas e iremos colocando las acciones en el renglon correspondiente a la regla
            for (int NumRegla = 0; NumRegla < DescRegG.NumReglas; NumRegla++) {
                ResultFirst.clear();
                ResultFollow.clear();
                //EQUIVALENCIA PARA INDEXOF (CON ENVOLVENTE)
                renglon = ArrayUtils.indexOf(Vn, DescRegG.ArrReglas[NumRegla].InfSimbolo.Simbolo);
                
                ResultFirst = DescRegG.First(DescRegG.ArrReglas[NumRegla].ListaLadoDerecho);
                System.out.println("FIRST: "+ ResultFirst + "No terminal: "+Vn[renglon]);
                for (String s : ResultFirst) {
                    columna = ArrayUtils.indexOf(vt2, s);
                    if (columna >= 0) {
                        TablaLL1[renglon][columna] = NumRegla + 1;
                        System.out.println("Tabla: "+TablaLL1[renglon][columna]);
                        System.out.println("Renglon: "+Vn[renglon]+" : "+renglon);
                    }
                }
                //Si hay epsilon en el first, se calcula el follow del lado izquierdo
                if (ResultFirst.contains("epsilon")) {
                    ResultFollow = DescRegG.Follow(DescRegG.ArrReglas[NumRegla].InfSimbolo.Simbolo);
                    System.out.println("Follow: "+ResultFollow);
                    for (String s : ResultFollow) {
                        columna = ArrayUtils.indexOf(vt2, s);

                    }
                }

            }
            System.out.println("k= "+DescRegG.Vn.size());
            System.out.println("l= "+ DescRegG.Vt.size() );
            for (int k = 0; k <= DescRegG.Vn.size(); k++) {
                for (int l = 0; l <= DescRegG.Vt.size(); l++) {  
                    System.out.print(TablaLL1[k][l]+" ");
                }
                System.out.println("");
            }

        }

        public boolean AnalizarSintacLL1(String Cadena) {
            int Tok, TokTerm;
            int renglon, columna;
            Stack<Simb> Pila = new Stack<Simb>();
            Stack<Simb> Pila2 = new Stack<Simb>();
            Simb simbolo, elemPila;
            Sigma = Cadena;
            String CadenaAnaliz = Sigma;
            this.LexGram.SetSigma(Sigma);
            int Accion;
            Pila.clear();
            simbolo = new Simb(Vn[0], false, -1);
            Pila.push(simbolo); //Agrega el simbolo no terminal inicial
            Tok = this.LexGram.yylex(); //pide token al analizador lexico
            do {
                if (Pila.contains(0) && Tok == 0) {
                    //cadena sintacticamente correcta
                    return true;

                }
                elemPila = Pila.pop(); //hay elementos
                if (elemPila.esTerminal) {
                    if (elemPila.Token == Tok) {
                        //pide otro token, continua con el analisis
                        Tok = this.LexGram.yylex();
                        continue;
                    } else {
                        //Cadena sintacticamente incorrecta
                        return false;
                    }
                } else { //elemento de la pila es NO TERMINAL
                    renglon = ArrayUtils.indexOf(Vn, elemPila.simbolo); //Renglon de la tabla
                    columna = ArrayUtils.indexOf(Vt3, Tok);
                    Accion = TablaLL1[renglon][columna];
                    Pila2.clear();
                    if (Accion != -1) {
                        for (ClaseNodo n : DescRegG.ArrReglas[Accion - 1].ListaLadoDerecho) {
                            TokTerm = -1;
                            if (n.Simbolo.equals("epsilon")) {
                                continue;
                            }

                            if (n.Terminal) {
                                int ind = ArrayUtils.indexOf(vt2, n.Simbolo);
                                if (ind < 0) 
                                    return false;
                                //si lo encuentra, obtiene el terminal
//                                TokTerm = Vt3[ind];  //verificar***
                                TokTerm=ArrayUtils.indexOf(Vt3, ind);
                            }
                            simbolo = new Simb(n.Simbolo, n.Terminal, TokTerm);
                            Pila2.push(simbolo);

                        }
                        while (Pila2.size() > 0) {
                            Pila.push(Pila2.pop());
                        }

                    } else {
                        return false;
                    }

                }

            } while (true);

        }
    }

}