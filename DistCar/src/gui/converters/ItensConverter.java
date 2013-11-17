package gui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import classesBasicas.ItemSerieCarro;
import fachada.Fachada;

@FacesConverter(value="ItensConverter",forClass=ItemSerieCarro.class)
public class ItensConverter implements Converter {
	
	 public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
            if (value == null)   
                return null;  
            Integer id = new Integer(value);              
            return Fachada.obterInstancia().pesquisarItemCodigo(id);  
    }  
  
    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
        
        if (value == null) 
            return null;  
        if (value instanceof ItemSerieCarro) {  
            ItemSerieCarro aux = (ItemSerieCarro) value;  
            return aux.getCodigo().toString();  
        }  
        else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
            return "-1";  
        }
		return null; 
    } 	
}