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
    public static void main(String[] args) throws IOException{
        
       TimeSIEMG time = new TimeSIEMG(120000);
       TimeSIEMG time2 = new TimeSIEMG(60000);
       
       System.out.println("##Inicio da criação do 1 - Evento1##");
       EventoAute evt1 = new EventoAute(time);
       evt1.getStatus();
       System.out.println("##FIM da criação do 1 - Evento1##");
       
       
       
       System.out.println("##Inicio da criação do 2 - Evento2##");
       EventoAute evt2 = new EventoAute(time2);
       evt2.getStatus();
       System.out.println("##FIM da criação do 2 - Evento2");
       
       //Map<Integer,List<String>> map;
        //map = aute.obterDados(time);
       exit(0);
       
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
    
