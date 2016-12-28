/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Evento.Evento;
import Evento.EventoAute;
import Funcionalidades.AuteSimultanea;
import Funcionalidades.TimeSIEMG;
import Login.Credenciais;
import Login.TelaLogin;
import Monitoramento.MonitorSIEMG;
import Monitoramento.Monitoramento;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class TesteMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        TimeSIEMG time = new TimeSIEMG(30);
        TimeSIEMG time2 = new TimeSIEMG(10);
        TimeSIEMG time3 = new TimeSIEMG(60);

        int idt1 = 1;
        int idt2 = 2;
        int idt3 = 3;

        System.out.println("##Inicio da criação do 1 - Evento1##");
        EventoAute evt1 = new EventoAute(time, idt1);
        Thread t1 = new Thread(evt1, "New Thread 1");
        t1.start();
        System.out.println("##FIM da criação do 1 - Evento1##");

        System.out.println("##Inicio da criação do 2 - Evento2##");
        EventoAute evt2 = new EventoAute(time2, idt2);
        Thread t2 = new Thread(evt2, "New Thread 2");
        t2.start();
        System.out.println("##FIM da criação do 2 - Evento2");

        System.out.println("##Inicio da criação do 3 - Evento3##");
        EventoAute evt3 = new EventoAute(time3, idt3);
        Thread t3 = new Thread(evt3, "New Thread 3");
        t3.start();
        System.out.println("##FIM da criação do 3 - Evento3");

        int fim = 0;
        
        while (evt1.getAtivo() || evt2.getAtivo() || evt3.getAtivo()){
            
            fim++;
                    
            System.out.println("####"+fim+"º Tentativa de finalizar");
            System.out.println("#### Status evt1 = "+evt1.getAtivo());
            System.out.println("#### Status evt2 = "+evt2.getAtivo());
            System.out.println("#### Status evt3 = "+evt3.getAtivo());
                        
             try {
                Thread.sleep(10*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EventoAute.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        exit(0);
    }
}
