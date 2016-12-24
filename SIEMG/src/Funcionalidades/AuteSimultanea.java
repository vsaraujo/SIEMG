/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Splunk.SplunkAuteSimultanea;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class AuteSimultanea implements Dados{

    private SplunkAuteSimultanea autesimultanea;
    
    public AuteSimultanea (TimeSIEMG time) throws IOException{
        
        System.out.println("Inicio AuteSimultanea");
       
        obterDados(time);
        
    }
   
    @Override
    public Map<Integer,List<String>> obterDados(TimeSIEMG time) {
        
        try {
            if(autesimultanea == null){
                System.out.println("Criando SplunkAuteSimultanea");
       
                autesimultanea = new SplunkAuteSimultanea(time);
            }
        } catch (IOException ex) {
            Logger.getLogger(AuteSimultanea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        autesimultanea.gerarNovoArquivo();
        //autesimultanea.getBean().printConsole();
               
        return autesimultanea.getMap();
    }

    @Override
    public int getQuantideResult() {
        return autesimultanea.getSize();
    }
    
}
