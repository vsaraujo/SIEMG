/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import com.splunk.*;       // The entry point to the client library
import java.io.IOException;

/**
 *
 * @author vitor.araujo
 */
public class SplunkConnect {

    private Service svc;
    private ServiceArgs loginArgs;
   
    public SplunkConnect() throws IOException {

        String[] args = {"", ""};
        
        if (args.length != 4) {
            System.out.println("Usage:\n\tjava SplunkConnect <username> <password> <hostname> <port>");
            System.out.println("\tE.G. java SplunkConnect admin P@55w0rd 127.0.0.1 8089\n");
            System.exit(1);
        }

        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);

        // Create a map of arguments and add login parameters
        loginArgs = new ServiceArgs();
        loginArgs.setUsername(args[0]);
        loginArgs.setPassword(args[1]);
        loginArgs.setHost(args[2]);
        loginArgs.setPort(new Integer(args[3]));

        // Create a Service instance and log in with the argument map
        svc = Service.connect(loginArgs);
        
     }
    
    public Service getSvc() {
        return svc;
    }
}
