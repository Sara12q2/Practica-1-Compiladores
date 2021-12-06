
package AnalizadorLexico;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


    class TransEdoAFD{
        public int IdEdo;
        public int TransAFD;
    }

public class AFD {
    public static HashSet<AFD>ConjAFDs=new HashSet<AFD>();
    public int NumEstados;
    public int CardAlfabeto;
    public String[]ArrAlfabeto;
    public int [][]TransicionesAFD;
    public static int [][]TablaAFD;
    //IDENTIFICADOR DEL AFD
    public int IdAFD;
    public AFD(){
        IdAFD=-1;
    }
    
    public AFD(int NumeroDeEstados, int IdAutFD){
        TablaAFD=new int[NumeroDeEstados][257];
        for(int i=0; i<NumeroDeEstados;i++)
            for(int j =0; j<257; j++)
                TablaAFD[i][j]=-1;
        NumEstados=NumeroDeEstados;
        IdAFD=IdAutFD;
        AFD.ConjAFDs.add(this);
        
    }
        
    public void GuardarAFDenArchivo(String NombArchivo) throws FileNotFoundException, IOException{
        System.out.println("TABLA ARCHIVO: ");
        for(int i=0;i<257;i++){
            System.out.print(AFD.TablaAFD[0][i]); 
        }
                PrintWriter printWriter = new PrintWriter(NombArchivo);
            try {
                printWriter = new PrintWriter(NombArchivo);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to locate the fileName: " + e.getMessage());
            }
            System.out.println("Numero de estados: "+NumEstados);
            Objects.requireNonNull(printWriter).print(NumEstados);
            Objects.requireNonNull(printWriter).print("\n");
            for(int i=0;i<NumEstados;i++){
                for(int j = 0; j<257;j++){
                    Objects.requireNonNull(printWriter).print(AFD.TablaAFD[i][j]);
                    if(j!=256)
                        Objects.requireNonNull(printWriter).print(";");
                }
                Objects.requireNonNull(printWriter).print("\n");
            }
            printWriter.close();
        
        
//        try(OutputStream os = new FileOutputStream(NombArchivo, false)){
//        Writer writer = new OutputStreamWriter(os,"UTF-8");
//        //GUARDANDO EL NUMERO DE RENGLONES
//        writer.write(String.valueOf(this.NumEstados));
//        //GUARDANDO LOS RENGLONES
//        for(int i=0;i<NumEstados;i++){
//            for(int j = 0; j<257;j++){
//                writer.write(AFD.TablaAFD[i][j]);
//                if(j!=256)
//                    writer.write(";");
//            }
//        }
//        writer.close();
//      }
    }
    
    public void LeerAFDdeArchivo(String NombArchivo, int IdentifAFD) throws IOException{
        int IdEdo;
        int k;
        String Renglon;
        String[] ValoresRenglon = new String[257];
    
        String cadena;
          FileReader f = new FileReader(NombArchivo);
          BufferedReader b = new BufferedReader(f);
          IdEdo = 0;
          Renglon = b.readLine();
          this.NumEstados = Integer.parseInt(Renglon);
          this.TablaAFD = new int[this.NumEstados][257];
          
          
          while((cadena = b.readLine())!=null) {
           Renglon = cadena;
           ValoresRenglon = Renglon.split(";");
            for(k = 0; k < 257; k++){
                this.TablaAFD[IdEdo][k] = Integer.parseInt(ValoresRenglon[k]);
            }
            IdEdo++;
          }
          b.close();
        this.NumEstados = IdEdo;
        this.IdAFD = IdentifAFD;
        AFD.ConjAFDs.add(this);
        return ;
    }

    public void setIdAFD(int IdAFD) {
        this.IdAFD = IdAFD;
    }
    
    public static void main(String[] args) throws IOException {
        AFD uno = new AFD();
        uno.LeerAFDdeArchivo("C:\\Users\\ivett\\Desktop\\Septimo semestre\\Compiladores\\Pruebas AFD\\Prueba.txt", 1);
        
        System.out.println("TAMANO: "+ uno.TablaAFD[0].length);
    }
    
  
        
     
}  
   