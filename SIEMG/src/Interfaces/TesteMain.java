/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Alertas.Alerta;
import Alertas.AlertaTipo;
import Funcionalidades.AuteSimples;
import Funcionalidades.TimeSIEMG;
import Login.Credenciais;
import Monitoramento.MonitorStatus;
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

/**
 *
 * @author Vítor
 */
public class TesteMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        Monitoramento monitor = Monitoramento.getInstancia();
        
        TimeSIEMG time = new TimeSIEMG(600);
//        TimeSIEMG time2 = new TimeSIEMG(600);
//        TimeSIEMG time3 = new TimeSIEMG(600);

        int idt1 = 1;
//        int idt2 = 2;
//        int idt3 = 3;
        
        GrupoParametros g1 = new GrupoParametros();
//        GrupoParametros g2 = new GrupoParametros();
//        GrupoParametros g3 = new GrupoParametros();
        
        //g1.setParametro(new Parametro(Campos.USER,Operadores.CONTEM,"www"));
        g1.setParametro(new Parametro(Campos.CONTADOR,Operadores.MAIOR_QUE,"10"));

        
        System.out.println("##Inicio da criação do 1 - Evento1##");
        Alerta evt1 = new Alerta(time, idt1,AlertaTipo.FALHA_AUTENTICACAO);
        evt1.setParametros(g1);
        System.out.println("##FIM da criação do 1 - Evento1##");
//        
//        System.out.println("##Inicio da criação do 2 - Evento2##");
//        AlertaAuteSimples evt2 = new AlertaAuteSimples(time2, idt2);
//        evt2.setParametros(g2);        
//        System.out.println("##FIM da criação do 2 - Evento2");
//
//        System.out.println("##Inicio da criação do 3 - Evento3##");
//        AlertaAuteSimples evt3 = new AlertaAuteSimples(time3, idt3);
//        evt3.setParametros(g3);
//        System.out.println("##FIM da criação do 3 - Evento3");

        monitor.anexarAlerta(evt1);
        //monitor.anexarEvento(evt2);
        //monitor.anexarEvento(evt3);
        
        monitor.inicializarAlertas();
        
        int fim = 0;
        
        while (monitor.getListStatus().get(0)!= MonitorStatus.DISPARADO){
            
            fim++;
                    
            System.out.println("####"+fim+"º Tentativa de finalizar");
//            System.out.println("#### Status evt1 = "+evt1.getStatus());
//            System.out.println("#### Status evt2 = "+evt2.getStatus());
//            System.out.println("#### Status evt3 = "+evt3.getStatus());
//                        
           
              Thread.sleep(10*1000);
           
        }
        
        
        exit(0);
    }
}
