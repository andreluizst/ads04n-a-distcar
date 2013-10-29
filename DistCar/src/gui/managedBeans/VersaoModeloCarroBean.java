package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;
import fachada.Fachada;

@ManagedBean
public class VersaoModeloCarroBean {
	
	private VersaoModeloCarro versaoModeloCarro = new VersaoModeloCarro();
	private String mensage;
	
	public VersaoModeloCarro getVersaoModeloCarro() {
		return versaoModeloCarro;
	}
	public void setVersaoModeloCarro(VersaoModeloCarro versaoModeloCarro) {
		this.versaoModeloCarro = versaoModeloCarro;
	}
	public String getMensage() {
		return mensage;
	}
	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	
	public List<VersaoModeloCarro> pesquisaModeloCarros() throws Exception{
		Fachada f = new Fachada();
		return f.listarVersao();

	}
}
