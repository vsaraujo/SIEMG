/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplunkFile;

import Funcionalidades.TimeSIEMG;
import Splunk.SplunkConnect;
import Splunk.SplunkXML2Bean;
import com.splunk.Job;
import com.splunk.JobArgs;
import com.splunk.Service;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class SplunkFileXML implements SplunkFile {

    String busca;
    String caminhoArquivo;
    TimeSIEMG time;
    SplunkConnect consplunk;
   
    private final File ResultadoXML;

    public SplunkFileXML(String busca, TimeSIEMG time) throws IOException {

        if (!(busca.trim().startsWith("|")) && !(busca.substring(0, 6).equalsIgnoreCase("search"))) {
            this.busca = "search " + busca;
        }

        this.time = time;
        caminhoArquivo = "src/Resultado/ResultadoXML.xml";        
        
        ResultadoXML = new File(caminhoArquivo);
        ResultadoXML.createNewFile();

    }    

    @Override
    public void gerarArquivo() {
        
        try {
            if(consplunk==null)
                consplunk = new SplunkConnect();
            
            Service svc = consplunk.getSvc();
            
            JobArgs jobArgs = new JobArgs();
            jobArgs.setExecutionMode(JobArgs.ExecutionMode.NORMAL);
            jobArgs.setSearchMode(JobArgs.SearchMode.NORMAL);
            jobArgs.setEarliestTime("-1m");
            jobArgs.setLatestTime("now");
            jobArgs.setStatusBuckets(300);

            Job job = svc.getJobs().create(busca,jobArgs);
            
            while (!job.isDone()) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("count", 0);
            InputStream results = job.getResults(arguments);

            //InputStream results = job.getResults();
            String line = null;
            BufferedReader br;
            try (FileWriter XMLnew = new FileWriter(ResultadoXML)) {
                br = new BufferedReader(new InputStreamReader(results, "UTF-8"));
                while ((line = br.readLine()) != null) {
                    XMLnew.write(line);
                    //System.out.println(line);
                }
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SplunkFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object getBean() {
    
        FileReader reader = null;
        
        try {
            reader = new FileReader(caminhoArquivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SplunkFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(SplunkXML2Bean.Field.class);
        xstream.processAnnotations(SplunkXML2Bean.FieldOrder.class);
        xstream.processAnnotations(SplunkXML2Bean.Meta.class);
        xstream.processAnnotations(SplunkXML2Bean.Result.class);
        xstream.processAnnotations(SplunkXML2Bean.Value.class);
        
        SplunkXML2Bean Bean = (SplunkXML2Bean) xstream.fromXML(reader);
        
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(SplunkFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Bean.printConsole();   
        
        return Bean;        
    }
}
