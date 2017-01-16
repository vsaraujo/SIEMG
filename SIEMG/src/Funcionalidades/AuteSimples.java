/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Parametros.Campos;
import Parametros.GrupoParametros;
import Parametros.Operadores;
import Parametros.Parametro;
import Splunk.SplunkAuteSimples;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class AuteSimples implements Funcionalidades{

    private SplunkAuteSimples autesimples;
    private Map<Integer,Parametro> listaregras;
    
    public AuteSimples () throws IOException{
        
        autesimples = null;
        listaregras = null;
        
    }
   
    @Override
    public Map<Integer,List<String>> obterDados(TimeSIEMG time, GrupoParametros param) {
        
        listaregras = param.getMapParametros();
        String parametros = converterParametroTOString(listaregras);        
        
        try {
            if(autesimples == null){
                autesimples = new SplunkAuteSimples(time,parametros);
            }
        } catch (IOException ex) {
            Logger.getLogger(AuteSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            autesimples.gerarNovoArquivo();
        } catch (IOException ex) {
            Logger.getLogger(AuteSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return autesimples.getMap();
    }

    @Override
    public int getQuantideResult() {
        return autesimples.getSize();
    }

    private String converterParametroTOString(Map<Integer, Parametro> listaregras) {
        
        String resultado = "";
        
        for(Integer i : listaregras.keySet()){
            
            Parametro param = listaregras.get(i);
            Campos campo = param.getCampo();
            Operadores operador = param.getOperador();
            String valor = param.getValor();
            
            resultado += getStringParametros(getStringCampos(campo),operador,valor)+" ";
        }
        
        return resultado;
    }

    private String getStringCampos(Campos campo) {
        
        String cmp = "";       
        
        switch (campo) {
            case COMPUTADOR:
                cmp = "Workstation_Name";
                break;
            case CONTADOR:
                cmp = "count";
                break;
            case HORA_FIMEVENTO:
                cmp = "lastTime";
                break;
            case HORA_INIEVENTO:
                cmp = "firstTime";
                break;
            case USER:
                cmp = "user";
                break;
            default:
                break;
        }        
        
        return cmp;
        
    }

    private String getStringParametros(String cmp, Operadores operador, String vlr) {
        
        String op = "";       
        
        switch (operador) {
            case CONTEM:
                op = "| where like("+cmp+",\"%"+vlr+"%\")";
                break;
            case IGUAL:
                op = "| where like("+cmp+",\""+vlr+"\")";
                break;
            case INICIA_COM:
                op = "| where like("+cmp+",\""+vlr+"%\")";
                break;
            case MAIOR_QUE:
                op = "| where "+cmp+" > "+vlr;
                break;
            case MENOR_QUE:
                op = "| where "+cmp+" < "+vlr;
                break;
            case NAO_CONTEM:
                op = "| where NOT like("+cmp+",\"%"+vlr+"%\")";
                break;
            case NAO_IGUAL:
                op = "| where NOT like("+cmp+",\""+vlr+"\")";
                break;
            case TERMINA_COM:
                op = "| where like("+cmp+",\"%"+vlr+"\")";
                break;
            default:
                break;
        }
        
        return op;
    }
    
}
