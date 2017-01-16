/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Parametros.GrupoParametros;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vítor
 */
public interface Funcionalidades {
    
    public Map<Integer, List<String>> obterDados(TimeSIEMG time, GrupoParametros param);
    public int getQuantideResult();
      
}
