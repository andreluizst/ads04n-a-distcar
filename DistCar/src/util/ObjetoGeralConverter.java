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
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		// TODO Auto-generated method stub
		if(value!=null && !"".equals(value)){
			Fachada f = new Fachada();
			System.out.println(value.toString());
			ModeloCarro m = new ModeloCarro();
			m = f.pesquisarModeloCarro(Integer.valueOf(value));
			System.out.println(m.toString());
			return m;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		System.out.println(value.toString());
		
		if(value instanceof ModeloCarro){
			
			ModeloCarro modelo = (ModeloCarro) value;
			return String.valueOf(modelo.getCodigo());
		}
		return "";
		/*if(value!=null && !"".equals(value)){
			ObjetoGeral obj = (ObjetoGeral) value;
			 if (obj.getCodigo() != null) {
	                this.addAttribute(component, obj);
	 
	                if (obj.getCodigo() != null) {
	                    return String.valueOf(obj.getCodigo());
	                }
			return (String) value;
			 }
		}
		return "";
	}
	
	private void addAttribute(UIComponent component, ObjetoGeral o){
		this.getAttributesFrom(component).put(o.getCodigo().toString(),o);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}*/
}
}
