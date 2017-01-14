/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alertas;

import Funcionalidades.AuteSimples;
import Funcionalidades.TimeSIEMG;
import Monitoramento.MonitorStatus;
import Monitoramento.Monitoramento;
import Parametros.GrupoParametros;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class AlertaAuteSimples implements Alerta, Runnable {

    private TimeSIEMG time;
    private AuteSimples aute;
    private Map<Integer, List<String>> lista;

    private Monitoramento monitor;    
    private AlertaTipo tipo;

    private MonitorStatus status;
    private GrupoParametros regras;
    private String title;
    private Integer indice;

    public AlertaAuteSimples(TimeSIEMG seg, int idEvento) throws IOException {

        try {
            
            setTipo(AlertaTipo.AUTENTICACAO_SIMPLES);
            time = seg;
            System.out.println("Classe aute criada");
            indice = idEvento;
            
            this.status = MonitorStatus.DESLIGADO;
            System.out.println("GetState = "+ this.getStatus());
            regras = new GrupoParametros();

            aute = new AuteSimples();

        } catch (NullPointerException e) {
            
            System.out.println(e.toString());
        }
    }

    @Override
    public MonitorStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(MonitorStatus status) {
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

        setStatus(MonitorStatus.LIGADO);        
        
        do {
            lista = aute.obterDados(time, regras);
            qntresultado = aute.getQuantideResult();

            System.out.println("============Time: " + time.getJanela() + " Quantidade de Resultado: " + qntresultado);

            if (qntresultado < 1) {
                try {
                    Thread.sleep(time.getExecucao() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AlertaAuteSimples.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } while (qntresultado < 1);

        System.out.println("## Fim Resultado da Thread: " + indice + " com o tempo de: " + time.getExecucao() + "s");
        for (Integer i : lista.keySet()) {

            System.out.println(lista.get(i));

        }

        System.out.println("####FIM### ID: " + indice);

        setStatus(MonitorStatus.DISPARADO);        

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

    @Override
    public void setTipo(AlertaTipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public AlertaTipo getTipo() {
        return tipo;
    }

    @Override
    public GrupoParametros getParametros() {
        return regras;        
    }

    @Override
    public TimeSIEMG getIntervalo() {
        return time;
    }
    
}
