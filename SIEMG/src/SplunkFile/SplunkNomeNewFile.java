/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplunkFile;

/**
 *
 * @author Vítor
 */
public class SplunkNomeNewFile {

    private static SplunkNomeNewFile Instancia;
    private static int num;

    public SplunkNomeNewFile() {

        num = -1;

    }

    public static synchronized int getNum() {
        num++;
        return num;
    }

    public static synchronized SplunkNomeNewFile getInstancia() {

        if (Instancia == null) {
            Instancia = new SplunkNomeNewFile();
             System.out.println("Criando Instancia SplunkNomeNewFile"+num);
     
            
        }

        

        return Instancia;
    }

}
