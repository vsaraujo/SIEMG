/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.TimeSIEMG;
import Parametros.GrupoParametros;

/**
 *
 * @author vitor.araujo
 */
public interface Evento {
    
    public Boolean getStatus();
    public void setStatus(Boolean status);
    public int getIndex();
    public void setParametros(GrupoParametros param);
    public void setJanela(TimeSIEMG janela);
    public void setAgendamento(TimeSIEMG agendamento);
    
}
