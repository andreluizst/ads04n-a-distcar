package gui.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import classesBasicas.Fabricante;
import fachada.Fachada;

@FacesConverter(value="fabricanteConverter",forClass=Fabricante.class)
public class FabricanteConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value)  {     
        if (value == null )   
            return null;  
        Integer id = new Integer(value);  
        try {
			return Fachada.obterInstancia().consultarFabricantePorId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
}  

public String getAsString(FacesContext context, UIComponent componente, Object value) {  
    
    if (value == null) 
        return null;  
    if (value instanceof Fabricante) {  
        Fabricante aux = (Fabricante) value; 
        if (aux.getCodigo()!= null)
        	return aux.getCodigo().toString();  
    }  
    else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
        return "-1";  
    }
	return null; 
} 	

}
