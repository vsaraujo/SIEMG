/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vitor.araujo
 */
interface Monitor {
    
    List<Evento> listEvent = new ArrayList<Evento>();
    
    public void anexarEvento(Evento e);
    public void removerEvento(Evento e);
    public void verificarStatus();
    
}
