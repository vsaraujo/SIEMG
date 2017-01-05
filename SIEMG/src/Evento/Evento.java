/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.TimeSIEMG;
import Parametros.GrupoParametros;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vitor.araujo
 */
public interface Evento {
    
    public Boolean getStatus();
    public void setStatus(Boolean status);    
    public void setParametros(GrupoParametros param);
    public void setTitle(String title);
    public void setIndice(Integer idx);
    public Integer getIndice();
    public String getTitle();
    public Map<Integer, List<String>> getListaResultados();
    
    
}
