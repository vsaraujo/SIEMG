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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vítor
 */
public class SplunkAuteSimultanea {
    
    private Map<Integer,List<String>> resultados;
    private SplunkFile fileresult;
    private static int num = 0;
    private static Boolean status = false;
    private final String consulta;
    private TimeSIEMG time;
    
    public SplunkAuteSimultanea(TimeSIEMG time) throws IOException{
        
        consulta = "sourcetype=WinEventLog:Security EventCode=4625 | rename ComputerName as Computador, user as Usuário, src_ip as IpOrigem | top Computador by Usuário, IpOrigem";
        this.time = time;
        // A classe SplunkFileXML retornará um arquivo XML.
        // Para que seja retornado um tipo diferente, basta utilizar a classe correspondente.
        //Exemplo: Para arquivos do tipo JSON usar a classe SplunkFileJSON
        
        if(status == false){
            setNum();
            status = true;
        }
            
        //gerarNovoArquivo();
        
    }
    
    public static void setNum(){        
        num++;        
    }    
    
    public void gerarNovoArquivo() throws IOException{
        
        if(fileresult==null){
            
            String nameFile = "ResultadoAute"+num;
             System.out.println("Criando SplunkFileXML");
       
            fileresult = new SplunkFileXML(nameFile,consulta,time);

            
        }else
        
        
           System.out.println("Criando arquivo XML");
       
        fileresult.gerarArquivo();
        
        //A classe SplunkXML2Bean faz um parse de um arquivo XML.
        //Para fazer a transformação de um arquivo diferente, basta utilizar a classe correspondente.
        //Exemplo: Para arquivos do tipo JSON usar a classe SplunkJSON2Bean
        resultados = fileresult.getBean();
        System.out.println("Bean atualizado");
       
    }
    
    public Map<Integer,List<String>> getMap() {       
        
        return resultados;
    }
    
    public int getSize(){
        return fileresult.getBean().size() - 1;
    }
    
}
