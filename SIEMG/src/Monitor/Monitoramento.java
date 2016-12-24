/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import Evento.Evento;

/**
 *
 * @author vitor.araujo
 */
public interface Monitoramento {

    public void anexarEvento(Evento e);
    public void removerEvento(Evento e);
    public void verificarStatus();

}
