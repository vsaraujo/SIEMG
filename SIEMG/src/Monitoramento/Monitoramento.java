/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoramento;

import java.util.Map;
import Alertas.Alerta;

/**
 *
 * @author vitor.araujo
 */
public interface Monitoramento {

    public void anexarEvento(Alerta e);
    public void removerEvento(Alerta e);
    public void startEventos();
    public void startEvento(Integer idx);
    public void restartEvento(Integer idx);
    public void stopEvento(Integer idx);    
    public void verificarStatus();
    public void setStatus(Alerta e,MonitorStatus status);
    public MonitorStatus getStatus(Alerta e);
    public Integer getCount();
    public Map<Integer, Alerta> getListEvento();
    public void setListEvento(Map<Integer, Alerta> listEvento);
    public Map<Integer, Thread> getListThreads();
    public void setListThreads(Map<Integer, Thread> listThreads);  
    public Map<Integer, MonitorStatus> getListStatus();

}
