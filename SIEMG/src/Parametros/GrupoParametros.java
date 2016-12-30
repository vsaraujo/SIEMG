/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parametros;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vitor.araujo
 */
public class GrupoParametros {

    Map<Integer, Parametro> map;
    private int idx;

    public GrupoParametros() {

        map = new HashMap<>();
        idx = 0;

    }

    public void setParametro(Parametro param) {

        map.put(idx, param);
        idx++;

    }

    public Parametro getParametro(int id) {

        return map.get(id);

    }
    
    public void removeParametro(int id){
        
        map.remove(id);
        
    }

    public Map<Integer, Parametro> getMapParametros() {

        return map;

    }

}
