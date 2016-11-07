
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vítor
 */
public class XMLtoBean {

    public static void main(String[] args) {
        
        FileReader reader = null;
        
        try {
            reader = new FileReader("../SIEMG/src/XML/Resultado.xml");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLtoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(SplunkXML2Bean.Field.class);
        xstream.processAnnotations(SplunkXML2Bean.FieldOrder.class);
        xstream.processAnnotations(SplunkXML2Bean.Meta.class);
        xstream.processAnnotations(SplunkXML2Bean.Result.class);
        xstream.processAnnotations(SplunkXML2Bean.Value.class);
            
        SplunkXML2Bean data = (SplunkXML2Bean) xstream.fromXML(reader);
        
        data.printConsole();
    }

}
