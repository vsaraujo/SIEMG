/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import Funcionalidades.TimeSIEMG;

/**
 *
 * @author vitor.araujo
 */
public interface Evento {
    
    enum status {        
        STATUS_SEM_RESULTADO,
        STATUS_COM_RESULTADO,
        STATUS_EXECUTANDO;
    }
    
    public void criarEvento();
    public int getStatus();
    public void setParametros(Parametros param);
    public void setJanela(TimeSIEMG janela);
    public void setAgendamento(TimeSIEMG agendamento);
    
}
