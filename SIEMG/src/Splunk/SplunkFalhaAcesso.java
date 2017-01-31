/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import Funcionalidades.TimeSIEMG;
import SplunkFile.SplunkFile;
import SplunkFile.SplunkFileXML;
import SplunkFile.SplunkNomeNewFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vítor
 */
public class SplunkFalhaAcesso {
    
    /**
     * Esta classe é responsável pela elaboração das consultas realizadas ao Splunk.
     * Mais especificamente, ela implemnta a consulta de Falha de Acesso.
     */

    private Map<Integer, List<String>> resultados;
    private SplunkFile fileresult;
    private static Boolean status = false;
    private final String consulta;
    private final TimeSIEMG time;

    public SplunkFalhaAcesso(TimeSIEMG time, String param) throws IOException {

        consulta = "sourcetype=WinEventLog:Security EventCode=4625 user!=\"*$\" | stats count by user,ComputerName" + param;

        this.time = time;
        // A classe SplunkFileXML retornará um arquivo XML.
        // Para que seja retornado um tipo diferente, basta utilizar a classe correspondente.
        //Exemplo: Para arquivos do tipo JSON usar a classe SplunkFileJSON

        if (status == false) {
            status = true;
        }

    }

    public void gerarNovoArquivo() throws IOException {

        if (fileresult == null) {

            String nameFile = "ResultadoFalha" + SplunkNomeNewFile.getNum();
            fileresult = new SplunkFileXML(nameFile, consulta, time);

        }

        fileresult.gerarArquivo();

        //A classe SplunkXML2Bean faz um parse de um arquivo XML.
        //Para fazer a transformação de um arquivo diferente, basta utilizar a classe correspondente.
        //Exemplo: Para arquivos do tipo JSON usar a classe SplunkJSON2Bean
        resultados = fileresult.getBean();
 
    }

    public Map<Integer, List<String>> getMap() {

        return resultados;
    }

    public int getSize() {

        //Diminui de 1 para remover o cabeçalho da tabela que é retornada com o resultado.
        return fileresult.getBean().size() - 1;
    }

}
