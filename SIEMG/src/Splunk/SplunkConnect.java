/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import Login.Credenciais;
import Interfaces.Tela_Login;
import com.splunk.HttpService;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;
import java.util.logging.Level;

/**
 *
 * @author vitor.araujo
 */
public final class SplunkConnect {
    
    /**
     * Esta classe é responsável pela autenticação das credenciais de acesso ao Splunk.
     * Esta classe é do tipo Singleton, onde apenas será instanciado um só vez.
     */

    private Service svc;
    private ServiceArgs loginArgs;
    private final Credenciais credenciais;
    private Tela_Login login = null;
    private static SplunkConnect splunkConnect;    

    
    public static synchronized SplunkConnect getSplunkConnect() {

        if (splunkConnect == null) {
            splunkConnect = new SplunkConnect();
        }
        return splunkConnect;
    }

    private SplunkConnect() {

        login = Tela_Login.getInstancia();
        credenciais = Credenciais.getInstancia();

        efetuarLogin();

    }

    private void abrirTelaLogin() {
        login.setVisible(true);
    }

    public void efetuarLogin() {

         if (login.executando()) {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    abrirTelaLogin();
                }
            });

        }

        while (login.executando()) {
     
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       }
        
        SplunkAvisos.setExecutando(Boolean.FALSE);
        login.setVisible(false);
        startConection();
    }

    public void startConection() {

        if (credenciais.isNull()) {

            if (!SplunkAvisos.getExecutando()) {

                SplunkAvisos.setExecutando(Boolean.TRUE);
                SplunkAvisos.setMsg("Favor preencher todos os campos.");               

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

            if (!SplunkAvisos.getExecutando()) {

                SplunkAvisos.setExecutando(Boolean.TRUE);
                SplunkAvisos.setMsg("Falha de login. Tente novamente.");

            }

            login.reinicializando();
            efetuarLogin();            

        }
        
        if (!SplunkAvisos.getExecutando()) {

            SplunkAvisos.setExecutando(Boolean.TRUE);
            SplunkAvisos.setMsg("Login realizado com sucesso!");            

        }
    }

    public Service getSvc() {
        return svc;
    }
}
