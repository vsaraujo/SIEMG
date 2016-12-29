/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

/**
 *
 * @author vitor.araujo
 */
public class Parametro {
    
    /**
     * Este são os Campos.
     * Utilizar a função GET para obter o código do Operador (int)     * 
     */
    
    
    public enum Campos{
        
        USER,
        COMPUTADOR,
        HORA_INIEVENTO,        
        HORA_FIMEVENTO,        
        CONTADOR;
        
        
    }
    
    /**
     * Estes são os Operadores.
     * Utilizar a função GET para obter o código do Operador (int)
     */
    
    public enum Operadores{
        
        IGUAL (1),
        NAO_IGUAL(2),
        CONTEM(3),        
        NAO_CONTEM(4),        
        INICIA_COM(5),
        TERMINA_COM(6),
        MAIOR_QUE(7),
        MENOR_QUE(8);
        
        private int operador;

        Operadores(int id){
            this.operador = id;
        }
        
        public int get() {
            return operador;
        }

        public void setOperador(int operador) {
            this.operador = operador;
        }
        
    }    
}
