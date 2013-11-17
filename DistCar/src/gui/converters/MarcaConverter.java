package gui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import classesBasicas.MarcaCarro;
import fachada.Fachada;

@FacesConverter(value="MarcaConverter",forClass=MarcaCarro.class)
public class MarcaConverter implements Converter {
	
	 public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
            if (value == null)   
                return null;  
            Integer id = new Integer(value);              
            return Fachada.obterInstancia().pesquisarMarcasCarroCodigo(id);  
    }  
  
    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
        
        if (value == null) 
            return null;  
        if (value instanceof MarcaCarro) {  
            MarcaCarro aux = (MarcaCarro) value;  
            return aux.getCodigo().toString();  
        }  
        else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
            return "-1";  
        }
		return null; 
    } 	
}