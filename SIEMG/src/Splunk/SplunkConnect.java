/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import com.splunk.*;       // The entry point to the client library
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author vitor.araujo
 */
public class SplunkConnect {

  
    
    private Service svc;
    private ServiceArgs loginArgs;
    
    String usuario,senha,servidor = null;
   
    public SplunkConnect() {   
        TelaLogin login = new TelaLogin(this);
        
        while (login.executando()) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        System.out.println("Finalizado login");
        startConection();
    }
      
    public void startConection(){
        
         if (usuario.equals("") || senha.equals("") || servidor.equals("")) {
            System.out.println("Usage:\n\tjava SplunkConnect <username> <password> <hostname> <port>");
            System.out.println("\tE.G. java SplunkConnect admin P@55w0rd 127.0.0.1 8089\n");
            System.exit(1);
        }

        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);

        // Create a map of arguments and add login parameters
        loginArgs = new ServiceArgs();
        loginArgs.setUsername(usuario);
        loginArgs.setPassword(senha);
        loginArgs.setHost(servidor);
        loginArgs.setPort(8089);

        // Create a Service instance and log in with the argument map
        svc = Service.connect(loginArgs);
        //System.out.println(svc.getUsers());
        
        
    }
    
    public Service getSvc() {
        
        return svc;        
        
    }

     public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }
}
