/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Splunk.SplunkAuteSimultanea;
import Splunk.SplunkXML2Bean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class AuteSimultanea implements Dados{

    private SplunkAuteSimultanea autesimultanea;
    
    public AuteSimultanea (TimeSIEMG time) throws IOException{
        
        obterDados(time);
        
        
        
        
    }
   
    @Override
    public SplunkAuteSimultanea obterDados(TimeSIEMG time) {
        
        try {
            if(autesimultanea == null)
                autesimultanea = new SplunkAuteSimultanea(time);
        } catch (IOException ex) {
            Logger.getLogger(AuteSimultanea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        autesimultanea.gerarNovoArquivo();
        autesimultanea.getBean().printConsole();
               
        return autesimultanea;
    }

    @Override
    public int getQuantideResult() {
        return autesimultanea.getBean().getSizeResult();
    }
    
}
