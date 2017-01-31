/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parametros;

/**
 *
 * @author vitor.araujo
 */
public class Parametro {
    
    /**
     * Classe responsável pelo objeto Paramentro que é utilizado pelo Grupo de Parametros dos alertas gerados.
     */

    private Campos campo;
    private Operadores operador;
    private String valor;

    public Parametro(Campos campo, Operadores operador, String valor) {

        this.campo = campo;
        this.operador = operador;
        this.valor = valor;

    }

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    public Operadores getOperador() {
        return operador;
    }

    public void setOperador(Operadores operador) {
        this.operador = operador;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
