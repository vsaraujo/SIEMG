/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Parametros.GrupoParametros;

/**
 *
 * @author Vítor
 */
public interface Dados {
    
    public Object obterDados(TimeSIEMG time, GrupoParametros param);
    public int getQuantideResult();
      
}
