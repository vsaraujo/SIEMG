/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import Funcionalidades.AuteSimultanea;
import Funcionalidades.Dados;
import Funcionalidades.TimeSIEMG;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class Monitor implements Runnable{
    
    private Dados aute;
    private TimeSIEMG time;
    private int delay;
    private int count;
    
    
    public Monitor(int janela) throws IOException{
        
        delay = janela;
        aute = new AuteSimultanea(time);
        count = 0;
    }
    
    

    @Override
    public void run() {
        
        while(aute.getQuantideResult()<6){
            
            aute.obterDados(time);
            
            System.out.println("Tentativa: "+count+"Resultados: "+aute.getQuantideResult());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            count++;
        }
        System.out.println("Resultados: "+aute.getQuantideResult());
  
    }
    
}
