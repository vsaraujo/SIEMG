/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import Login.Credenciais;
import Login.TelaLogin;
import com.splunk.*;       // The entry point to the client library
import java.util.logging.Level;

/**
 *
 * @author vitor.araujo
 */
public final class SplunkConnect {

    private Service svc;
    private ServiceArgs loginArgs;
    private Credenciais credenciais;

    public SplunkConnect() {
        credenciais = new Credenciais();
        efetuarLogin();
    }

    public void efetuarLogin() {

        TelaLogin login = new TelaLogin(credenciais);
        login.setVisible(Boolean.TRUE);

        while (login.executando()) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        login.dispose();
        System.out.println("Finalizado login");
        startConection();
    }

    public void startConection() {

        if (credenciais.isNull()) {
            efetuarLogin();
        }

        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);

        // Create a map of arguments and add login parameters
        loginArgs = new ServiceArgs();
        loginArgs.setUsername(credenciais.getUsuario());
        loginArgs.setPassword(credenciais.getSenha());
        loginArgs.setHost(credenciais.getServidor());
        loginArgs.setPort(8089);

        // Create a Service instance and log in with the argument map
        try {
            svc = Service.connect(loginArgs);
        } catch (RuntimeException e) {

            System.out.println("Falha de Login");
            efetuarLogin();
        }

        //System.out.println(svc.getUsers());
        //System.out.println(svc.getInfo());
    }

    public Service getSvc() {
        return svc;
    }
}
