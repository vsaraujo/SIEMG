/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import Funcionalidades.AuteSimultanea;
import Funcionalidades.TimeSIEMG;
import Monitor.Monitor;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Vítor
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TesteMain {
    public static void main(String[] args) throws IOException {
        Runnable alerta = new Monitor(3000);
        new Thread(alerta).start();     
    }
}
        
        
        /*
        TimeSIEMG time = null;
        SplunkXML2Bean Bean;
        SplunkAuteSimultanea AuteSplunk = new SplunkAuteSimultanea(time);
        Bean = AuteSplunk.getBean();
        Bean.printConsole();
        */
    
