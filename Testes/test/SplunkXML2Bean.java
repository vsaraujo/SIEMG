
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vítor
 */
@XStreamAlias("results")
public class SplunkXML2Bean {
    
    @XStreamAlias("meta")
    private Meta meta = new Meta();
    
    @XStreamImplicit(itemFieldName = "result")
    private List<Result> result = new ArrayList();

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
    
    public int getSizeResult() {
        return result.size();
    }
    
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    
    @XStreamAlias("meta")
    public class Meta {
        
        @XStreamAlias("fieldOrder")
        private FieldOrder fieldOrder = new FieldOrder();

        public FieldOrder getFieldOrder() {
            return fieldOrder;
        }

        public void setFieldOrder(FieldOrder fieldOrder) {
            this.fieldOrder = fieldOrder;
        }
    }
    
    @XStreamAlias("fieldOrder")
    public class FieldOrder {        
        @XStreamImplicit(itemFieldName = "field")
        private List<String> field = new ArrayList();

        public List<String> getField() {
            return field;
        }

        public void setField(List<String> field) {
            this.field = field;
        }
        
        public int getSizeFieldOrder() {
            return field.size();
        }
    }
    
    
    @XStreamAlias("result")
    public class Result{        
        @XStreamImplicit(itemFieldName = "field k=")
        private List<Field> field = new ArrayList();

        public List<Field> getField() {
            return field;
        }

        public void setField(List<Field> field) {
            this.field = field;
        }
        public int getSizeFields() {
            return field.size();
        }
    }
    
    @XStreamAlias("field k=")
    public class Field{
        @XStreamImplicit(itemFieldName = "value")
        private List<Value> value = new ArrayList();

        public List<Value> getValue() {
            return value;
        }

        public void setValue(List<Value> value) {
            this.value = value;
        }
        
        public int getSizeValue() {
            return value.size();
        }
    }
    
    @XStreamAlias("value")
    public class Value{
        @XStreamImplicit(itemFieldName = "text")
        private List<String> text = new ArrayList();

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }
        public int getSizeValue() {
            return text.size();
        }
    }
    
    public void printConsole(){
        
        for(String field : meta.getFieldOrder().getField() ){
            System.out.print("| "+field+" \t");            
        }
        System.out.println();
        
        for (Result result : result){
            for(Field field : result.getField()){
                for(Value value : field.getValue()){
                    for(String text: value.getText() ){
                        System.out.print("| "+text+" \t");
                    }
                }
            }
            System.out.println();                  
        }        
    }

}
