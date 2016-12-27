/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.AuteSimultanea;
import Funcionalidades.TimeSIEMG;
import Monitoramento.Monitoramento;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class EventoAute implements Evento, Runnable {

    TimeSIEMG time;
    AuteSimultanea aute;
    Map<Integer, List<String>> lista;
    Monitoramento monitor;

    public EventoAute(TimeSIEMG seg) throws IOException {

        try {
            time = seg;
            System.out.println("Classe aute criada");

            aute = new AuteSimultanea(time);

        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public int getStatus() {

        int qntresultado = 0;

        while (qntresultado < 1) {

            lista = aute.obterDados(time);
            qntresultado = aute.getQuantideResult();

            System.out.println("============Time: "+time.getJanela()+"Quantidade de Resultado: " + qntresultado);
            
            try {
                Thread.sleep(time.getExecucao()*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EventoAute.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        for (Integer i : lista.keySet()){
            
            System.out.println(lista.get(i));
            
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

    @Override
    public void run() {
        
        getStatus();
        
    }

}
