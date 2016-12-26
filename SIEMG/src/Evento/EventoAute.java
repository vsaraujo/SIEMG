/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.AuteSimultanea;
import Funcionalidades.TimeSIEMG;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vítor
 */
public class EventoAute implements Evento {

    TimeSIEMG time;
    AuteSimultanea aute;
    Map<Integer, List<String>> lista;

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

        while (qntresultado < 6) {

            lista = aute.obterDados(time);
            qntresultado = aute.getQuantideResult();

            System.out.println("============Quantidade de Resultado: " + qntresultado);

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

}
