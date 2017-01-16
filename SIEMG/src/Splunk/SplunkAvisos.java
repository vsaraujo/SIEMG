/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor.araujo
 */
public class SplunkAvisos {
    
    private static Component frame;
    private static String msg;
    private static SplunkAvisos instancia;
    private static Boolean executando;

    private SplunkAvisos(){
        
        executando = Boolean.FALSE;        
        frame = new JFrame();
        
    }  
    
    public static void  setExecutando(Boolean executando) {
        SplunkAvisos.executando = executando;
    }

    public static Boolean getExecutando() {
        return executando;
    }

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        
        SplunkAvisos.msg = msg;        
        JOptionPane.showMessageDialog(frame, msg);
        
    }
    
    public static synchronized SplunkAvisos getInstancia() {
        
        if(instancia == null)
            instancia = new SplunkAvisos();
        
        return instancia;
        
    }
}
