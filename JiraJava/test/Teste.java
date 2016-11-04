
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vitor.araujo
 */
public class Teste implements Runnable{

    private String palavra;
    private int delay;
    private static FileReader file;
    private BufferedReader lerArq;
    
    public Teste(String texto, int tmp) throws FileNotFoundException{
        palavra = texto;
        delay = tmp;                
    }
        
    @Override
    public void run() {
        try {
           System.out.println(palavra + " ");
           for(;;) {
               file = new FileReader("C:\\Users\\vitor.araujo\\Documents\\NetBeansProjects\\JiraJava\\test\\teste.txt");
               lerArq = new BufferedReader(file);
               
               String linha = lerArq.readLine();
               
               if(linha.equals("10")){
                   System.out.println("Lendo arquivo...");
                   System.out.printf("Valor do arquivo: %s\n", linha);
                   break;
               }
               file.close();
               Thread.sleep(delay);
           }
        }catch (Exception e){
            System.err.println(e.toString());
            return;
        }        
    }
    public static void main (String[] args) throws FileNotFoundException{
        Runnable alerta = new Teste("Inicio Alerta!",5000);
        new Thread(alerta).start();        
    }   
}
