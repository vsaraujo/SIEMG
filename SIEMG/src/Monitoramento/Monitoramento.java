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

    Map<Integer, Alerta> listAlertas;
    Map<Integer, Thread> listThreads;
    Map<Integer, MonitorStatus> listStatus;

    public Monitoramento() {

        count = 0;
        listAlertas = new HashMap<>();
        listThreads = new HashMap<>();
        listStatus = new HashMap<>();

    }

    public void anexarEvento(Alerta th) {

        try {
            
            System.out.println("Iniciando put no listAlertas");

            listAlertas.put(th.getIndice(), th);
            
            System.out.println("Fim put no listAlertas");
            System.out.println("Iniciando put no listThreads");
            listThreads.put(th.getIndice(), new Thread((Runnable) th));
            System.out.println("fim put no listThreads");
            System.out.println("Iniciando put no listStatus");
            listStatus.put(th.getIndice(), th.getStatus());
            System.out.println("Fim put no listStatus");

            th.setMonitor(this);

            count++;

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    public void removerEvento(Alerta e) {

        for (Integer id : listAlertas.keySet()) {

            if (listAlertas.get(id).equals(e)) {
                
                
                stopEvento(id);
                        
                listAlertas.remove(id);
                listThreads.get(id).stop();
                listThreads.remove(id);
                listStatus.remove(id);
                count--;
                break;
            }

        }

    }

    public void verificarStatus() {

        try {

            for (Integer id : listAlertas.keySet()) {
                //System.out.println("ID:"+e.toString()+"Quantidade de resultado: "+e.getStatus());
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

    public void startEventos() {
        try {

            for (Integer id : listThreads.keySet()) {
                listThreads.get(id).start();
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }
    }

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

    public void stopEvento(Integer idx) {

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

    public Map<Integer, Alerta> getListEvento() {
        return listAlertas;
    }

    public Map<Integer, MonitorStatus> getListStatus() {
        return listStatus;
    }

    public void setListEvento(Map<Integer, Alerta> listEvento) {
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

    public MonitorStatus getStatus(Alerta e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
