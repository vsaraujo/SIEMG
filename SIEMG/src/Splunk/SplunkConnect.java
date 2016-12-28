/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import Funcionalidades.Avisos;
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
    private final Credenciais credenciais;
    private TelaLogin login = null;
    private static SplunkConnect splunkConnect;    

    public static synchronized SplunkConnect getSplunkConnect() {

        if (splunkConnect == null) {
            splunkConnect = new SplunkConnect();
        }

        return splunkConnect;
    }

    public SplunkConnect() {

        System.out.println("Criando Credenciais");
        login = TelaLogin.getInstancia();
        credenciais = Credenciais.getInstancia();

        efetuarLogin();

    }

    private void abrirTelaLogin() {

        System.out.println("Setando TelaLogin Visible");
        login.setVisible(true);

    }

    public void efetuarLogin() {

        System.out.println("Criando TelaLogin");

        if (login.executando()) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    abrirTelaLogin();
                }
            });

        }

        while (login.executando()) {

            System.out.println("Aguarde login ...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        Avisos.setExecutando(Boolean.FALSE);
        login.setVisible(false);
        System.out.println("Finalizado login");
        startConection();
    }

    public void startConection() {

        if (credenciais.isNull()) {

            System.out.println("Start - Credencial Vazia");

            if (!Avisos.getExecutando()) {

                Avisos.setExecutando(Boolean.TRUE);
                Avisos.setMsg("Favor preencher todos os campos.");               

            }

            login.reinicializando();

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

            if (!Avisos.getExecutando()) {

                Avisos.setExecutando(Boolean.TRUE);
                Avisos.setMsg("Falha de login. Tente novamente.");

            }

            login.reinicializando();
            efetuarLogin();            

        }
        
        if (!Avisos.getExecutando()) {

            Avisos.setExecutando(Boolean.TRUE);
            Avisos.setMsg("Login realizado com sucesso!");            

        }
    }

    public Service getSvc() {

        return svc;

    }
}
