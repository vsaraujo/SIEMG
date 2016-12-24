/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import Evento.Evento;
import Funcionalidades.AuteSimultanea;
import Funcionalidades.Dados;
import Funcionalidades.TimeSIEMG;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class MonitorSIEMG implements Monitoramento {

    private Dados aute;
    private TimeSIEMG time;
    private int delay;
    private int count;
    List<Evento> listEvent;

    public MonitorSIEMG(int janela) throws IOException {

        delay = janela;
        aute = new AuteSimultanea(time);
        count = 0;
        listEvent = new ArrayList<Evento>();

    }

    public MonitorSIEMG(Evento ev) {
    
        anexarEvento(ev);
        
    }

    public void run() {

        Map<Integer, List<String>> test = new HashMap<Integer, List<String>>();

        while (aute.getQuantideResult() < 6) {

            test = (Map<Integer, List<String>>) aute.obterDados(time);

            for (Map.Entry entry : test.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }

            System.out.println("Tentativa: " + count + "Resultados: " + aute.getQuantideResult());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorSIEMG.class.getName()).log(Level.SEVERE, null, ex);
            }
            count++;
        }
        System.out.println("Resultados: " + aute.getQuantideResult());

    }

    @Override
    public void anexarEvento(Evento e) {
    
        try {
           
            listEvent.add(e);        
        
        }
        catch (NullPointerException evt){
           System.out.println(evt.toString());
        }
    }

    @Override
    public void removerEvento(Evento e) {
    
        listEvent.remove(e);
    
    }

    @Override
    public void verificarStatus() {
    
        try {
        
            for(Evento e: listEvent){
                System.out.println("ID:"+e.toString()+"Quantidade de resultado: "+e.getStatus());
            }
            
        }
        catch (NullPointerException evt){
           System.out.println(evt.toString());
        }
    }

}
