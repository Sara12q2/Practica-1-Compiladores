
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
import java.io.Writer;
import java.util.HashSet;
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
    public int [][]TablaAFD;
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
        try(OutputStream os = new FileOutputStream(NombArchivo, false)){
        Writer writer = new OutputStreamWriter(os,"UTF-8");
        //GUARDANDO EL NUMERO DE RENGLONES
        writer.write(String.valueOf(this.NumEstados));
        //GUARDANDO LOS RENGLONES
        for(int i=0;i<NumEstados;i++){
            for(int j = 0; j<257;j++){
                writer.write(TablaAFD[i][j]);
                if(j!=256)
                    writer.write(";");
            }
            writer.close();
        }
      }
    }
    
    public void LeerAFDdeArchivo(String NombArchivo, int IdentifAFD) throws IOException{
        int IdEdo;
        int k;
        String Renglon;
        String[] ValoresRenglon = new String[257];
        
        try(BufferedReader reader = new BufferedReader(new FileReader(NombArchivo))){
           IdEdo = 0;
           Renglon = reader.readLine();
           this.NumEstados = Integer.parseInt(Renglon);
           this.TablaAFD = new int[this.NumEstados][257];
           while(!reader.ready()){
           Renglon = reader.readLine();
           ValoresRenglon = Renglon.split(";");
            for(k = 0; k < 257; k++){
                this.TablaAFD[IdEdo][k] = Integer.parseInt(ValoresRenglon[k]);
            }
            IdEdo++;
           }
        }
        this.NumEstados = IdEdo;
        this.IdAFD = IdentifAFD;
        AFD.ConjAFDs.add(this);
        return ;
    }

    public void setIdAFD(int IdAFD) {
        this.IdAFD = IdAFD;
    }
    
    
  
        
     
}  
   