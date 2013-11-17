package gui.managedBeans;

import fachada.Fachada;
import gui.MsgPrimeFaces;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;


@ManagedBean
@SessionScoped
public class ModeloBean {

	private ModeloCarro modelo;
	private List<ModeloCarro> modelos;
	private ModeloCarro modeloSelecionado;
	private ModeloCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private List<MarcaCarro> marcas;
    
	
	public List<MarcaCarro> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaCarro> marcas) {
		this.marcas = marcas;
	}

	public ModeloCarro getModelo() {
		return modelo;
	}

	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public ModeloCarro getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(ModeloCarro modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public ModeloCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(ModeloCarro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	@PostConstruct
	public void init() {
		modelo = new ModeloCarro();
		listarModelo();
		listarMarcas();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}
	
	public String salvar() throws Exception {
		modelo.setDataUltimaAtualizacao(Calendar.getInstance());
		System.out.println(modelo);
		Fachada.obterInstancia().salvarModeloCarro(modelo);
		MsgPrimeFaces.exibirMensagemInfomativa("Versão de carro salvo com sucesso!");
		init();
		return "modelo";
	}
		
	public List<ModeloCarro> listarModelo() {
		try {
			modelos = Fachada.obterInstancia().listarModelosCarros();
			return modelos;
		} catch (Exception ex) {
			MsgPrimeFaces.exibirMensagemInfomativa( ex.getMessage());
		}
		return modelos;
	}
	
	public List<MarcaCarro> listarMarcas(){
		return marcas = Fachada.obterInstancia().listarMarcasCarros();
	}

	public void excluir(){
		if(modeloSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um modelo de carro para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerModeloCarro(modeloSelecionado);
		MsgPrimeFaces.exibirMensagemInfomativa("Modelo excluído com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listarModelo();
	}
	
	    public String novo(){
	    	modelo = new ModeloCarro();
			return "modelo-prop";
		}           
	    
	    public String alterar(){
	    	if (modeloSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma modelo de carro para alterar!");
	    		return "modelo";
	    	}
	    	else{
	    	modelo = Fachada.obterInstancia().pesquisarModelosCarroCodigo(modeloSelecionado.getCodigo());
	    	//modelo.setModeloCarro(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoSelecionada.getModeloCarro().getCodigo()));
	    	return "modelo-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	modelo = new ModeloCarro();
	    	modeloSelecionado = null;
	    	return "modelo";
	    }  
	    
	    public String consultar(){
		 	 modelos = Fachada.obterInstancia().consultarModelosCarros(modelo);
			 modelo= new ModeloCarro();  
			 return  "modelo";
	    }
}
