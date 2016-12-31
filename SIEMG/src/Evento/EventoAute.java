/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.AuteSimultanea;
import Funcionalidades.TimeSIEMG;
import Monitoramento.Monitoramento;
import Parametros.Campos;
import Parametros.GrupoParametros;
import Parametros.Operadores;
import Parametros.Parametro;
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

    private TimeSIEMG time;
    private AuteSimultanea aute;
    private Map<Integer, List<String>> lista;
    private Monitoramento monitor;
    private int idEvento;
    private Boolean status;
    private GrupoParametros regras;

    public EventoAute(TimeSIEMG seg, int idthread) throws IOException {

        try {
            time = seg;
            System.out.println("Classe aute criada");
            idEvento = idthread;
            
            status = Boolean.TRUE;

            regras = new GrupoParametros();

            aute = new AuteSimultanea();

        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int getIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParametros(GrupoParametros param) {
        regras = param;
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
        int qntresultado = 0;

        setStatus(Boolean.TRUE);
        
        do {
            lista = aute.obterDados(time, regras);
            qntresultado = aute.getQuantideResult();

            System.out.println("============Time: " + time.getJanela() + " Quantidade de Resultado: " + qntresultado);

            if (qntresultado < 1) {
                try {
                    Thread.sleep(time.getExecucao() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventoAute.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } while (qntresultado < 1);

        System.out.println("## Fim Resultado da Thread: " + idEvento + " com o tempo de: " + time.getExecucao() + "s");
        for (Integer i : lista.keySet()) {

            System.out.println(lista.get(i));

        }

        System.out.println("####FIM### ID: " + idEvento);

        setStatus(Boolean.FALSE);

    }

}
