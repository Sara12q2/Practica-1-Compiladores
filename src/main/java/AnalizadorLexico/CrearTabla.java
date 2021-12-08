package AnalizadorLexico;

import java.io.IOException;
import java.util.HashSet;

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
//
//        DescRegG.AnalizarGramatica();   
//        Vt = new SimbTerrem[DescRegG.Vt.Count + 1];
//        Vt2 = new String[DescRegG.Vt.Count + 1];
//        Vn = new String[DescRegG.Vn.Count + 1];
//        j=0;
//        for(String s : DescRegG.Vt){
//            Vt[j] = new SimbTerm(s, -1);
//            Vt2[j++] = s;
//        }
//
//        Vt[j] = new SimbTerm("$",-1);
//        vt2[j++] = "$";
//        j=0;
        
    }

       

   }
    


}


























