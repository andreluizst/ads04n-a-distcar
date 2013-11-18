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
	private List<ModeloCarro> modelos;
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

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public List<AcessorioCarro> getListaAcessorios() {
		return listaAcessorios;
	}

	public void setListaAcessorios(List<AcessorioCarro> listaAcessorios) {
		this.listaAcessorios = listaAcessorios;
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
		MsgPrimeFaces.exibirMensagemInfomativa("Acessório salvo com sucesso!");
		init();
		return "acessorio";
	
	}
		
	/*public void listar(){
		listaAcessorios = Fachada.obterInstancia().listarAcessorios();
	}*/
		
	public List<ModeloCarro> listarModelo() {
		try {
			modelos = Fachada.obterInstancia().listarModelosCarros();
			return modelos;
		} catch (Exception ex) {
			MsgPrimeFaces.exibirMensagemInfomativa( ex.getMessage());
		}
		return modelos;
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
		listarAcessorios();
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
	    	acessorioCarro = Fachada.obterInstancia().pesquisarAcessorioCodigo(acessorioSelecionado.getCodigo());
	    	//acessorioCarro.setModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(acessorioSelecionado.getModelo().getCodigo()));
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
