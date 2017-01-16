/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitoramento;

import java.util.HashMap;
import java.util.Map;
import Alertas.Alerta;

/**
 *
 * @author Vítor
 */
public class Monitoramento {

    private Integer count;
    private static Monitoramento instancia;
    private Map<Integer, Alerta> listAlertas;
    private Map<Integer, Thread> listThreads;
    private final Map<Integer, MonitorStatus> listStatus;

    private Monitoramento() {

        count = 0;
        listAlertas = new HashMap<>();
        listThreads = new HashMap<>();
        listStatus = new HashMap<>();

    }

    public void anexarAlerta(Alerta th) {

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

    public void removerAlerta(Alerta e) {

        for (Integer id : listAlertas.keySet()) {

            if (listAlertas.get(id).equals(e)) {
                
                pararAlerta(id);
                        
                listAlertas.remove(id);
                listThreads.get(id).stop();
                listThreads.remove(id);
                listStatus.remove(id);
                count--;
                break;
            }

        }

    }

    public void inicializarAlertas() {
        try {

            for (Integer id : listThreads.keySet()) {
                listThreads.get(id).start();
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    public void inicializarAlerta(Integer idx) {

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

    public void reinicializarAlerta(Integer idx) {

        try {
            for (Integer id : listAlertas.keySet()) {
                if (id.equals(idx)) {
                    pararAlerta(id);
                    listThreads.remove(id);                    
                    Alerta tmp = listAlertas.get(id);
                    tmp.setIndice(id);                    
                    anexarAlerta(tmp);                     
                    inicializarAlerta(id);
                 }
            }
        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    public void pararAlerta(Integer idx) {

        try {
            for (Integer id : listThreads.keySet()) {
                if (id.equals(idx)) {
                    listThreads.get(id).interrupt();
                }
            }
        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    public Integer getCount() {
        return count;
    }

    public static synchronized Monitoramento getInstancia() {

        if (instancia == null) {
            instancia = new Monitoramento();
        }
        return instancia;
    }

    public Map<Integer, Alerta> getListAlertas() {
        return listAlertas;
    }

    public Map<Integer, MonitorStatus> getListStatus() {
        return listStatus;
    }

    public void setListAlertas(Map<Integer, Alerta> listEvento) {
        this.listAlertas = listEvento;
    }

    public Map<Integer, Thread> getListThreads() {
        return listThreads;
    }

    public void setListThreads(Map<Integer, Thread> listThreads) {
        this.listThreads = listThreads;
    }

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
}
