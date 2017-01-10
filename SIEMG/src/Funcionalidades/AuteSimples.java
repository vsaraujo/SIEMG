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
import Splunk.SplunkAuteSimultanea;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor
 */
public class AuteSimples implements Dados{

    private SplunkAuteSimultanea autesimultanea;
    private Map<Integer,Parametro> listaregras;
    
    public AuteSimples () throws IOException{
        
        //listaregras = new HashMap<>();
        
    }
   
    @Override
    public Map<Integer,List<String>> obterDados(TimeSIEMG time, GrupoParametros param) {
        
        listaregras = param.getMapParametros();
        String parametros = converterParametroTOString(listaregras);        
        
        try {
            if(autesimultanea == null){
                System.out.println("Criando SplunkAuteSimultanea");
       
                autesimultanea = new SplunkAuteSimultanea(time,parametros);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(AuteSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            autesimultanea.gerarNovoArquivo();
            //autesimultanea.getBean().printConsole();
        } catch (IOException ex) {
            Logger.getLogger(AuteSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return autesimultanea.getMap();
    }

    @Override
    public int getQuantideResult() {
        return autesimultanea.getSize();
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
        
        if(campo.equals(Campos.COMPUTADOR)){
            
            cmp = "Workstation_Name";
            
        }else if(campo.equals(Campos.CONTADOR)){
            
            cmp = "count";
            
        }else if(campo.equals(Campos.HORA_FIMEVENTO)){
            
            cmp = "lastTime";
            
        }else if(campo.equals(Campos.HORA_INIEVENTO)){
            
            cmp = "firstTime";
            
        }else if(campo.equals(Campos.USER)){
            
            cmp = "user";
        }        
        
        return cmp;
        
    }

    private String getStringParametros(String cmp, Operadores operador, String vlr) {
        
        String op = "";       
        
        if(operador.equals(Operadores.CONTEM)){
            
            op = "| where like("+cmp+",\"%"+vlr+"%\")";
            
        }else if(operador.equals(Operadores.IGUAL)){
            
            op = "| where like("+cmp+",\""+vlr+"\")";
            
        }else if(operador.equals(Operadores.INICIA_COM)){
            
            op = "| where like("+cmp+",\""+vlr+"%\")";
            
        }else if(operador.equals(Operadores.MAIOR_QUE)){
            
            op = "| where "+cmp+" > "+vlr;
            
        }else if(operador.equals(Operadores.MENOR_QUE)){
            
            op = "| where "+cmp+" < "+vlr;
            
        }else if(operador.equals(Operadores.NAO_CONTEM)){
            
            op = "| where NOT like("+cmp+",\"%"+vlr+"%\")";
            
        }else if(operador.equals(Operadores.NAO_IGUAL)){
            
            op = "| where NOT like("+cmp+",\""+vlr+"\")";
            
        }else if(operador.equals(Operadores.TERMINA_COM)){
            
            op = "| where like("+cmp+",\"%"+vlr+"\")";
            
        }
        
        return op;
    }
    
}
