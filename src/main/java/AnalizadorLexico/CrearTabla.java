package AnalizadorLexico;
import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;
import java.util.HashSet;
import java.util.stream.IntStream;
public class CrearTabla {
    class SimbTerm{
     public String Simbolo;
     public int ValToken;
     public SimbTerm(String Simb, int Tok){
         Simbolo = Simb;
         ValToken = Tok;
     }
     
     public SimbTerm(){
         Simbolo = "";
         ValToken = -1;
     }
        
   }
    
   class AnalizadorLL1{
       DescRegGram_Gram DescRegG;
       AnalizadorLexico LexGram;
       public String Gram;
       public String Sigma;
       public int[][] TablaLL1;
       public SimbTerm[] Vt;
       public String[] Vn;
       public String[] vt2;
//       const String ArchAFDLexiGramGram = "RUTA";
       String ArchAFDLexiGramGram = "RUTA";
       

   

       public AnalizadorLL1(String CadGramatica, String ArchAFDLexic) throws IOException{
           Gram = CadGramatica;
           DescRegG = new DescRegGram_Gram(CadGramatica, ArchAFDLexiGramGram, 0);
           LexGram = new AnalizadorLexico(ArchAFDLexic,1);
       }
       
       public AnalizadorLL1(String CadGramatica) throws IOException{
           Gram = CadGramatica;
           DescRegG = new DescRegGram_Gram(CadGramatica,ArchAFDLexiGramGram,0);
       }
       public void SetLexico(String ArchAFDLexic) throws IOException{
           LexGram = new AnalizadorLexico(ArchAFDLexic, 1);
       }
          
    public void CrearTablaLL1(){
        int j;
        HashSet<String> ResultFirst = new HashSet<String>();
        HashSet<String> ResultFollow = new HashSet<String>();

        DescRegG.AnalizarGramatica();   
        Vt = new SimbTerm[DescRegG.Vt.size() + 1]; //Numero de columnas de la tabla LL1
        vt2 = new String[DescRegG.Vt.size() + 1]; //Arreglo de terminales
        Vn = new String[DescRegG.Vn.size() + 1]; //Filas Tabla
        j=0;
        //Llenando de terminales
        for(String s : DescRegG.Vt){
            Vt[j] = new SimbTerm(s, -1); //Aún no hay token
            vt2[j++] = s;
        }

        Vt[j] = new SimbTerm("$",-1);
        vt2[j++] = "$";
        j=0;
        for(String s : DescRegG.Vn){
            Vn[j++] = "$";
        }
        Vn[j++] = "$";
        
        TablaLL1 = new int[DescRegG.Vn.size()+1][DescRegG.Vt.size() + 1];
        for(int k=0; k <= DescRegG.Vn.size();k++)
            for(int l = 0; l <= DescRegG.Vn.size();l++)
                TablaLL1[k][1] = -1;
        int renglon;
        int columna;
        //Se analiza cada una de las reglas e iremos colocando las acciones en el renglon correspondiente a la regla
        for(int NumRegla = 0; NumRegla < DescRegG.NumReglas; NumRegla++){
            ResultFirst.clear();
            ResultFollow.clear();
            //EQUIVALENCIA PARA INDEXOF (CON ENVOLVENTE)
            renglon = ArrayUtils.indexOf(Vn, DescRegG.ArrReglas[NumRegla].InfSimbolo.Simbolo);
            
            ResultFirst = DescRegG.First(DescRegG.ArrReglas[NumRegla].ListaLadoDerecho);
            for(String s : ResultFirst){
                columna = ArrayUtils.indexOf(vt2,s);
                if(columna >= 0)
                    TablaLL1[renglon][columna] = NumRegla+1;
            }
            //Si hay epsilon en el first, se calcula el follow del lado izquierdo
            if(ResultFirst.contains("epilon")){
                ResultFollow = DescRegG.Follow(DescRegG.ArrReglas[NumRegla].InfSimbolo.Simbolo);
                for(String s : ResultFollow){
                    columna = ArrayUtils.indexOf(vt2,s);
                    TablaLL1[renglon][columna] = NumRegla+1;
                 
                }
            }
            
        }
                
        
    }

       

   }
    


}





















