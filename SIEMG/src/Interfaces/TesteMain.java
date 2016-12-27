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
import Login.NovoJFrame;
import Login.TelaLogin;
import Monitoramento.MonitorSIEMG;
import Monitoramento.Monitoramento;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Vítor
 */

public class TesteMain {
    public static void main(String[] args) throws IOException, InterruptedException{
        
       TelaLogin teste = null;
       teste.getInstancia();
       
       System.out.println("Inicio da TelaLogin");
       Thread.sleep(20*1000);
        
       TimeSIEMG time = new TimeSIEMG(30);
       TimeSIEMG time2 = new TimeSIEMG(10);
       
       System.out.println("##Inicio da criação do 1 - Evento1##");
       EventoAute evt1 = new EventoAute(time);
       
       Thread t1 = new Thread(evt1, "New Thread 1");
       t1.start();
       //evt1.getStatus();
       System.out.println("##FIM da criação do 1 - Evento1##");
       
              
       System.out.println("##Inicio da criação do 2 - Evento2##");
       EventoAute evt2 = new EventoAute(time2);
       Thread t2 = new Thread(evt2, "New Thread 2");       
       t2.start();
       //evt2.getStatus();       
       System.out.println("##FIM da criação do 2 - Evento2");
       
       //Map<Integer,List<String>> map;
        //map = aute.obterDados(time);
        
        
       
       //System.out.println(aute);
       
       
       //java.awt.EventQueue.invokeLater(new Runnable() {
         //   public void run() {
           //new NovoJFrame().setVisible(true);
           //}
        //});

       
       
       //Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
       
       //while(aute.getQuantideResult()<5){
           
         //  map = aute.obterDados(time);
           
       //}
       
       //System.out.println(map);
    
    }
               
}
        
        
        /*
        TimeSIEMG time = null;
        SplunkXML2Bean Bean;
        SplunkAuteSimultanea AuteSplunk = new SplunkAuteSimultanea(time);
        Bean = AuteSplunk.getBean();
        Bean.printConsole();
        */
    
