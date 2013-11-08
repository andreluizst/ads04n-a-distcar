package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.expression.impl.ThisExpressionResolver;

import java.util.Map;

import classesBasicas.ModeloCarro;
import classesBasicas.ObjetoGeral;
import fachada.Fachada;

@FacesConverter(value="objetoGeralConverter",forClass=ModeloCarro.class)
public class ObjetoGeralConverter implements Converter {

	@Override
	/*public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		if(value!=null && !"".equals(value)){
			Fachada f = Fachada.obterInstancia();
			System.out.println(value.toString());
			ModeloCarro m = new ModeloCarro();
			m = f.pesquisarModeloCarro(Integer.valueOf(value));
			System.out.println(m.toString());
			return m;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		if (arg2 != null && ! "".equals(arg2)) {
			
			ModeloCarro mc = (ModeloCarro) arg2;
			if (mc.getCodigo() != null) {
			this.addAttribute(arg1, mc);
			if (mc.getCodigo() != null) {
			return String.valueOf(mc.getCodigo());
			}
			return (String) arg2;
			}
			}
			return "";
	}
	
	private void addAttribute(UIComponent component, ModeloCarro m) {
		this.getAttributesFrom(component).put(m.getCodigo().toString(), m);
		}
	
		private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
		}*/
	
	 public Object getAsObject(FacesContext context, UIComponent component, String value)  {  
        
     
            if (value == null || value.equalsIgnoreCase("-1"))   
                return null;  
            Integer id = new Integer(value);              
            return Fachada.obterInstancia().pesquisarModeloCarro(id);  
              
       
    }  
  
    public String getAsString(FacesContext context, UIComponent componente, Object value) {  
        
        if (value == null) 
            return null;  
        if (value instanceof ModeloCarro) {  
            ModeloCarro aux = (ModeloCarro) value;  
            return aux.getCodigo().toString();  
        }  
        else if(value instanceof String && ((String)value).equalsIgnoreCase("-1")){  
            return "-1";  
        }
		return null; 
    }
      	
}