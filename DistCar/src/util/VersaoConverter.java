package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import classesBasicas.VersaoCarro;
import fachada.Fachada;

public class VersaoConverter {

	@FacesConverter(value="versaoConverter",forClass=VersaoCarro.class)
	public class ModeloCarroConverter implements Converter {
		
		 public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
	            if (value == null)   
	                return null;  
	            Integer id = new Integer(value);              
	            return Fachada.obterInstancia().pesquisarVersaoCodigo(id);  
	    }  
	  
	    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
	        
	        if (value == null) 
	            return null;  
	        if (value instanceof VersaoCarro) {  
	            VersaoCarro aux = (VersaoCarro) value;  
	            return aux.getCodigo().toString();  
	        }  
	        else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
	            return "-1";  
	        }
			return null; 
	    } 	
	}
}

