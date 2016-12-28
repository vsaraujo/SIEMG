/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoramento;

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
    private int count;
    List<Evento> listEvent;

    public MonitorSIEMG(){
        
        count = 0;
        listEvent = new ArrayList<Evento>();

    }

   
    @Override
    public void anexarEvento(Evento e) {
    
        try {
           
            listEvent.add(e);       
            count++;
        
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
                //System.out.println("ID:"+e.toString()+"Quantidade de resultado: "+e.getStatus());
            }
            
        }
        catch (NullPointerException evt){
           System.out.println(evt.toString());
        }
    }

}
