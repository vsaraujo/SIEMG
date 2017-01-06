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

    private Thread.State status;
    private GrupoParametros regras;
    private String title;
    private Integer indice;

    public EventoAute(TimeSIEMG seg, int idEvento) throws IOException {

        try {
            time = seg;
            System.out.println("Classe aute criada");
            indice = idEvento;
            
            this.status = Thread.State.NEW;
            System.out.println("GetState = "+ this.getStatus());
            regras = new GrupoParametros();

            aute = new AuteSimultanea();

        } catch (NullPointerException e) {
            
            System.out.println(e.toString());
        }
    }

    @Override
    public Thread.State getStatus() {
        return status;
    }

    @Override
    public void setStatus(Thread.State status) {
        this.status = status;
        monitor.setStatus(this, status);
    }

    @Override
    public void setParametros(GrupoParametros param) {
        regras = param;
    }

    @Override
    public void run() {
        int qntresultado = 0;

        setStatus(Thread.State.RUNNABLE);        
        
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

        System.out.println("## Fim Resultado da Thread: " + indice + " com o tempo de: " + time.getExecucao() + "s");
        for (Integer i : lista.keySet()) {

            System.out.println(lista.get(i));

        }

        System.out.println("####FIM### ID: " + indice);

        setStatus(Thread.State.TERMINATED);        

    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setIndice(Integer idx) {
        indice = idx;
    }

    @Override
    public Integer getIndice() {
        return indice;
    }
    
    @Override
    public Map<Integer, List<String>> getListaResultados() {
        return lista;
    }
    @Override
    public void setMonitor(Monitoramento monitor) {
        this.monitor = monitor;
    }
    
}
