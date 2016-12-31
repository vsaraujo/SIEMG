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

    private int count;
    private static int indice;
    private static MonitorSIEMG instancia;

    Map<Integer, Evento> listEvento;
    Map<Integer, Thread> listThreads;

    public MonitorSIEMG() {

        count = 0;
        indice = 0;
        listEvento = new HashMap<>();
        listThreads = new HashMap<>();

    }

    @Override
    public void anexarEvento(Evento th) {

        try {

            listEvento.put(indice, th);
            listThreads.put(indice, new Thread((Runnable) th));

            indice++;
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
    public void startEvento(Evento th) {

        try {

            for (Integer id : listEvento.keySet()) {
                if (listEvento.get(id).equals(th)) {
                    listThreads.get(id).start();
                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }

    }

    @Override
    public void stopEvento(Evento th) {

        try {

            for (Integer id : listEvento.keySet()) {
                if (listEvento.get(id).equals(th)) {
                    listThreads.get(id).stop();
                }
            }

        } catch (NullPointerException evt) {
            System.out.println(evt.toString());
        }

    }

    public int getCount() {
        return count;
    }

    public static synchronized MonitorSIEMG getInstancia() {

        if (instancia == null) {
            instancia = new MonitorSIEMG();
        }
        return instancia;
    }

}
