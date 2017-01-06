/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoramento;

import Evento.Evento;
import java.util.Map;

/**
 *
 * @author vitor.araujo
 */
public interface Monitoramento {

    public void anexarEvento(Evento e);
    public void removerEvento(Evento e);
    public void startEventos();
    public void startEvento(Integer idx);
    public void stopEvento(Integer idx);    
    public void verificarStatus();
    public void setStatus(Evento e,Thread.State status);
    public Thread.State getStatus(Evento e);
    public Integer getCount();
    public Map<Integer, Evento> getListEvento();
    public void setListEvento(Map<Integer, Evento> listEvento);
    public Map<Integer, Thread> getListThreads();
    public void setListThreads(Map<Integer, Thread> listThreads);    

}
