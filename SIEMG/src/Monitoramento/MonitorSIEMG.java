/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoramento;

import Funcionalidades.AuteSimples;
import Funcionalidades.Dados;
import Funcionalidades.TimeSIEMG;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Alertas.Alerta;

/**
 *
 * @author Vítor
 */
public class MonitorSIEMG implements Monitoramento {

    private Integer count;
    private static Monitoramento instancia;

    Map<Integer, Alerta> listAlertas;
    Map<Integer, Thread> listThreads;
    Map<Integer, MonitorStatus> listStatus;

    public MonitorSIEMG() {

        count = 0;
        listAlertas = new HashMap<>();
        listThreads = new HashMap<>();
        listStatus = new HashMap<>();

    }

    @Override
    public void anexarEvento(Alerta th) {

        try {

            listAlertas.put(th.getIndice(), th);
            listThreads.put(th.getIndice(), new Thread((Runnable) th));
            listStatus.put(th.getIndice(), th.getStatus());

            th.setMonitor(this);

            count++;

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    @Override
    public void removerEvento(Alerta e) {

        for (Integer id : listAlertas.keySet()) {

            if (listAlertas.get(id).equals(e)) {
                listAlertas.remove(id);
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

            for (Integer id : listAlertas.keySet()) {
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

            for (Integer id : listAlertas.keySet()) {
                if (id.equals(idx)) {

                    listThreads.get(id).start();

                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }

    }

    @Override
    public void restartEvento(Integer idx) {

        try {

            for (Integer id : listAlertas.keySet()) {
                if (id.equals(idx)) {

                    stopEvento(id);
                    listThreads.remove(id);
                    System.out.println("Thread removida");
                    
                    Alerta tmp = listAlertas.get(id);
                    tmp.setIndice(id);
                    
                    System.out.println("Alerta criado");
                    
                    
                    anexarEvento(tmp);
                    System.out.println("Alerta anexado");
                    
                    startEvento(id);
                    System.out.println("Alerta startado");

                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }

    }

    @Override
    public void stopEvento(Integer idx) {

        try {

            for (Integer id : listAlertas.keySet()) {
                if (id.equals(idx)) {
                    listThreads.get(id).interrupt();
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
    public Map<Integer, Alerta> getListEvento() {
        return listAlertas;
    }

    @Override
    public Map<Integer, MonitorStatus> getListStatus() {
        return listStatus;
    }

    @Override
    public void setListEvento(Map<Integer, Alerta> listEvento) {
        this.listAlertas = listEvento;
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
    public void setStatus(Alerta e, MonitorStatus status) {

        try {

            for (Integer id : listAlertas.keySet()) {
                if (id.equals(e.getIndice())) {
                    listStatus.put(id, status);
                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    @Override
    public MonitorStatus getStatus(Alerta e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
