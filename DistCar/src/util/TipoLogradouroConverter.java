package util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import classesBasicas.TipoLogradouro;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@FacesConverter(forClass=TipoLogradouro.class, value="tipoLogradouroConverter")
public class TipoLogradouroConverter implements Converter {
	private ResourceBundle rb = ResourceBundle.getBundle("util.idioma_pt_BR");

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		TipoLogradouro obj = null;
		if (valor == null)
			return null;
		Integer codigo = new Integer(valor);
		if (codigo == -1)
			return null;
		try{
			obj = Fachada.obterInstancia().pegarTipoLogradouroPorId(codigo);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(rb.getString("msgErroConverterTitulo"), 
					MessageFormat.format(rb.getString("msgErroConverter"), "TipoLogradouro")
			);
		}
		return obj; 
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object objArg) {
		TipoLogradouro obj = (TipoLogradouro)objArg;
		return obj!=null?obj.getCodigo()+"":"null";
	}

}
