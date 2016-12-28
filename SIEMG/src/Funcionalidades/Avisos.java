/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor.araujo
 */
public class Avisos {
    
    private static Component frame;
    private static String msg;
    
    private static Avisos instancia;
    private static Boolean executando;

    public Avisos(){
        
        executando = Boolean.FALSE;        
        frame = new JFrame();
        
    }  
    
    public static void  setExecutando(Boolean executando) {
        Avisos.executando = executando;
    }

    public static Boolean getExecutando() {
        return executando;
    }

    

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        
        Avisos.msg = msg;        
        JOptionPane.showMessageDialog(frame, msg);
        
    }
    
    public static synchronized Avisos getInstancia() {
        
        if(instancia == null)
            instancia = new Avisos();
        
        return instancia;
        
    }
}
