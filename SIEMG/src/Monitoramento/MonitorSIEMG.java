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

    
    private Integer count;    
    private static Monitoramento instancia;

    Map<Integer, Evento> listEvento;
    Map<Integer, Thread> listThreads;
    Map<Integer, Thread.State> listStatus;

    public MonitorSIEMG() {

        count = 0;        
        listEvento = new HashMap<>();
        listThreads = new HashMap<>();
        listStatus = new HashMap<>();

    }

    @Override
    public void anexarEvento(Evento th) {

        try {

            listEvento.put(th.getIndice(), th);
            listThreads.put(th.getIndice(), new Thread((Runnable) th));
            listStatus.put(th.getIndice(), th.getStatus());
            
            th.setMonitor(this);
            
            count++;

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    @Override
    public void removerEvento(Evento e) {

        for (Integer id : listEvento.keySet()) {

            if (listEvento.get(id).equals(e)) {
                listEvento.remove(id);
                listThreads.get(id).stop();
                listThreads.remove(id);
                count--;
                break;
            }

        }

    }

    @Override
    public void verificarStatus() {

        try {

            for (Integer id : listEvento.keySet()) {
                //System.out.println("ID:"+e.toString()+"Quantidade de resultado: "+e.getStatus());
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    @Override
    public void startEventos() {
        try {

            for (Integer id : listThreads.keySet()) {
                listThreads.get(id).start();
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    @Override
    public void startEvento(Integer idx) {

        try {

            for (Integer id : listEvento.keySet()) {
                if (id.equals(idx)) {
                    listThreads.get(id).start();
                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }

    }

    @Override
    public void stopEvento(Integer idx) {

        try {

            for (Integer id : listEvento.keySet()) {
                if (id.equals(idx)) {
                    listThreads.get(id).stop();
                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }

    }

    
    @Override
    public Integer getCount() {
        return count;
    }

    public static synchronized Monitoramento getInstancia() {

        if (instancia == null) {
            instancia = new MonitorSIEMG();
        }
        return instancia;
    }
    
    @Override
    public Map<Integer, Evento> getListEvento() {
        return listEvento;
    }

    @Override
    public void setListEvento(Map<Integer, Evento> listEvento) {
        this.listEvento = listEvento;
    }

    @Override
    public Map<Integer, Thread> getListThreads() {
        return listThreads;
    }

    @Override
    public void setListThreads(Map<Integer, Thread> listThreads) {
        this.listThreads = listThreads;
    }

    @Override
    public void setStatus(Evento e,Thread.State status) {
        
        try {

            for (Integer id : listEvento.keySet()) {
                if (id.equals(e.getIndice())) {
                    listStatus.put(id, status);
                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    @Override
    public Thread.State getStatus(Evento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
