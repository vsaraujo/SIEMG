/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.AuteSimultanea;
import Funcionalidades.TimeSIEMG;
import java.io.IOException;

/**
 *
 * @author Vítor
 */
public class EventoAute implements Evento{
    
    TimeSIEMG time;
    AuteSimultanea aute;
    
    public EventoAute(int seg) throws IOException{
        
        try {
            time = new TimeSIEMG(seg);
            System.out.println("Classe aute criada");
            
            aute = new AuteSimultanea(time);
            
        
        }
        catch (NullPointerException e){
           System.out.println(e.toString());
        }
    }
        
    @Override
    public int getStatus() {
        
        int qntresultado = 0;
        
        while(qntresultado < 5){
    
         aute.obterDados(time);
         qntresultado = aute.getQuantideResult();
         
        }
        return qntresultado;
        
    }

    @Override
    public int getIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParametros(Parametros param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setJanela(TimeSIEMG janela) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAgendamento(TimeSIEMG agendamento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
