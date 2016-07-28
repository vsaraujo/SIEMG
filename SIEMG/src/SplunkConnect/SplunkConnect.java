/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplunkConnect;

import com.splunk.*;       // The entry point to the client library
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author vitor.araujo
 */
public class SplunkConnect {

    private String mySearch;
    private Service svc;
    private ServiceArgs loginArgs;
    //private JobExportArgs exportArgs;
    private Args outputArgs;
    private File ResultadoXML;

    public SplunkConnect(String args[]) throws IOException {

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
        
        ResultadoXML = new File("src/XML/Resultado.xml");
        ResultadoXML.createNewFile();
        //exportArgs = new JobExportArgs();
        //exportArgs.setSearchMode(JobExportArgs.SearchMode.NORMAL);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Enter a search string then press <ENTER>: ");
        //String mySearch = br.readLine();        
    }

    public void enviarSearch(String mySearch) throws IOException {

        if (!(mySearch.trim().startsWith("|")) && !(mySearch.substring(0, 6).equalsIgnoreCase("search"))) {
            mySearch = "search " + mySearch;
        }

        Job job = svc.getJobs().create(mySearch);
        while (!job.isDone()) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        InputStream results = job.getResults();
        
        String line = null;
        FileWriter XMLnew = new FileWriter(ResultadoXML);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(results, "UTF-8"));
        while ((line = br.readLine()) != null) {
            XMLnew.write(line);
            System.out.println(line);
        }
        XMLnew.close();
        br.close();        

    }
}
