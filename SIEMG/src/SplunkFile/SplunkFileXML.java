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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class SplunkFileXML implements SplunkFile {
    
    /**
     * Esta classe é responsável por obter os dados da consulta ao Splunk e
     * retorna-lo no formato XML e Objecto Java.     *  
     */

    private String busca;
    private SplunkXML2Bean Bean;
    private static Service svc;
    private static SplunkConnect consplunk;
    private final File ResultadoXML;
    private final String caminhoArquivo;
    private final String timeEarliest;

    public SplunkFileXML(String nameFile, String busca, TimeSIEMG time) throws IOException {

//      Esta validação é necessário devido a forma como o Splunk identifica uma consulta
        
        if (!(busca.trim().startsWith("|")) && !(busca.substring(0, 6).equalsIgnoreCase("search"))) {
            this.busca = "search " + busca;
        }

        timeEarliest = "-" + time.getExecucao() + "s";
        caminhoArquivo = "src/Resultado/" + nameFile + ".xml";
        ResultadoXML = new File(caminhoArquivo);
        ResultadoXML.createNewFile();

    }

    @Override
    public void gerarArquivo() {

        try {

//          As credenciais de acesso são geradas a partir da classe SplunkConnect.

            consplunk = SplunkConnect.getSplunkConnect();
            svc = consplunk.getSvc();

//          Estes jobs são criados a cada consulta no Splunk.

            JobArgs jobArgs = new JobArgs();
            jobArgs.setExecutionMode(JobArgs.ExecutionMode.NORMAL);
            jobArgs.setSearchMode(JobArgs.SearchMode.NORMAL);
            jobArgs.setEarliestTime(timeEarliest);
            jobArgs.setLatestTime("now");
            jobArgs.setStatusBuckets(300);
            Job job = svc.getJobs().create(busca, jobArgs);

//          Aguardando a finalização da consulta realizada.

            while (!job.isDone()) {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(SplunkConnect.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

//          Por padrão o Splunk retorna apenas 100 resultados. Para mais resultados é necessásio passar estes argumento no Job.

            InputStream results = job.getResults();
            Map<String, Object> arguments = new HashMap<>();
            arguments.put("count", 0);
            results = job.getResults(arguments);

            
//          Gera arquivo XML no pacote "Resultado" com o retorno da consulta

            String line = null;
            BufferedReader br;
            try (FileWriter XMLnew = new FileWriter(ResultadoXML)) {
                br = new BufferedReader(new InputStreamReader(results, "UTF-8"));
                while ((line = br.readLine()) != null) {
                    XMLnew.write(line);
                }
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(SplunkFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Map<Integer, List<String>> getBean() {

        FileReader reader = null;

        try {
            reader = new FileReader(caminhoArquivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SplunkFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }

//      A classe XStream é responsável pela conversão do dados coletados no XML para um Objeto Java.

        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(SplunkXML2Bean.Field.class);
        xstream.processAnnotations(SplunkXML2Bean.FieldOrder.class);
        xstream.processAnnotations(SplunkXML2Bean.Meta.class);
        xstream.processAnnotations(SplunkXML2Bean.Result.class);
        xstream.processAnnotations(SplunkXML2Bean.Value.class);

//      A classe SplunkXMLBean serve de máscara para obter os dados do XML.

        Bean = (SplunkXML2Bean) xstream.fromXML(reader);
        
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(SplunkFileXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Bean.getMap();
    }

}
