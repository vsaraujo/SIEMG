/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alertas;

import Funcionalidades.AuteSimples;
import Funcionalidades.FalhadeAcesso;
import Funcionalidades.Funcionalidades;
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
public class Alerta implements Runnable {

    private TimeSIEMG time;
    private Funcionalidades funcionalidade;
    private Map<Integer, List<String>> lista;
    private Monitoramento monitor;
    private AlertaTipo tipo;
    private MonitorStatus status;
    private GrupoParametros regras;
    private String title;
    private Integer indice;

    public Alerta(TimeSIEMG seg, int idEvento, AlertaTipo alerta) throws IOException {

        try {
            
            time = seg;
            indice = idEvento;
            this.status = MonitorStatus.DESLIGADO;
            regras = new GrupoParametros();
            
            switch (alerta){
                
                case AUTENTICACAO_SIMPLES:
                    setTipo(AlertaTipo.AUTENTICACAO_SIMPLES);
                    funcionalidade = new AuteSimples();                    
                    break;
                    
                case FALHA_AUTENTICACAO:
                    setTipo(AlertaTipo.FALHA_AUTENTICACAO);
                    funcionalidade = new FalhadeAcesso();
                    break;
            }

        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
    }

    public MonitorStatus getStatus() {
        return status;
    }

    public void setStatus(MonitorStatus status) {
        this.status = status;
        monitor.setStatus(this, status);
    }

    public void setParametros(GrupoParametros param) {
        regras = param;
    }

    @Override
    public void run() {
        int qntresultado = 0;

        setStatus(MonitorStatus.LIGADO);

        do {
            lista = funcionalidade.obterDados(time, regras);            
            qntresultado = funcionalidade.getQuantideResult();

            if (qntresultado < 1) {
                try {
                    Thread.sleep(time.getExecucao() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Alerta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } while (qntresultado < 1);

        setStatus(MonitorStatus.DISPARADO);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setIndice(Integer idx) {
        indice = idx;
    }

    public Integer getIndice() {
        return indice;
    }

    public Map<Integer, List<String>> getListaResultados() {
        return lista;
    }

    public void setMonitor(Monitoramento monitor) {
        this.monitor = monitor;
    }

    public void setTipo(AlertaTipo tipo) {
        this.tipo = tipo;
    }

    public AlertaTipo getTipo() {
        return tipo;
    }

    public GrupoParametros getParametros() {
        return regras;
    }

    public TimeSIEMG getIntervalo() {
        return time;
    }

}
