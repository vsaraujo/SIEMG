/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Splunk.SplunkAuteSimultanea;
import java.io.IOException;

/**
 *
 * @author Vítor
 */
public class AuteSimultanea implements Dados{

    private SplunkAuteSimultanea autesimultanea;
    
    public AuteSimultanea (TimeSIEMG time) throws IOException{
        
        autesimultanea = new SplunkAuteSimultanea(time);
        autesimultanea.getBean();
        
        
    }
    
    @Override
    public Object obterDados(TimeSIEMG time) {
        
        
        return autesimultanea;
    }
    
}
