/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import Funcionalidades.TimeSIEMG;

/**
 *
 * @author vitor.araujo
 */
public interface Evento {
    
    public int getStatus();
    public int getIndex();
    public void setParametros(Parametros param);
    public void setJanela(TimeSIEMG janela);
    public void setAgendamento(TimeSIEMG agendamento);
    
}