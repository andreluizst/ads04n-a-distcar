package gui.managedBeans;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import classesBasicas.AcessorioCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class AcessorioBean {
	
	private AcessorioCarro acessorioCarro;
	private List<ModeloCarro> modeloCarros;
	private List<AcessorioCarro> listaAcessorios;
	private AcessorioCarro acessorioSelecionado;
	private AcessorioCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	
	public AcessorioCarro getAcessorioCarro() {
		return acessorioCarro;
	}
	public void setAcessorioCarro(AcessorioCarro acessorioCarro) {
		this.acessorioCarro = acessorioCarro;
	}
	public List<ModeloCarro> getModeloCarros() {
		return modeloCarros;
	}
	public void setModeloCarros(List<ModeloCarro> modeloCarros) {
		this.modeloCarros = modeloCarros;
	}
	
	public AcessorioCarro getAcessorioSelecionado() {
		return acessorioSelecionado;
	}
	public void setAcessorioSelecionado(AcessorioCarro acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}
	public AcessorioCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}
	public void setSituacaoSelecionada(AcessorioCarro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}
	public Situacao[] getSituacoes() {
		return situacoes;
	}
	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}
	
	public List<AcessorioCarro> getListaAcessorios() {
		return listaAcessorios;
	}
	public void setListaAcessorios(List<AcessorioCarro> listaAcessorios) {
		this.listaAcessorios = listaAcessorios;
	}
	@PostConstruct
	public void init() {
		acessorioCarro = new AcessorioCarro();
		listarModelo();
		listarAcessorios();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}


	private List<AcessorioCarro> listarAcessorios() {  
      listaAcessorios = Fachada.obterInstancia().listarAcessorios();
      return listaAcessorios;
      } 
	
	public String salvar() throws Exception {
		
		acessorioCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		Fachada.obterInstancia().salvarAcessorio(acessorioCarro);
		MsgPrimeFaces.exibirMensagemInfomativa("Item Séria salvo com sucesso!");
		init();
		return "acessorio";
	
	}
		
	public void listar(){
		listaAcessorios = Fachada.obterInstancia().listarAcessorios();
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
		if(acessorioSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um acessório para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerAcessorio(acessorioSelecionado);
		MsgPrimeFaces.exibirMensagemInfomativa("Acessório Excluído com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listar();
	}
	
	    public String novo(){
	    	acessorioCarro = new AcessorioCarro();
			return "acessorio-prop";
		}           
	    
	    public String alterar(){
	    	if (acessorioSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione um acessório para alterar!");
	    		return "acessorio";
	    	}
	    	else{
	    	acessorioCarro = acessorioSelecionado;
	    	acessorioCarro.setModeloCarro(Fachada.obterInstancia().pesquisarModelosCarroCodigo(acessorioSelecionado.getModeloCarro().getCodigo()));
	    	return "acessorio-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	acessorioCarro = new AcessorioCarro();
	    	acessorioSelecionado = null;
	    	return "acessorio";
	    }  
	    
	    public String consultar(){
	    	
		 	 listaAcessorios = Fachada.obterInstancia().consultarAcessorios(acessorioCarro);
			 acessorioCarro= new AcessorioCarro();  
			 return  "acessorio";
	    }
	    

}
