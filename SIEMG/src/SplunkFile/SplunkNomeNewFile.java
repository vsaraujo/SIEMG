/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplunkFile;

/**
 *
 * @author Vítor
 * 
 * Classe responsável pela geração de nome de arquivo, de forma a garantir
 * a utilização de nomes únicos, gerados através do resultado retornado pelos
 * Alertas (Threads) do sistema.
 *
 */

public class SplunkNomeNewFile {

    private static SplunkNomeNewFile Instancia;
    private static int num;

    private SplunkNomeNewFile() {
        num = -1;
    }

    public static synchronized int getNum() {
        num++;
        return num;
    }

    public static synchronized SplunkNomeNewFile getInstancia() {

        if (Instancia == null) {
            Instancia = new SplunkNomeNewFile();
        }
        
        return Instancia;
    }

}
