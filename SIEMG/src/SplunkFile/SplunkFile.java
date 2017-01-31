/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SplunkFile;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Vítor
 */
public interface SplunkFile {
    
    /**
     * Interface para implementar outros formatos de exportação de dados
     */
    
    public void gerarArquivo();
    public Map<Integer,List<String>> getBean();
    
}
