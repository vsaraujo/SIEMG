/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import Funcionalidades.TimeSIEMG;
import SplunkFile.SplunkFile;
import SplunkFile.SplunkFileXML;
import java.io.IOException;

/**
 *
 * @author Vítor
 */
public class SplunkAuteSimultanea {
    
    private Object Bean;
    private SplunkFile fileresult;
    
    public SplunkAuteSimultanea(TimeSIEMG time) throws IOException{
        
        String consulta = "sourcetype=WinEventLog:Security EventCode=4625 | rename ComputerName as Computador, user as Usuário, src_ip as IpOrigem | top Computador by Usuário, IpOrigem";
        
        // A classe SplunkFileXML retornará um arquivo XML.
        // Para que seja retornado um tipo diferente, basta utilizar a classe correspondente.
        //Exemplo: Para arquivos do tipo JSON usar a classe SplunkFileJSON
        
        if(fileresult==null)
            fileresult = new SplunkFileXML(consulta,time);
        
        fileresult.gerarArquivo();
        
        //A classe SplunkXML2Bean faz um parse de um arquivo XML.
        //Para fazer a transformação de um arquivo diferente, basta utilizar a classe correspondente.
        //Exemplo: Para arquivos do tipo JSON usar a classe SplunkJSON2Bean
        Bean = fileresult.getBean();
        
    }
    
    public void gerarNovoArquivo(){
        
        fileresult.gerarArquivo();
        Bean = fileresult.getBean();
    }
    
    public SplunkXML2Bean getBean() {
        return (SplunkXML2Bean) Bean;
    }
    
}
