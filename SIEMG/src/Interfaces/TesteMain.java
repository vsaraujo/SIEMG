/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Evento.AlertaAuteSimples;
import Funcionalidades.AuteSimples;
import Funcionalidades.TimeSIEMG;
import Login.Credenciais;
import Monitoramento.MonitorSIEMG;
import Monitoramento.Monitoramento;
import Parametros.GrupoParametros;
import Parametros.Parametro;
import Parametros.Campos;
import Parametros.Operadores;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Evento.Alerta;

/**
 *
 * @author Vítor
 */
public class TesteMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        //MonitorSIEMG monitor = MonitorSIEMG.getInstancia();
        
        TimeSIEMG time = new TimeSIEMG(600);
        TimeSIEMG time2 = new TimeSIEMG(600);
        TimeSIEMG time3 = new TimeSIEMG(600);

        int idt1 = 1;
        int idt2 = 2;
        int idt3 = 3;
        
        GrupoParametros g1 = new GrupoParametros();
        GrupoParametros g2 = new GrupoParametros();
        GrupoParametros g3 = new GrupoParametros();
        
        g1.setParametro(new Parametro(Campos.USER,Operadores.CONTEM,"www"));
        g1.setParametro(new Parametro(Campos.CONTADOR,Operadores.MAIOR_QUE,"0"));

        g2.setParametro(new Parametro(Campos.USER,Operadores.CONTEM,"adm"));
        g2.setParametro(new Parametro(Campos.CONTADOR,Operadores.MAIOR_QUE,"0"));
        
        g3.setParametro(new Parametro(Campos.USER,Operadores.CONTEM,"esi"));
        g3.setParametro(new Parametro(Campos.CONTADOR,Operadores.MAIOR_QUE,"0"));

        System.out.println("##Inicio da criação do 1 - Evento1##");
        AlertaAuteSimples evt1 = new AlertaAuteSimples(time, idt1);
        evt1.setParametros(g1);
        System.out.println("##FIM da criação do 1 - Evento1##");
        
        System.out.println("##Inicio da criação do 2 - Evento2##");
        AlertaAuteSimples evt2 = new AlertaAuteSimples(time2, idt2);
        evt2.setParametros(g2);        
        System.out.println("##FIM da criação do 2 - Evento2");

        System.out.println("##Inicio da criação do 3 - Evento3##");
        AlertaAuteSimples evt3 = new AlertaAuteSimples(time3, idt3);
        evt3.setParametros(g3);
        System.out.println("##FIM da criação do 3 - Evento3");

        //monitor.anexarEvento(evt1);
        //monitor.anexarEvento(evt2);
        //monitor.anexarEvento(evt3);
        
        //monitor.startEventos();
        
        int fim = 0;
        
//        while (evt1.getStatus() || evt2.getStatus() || evt3.getStatus()){
//            
//            fim++;
//                    
//            System.out.println("####"+fim+"º Tentativa de finalizar");
//            System.out.println("#### Status evt1 = "+evt1.getStatus());
//            System.out.println("#### Status evt2 = "+evt2.getStatus());
//            System.out.println("#### Status evt3 = "+evt3.getStatus());
//                        
//             try {
//                Thread.sleep(10*1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(AlertaAuteSimples.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        exit(0);
    }
}
