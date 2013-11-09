package gui.managedBeans;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import classesBasicas.VersaoCarro;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class VersaoBean {
	
	private VersaoCarro versaoCarro;
	private List<ModeloCarro> modeloCarros;
	private List<VersaoCarro> listarVersoes;
	private VersaoCarro versaoSelecionada;
	private VersaoCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();

	public VersaoCarro getVersaoCarro() {
		return versaoCarro;
	}

	public void setVersaoCarro(VersaoCarro versaoCarro) {
		this.versaoCarro = versaoCarro;
	}

	public List<ModeloCarro> getModeloCarros() {
		return modeloCarros;
	}

	public void setModeloCarros(List<ModeloCarro> modeloCarros) {
		this.modeloCarros = modeloCarros;
	}

	public List<VersaoCarro> getListarVersoes() {
		return listarVersoes;
	}

	public void setListarVersoes(List<VersaoCarro> listarVersoes) {
		this.listarVersoes = listarVersoes;
	}

	public VersaoCarro getVersaoSelecionada() {
		return versaoSelecionada;
	}

	public void setVersaoSelecionada(VersaoCarro versaoSelecionada) {
		this.versaoSelecionada = versaoSelecionada;
	}

	public VersaoCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(VersaoCarro situacaoSelecionada) {
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
		versaoCarro = new VersaoCarro();
		listarModelo();
		listarVersoes();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}

	private List<VersaoCarro> listarVersoes() {  
      listarVersoes = Fachada.obterInstancia().listarVersoes();
      return listarVersoes;
      } 
	
	public String salvar() throws Exception {
		
		versaoCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		Fachada.obterInstancia().salvarVersao(versaoCarro);
		MsgPrimeFaces.exibirMensagemInfomativa("Versão de carro salvo com sucesso!");
		init();
		return "versao";
	}
		
	public void listar(){
		listarVersoes = Fachada.obterInstancia().listarVersoes();
	}
		
	public List<ModeloCarro> listarModelo() {
		try {
			modeloCarros = Fachada.obterInstancia().listarModelosCarros();
			return modeloCarros;
		} catch (Exception ex) {
			MsgPrimeFaces.exibirMensagemInfomativa( ex.getMessage());
		}
		return modeloCarros;
	}


	public void excluir(){
		if(versaoSelecionada==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma versão do carro para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerVersao(versaoSelecionada);
		MsgPrimeFaces.exibirMensagemInfomativa("Versão excluída com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listar();
	}
	
	    public String novo(){
	    	versaoCarro = new VersaoCarro();
			return "versao-prop";
		}           
	    
	    public String alterar(){
	    	if (versaoSelecionada==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma versão de carro para alterar!");
	    		return "versao";
	    	}
	    	else{
	    	versaoCarro = versaoSelecionada;
	    	versaoCarro.setModeloCarro(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoSelecionada.getModeloCarro().getCodigo()));
	    	return "versao-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	versaoCarro = new VersaoCarro();
	    	versaoSelecionada = null;
	    	return "versao";
	    }  
	    
	    public String consultar(){
	    	
		 	 listarVersoes = Fachada.obterInstancia().consultarVersoes(versaoCarro);
			 versaoCarro= new VersaoCarro();  
			 return  "versao";
	    }
}
