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
	
	private AcessorioCarro acessorio;
	private List<ModeloCarro> modeloCarros;
	private List<AcessorioCarro> listaAcessorios;
	private AcessorioCarro acessorioSelecionado;
	private Fachada f;
	private AcessorioCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	
	public AcessorioCarro getAcessorio() {
		return acessorio;
	}
	public void setAcessorio(AcessorioCarro acessorio) {
		this.acessorio = acessorio;
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
		f = Fachada.obterInstancia();
		acessorio = new AcessorioCarro();
		listarModelo();
		listarAcessorios();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}


	private List<AcessorioCarro> listarAcessorios() {  
      listaAcessorios = Fachada.obterInstancia().listarAcessorio();
      return listaAcessorios;
      } 
	
	public String salvar() throws Exception {
		
		acessorio.setDataUltimaAtualizacao(Calendar.getInstance());
		f.salvarAcessorio(acessorio);
		MsgPrimeFaces.exibirMensagemInfomativa("Item Séria salvo com sucesso!");
		init();
		return "acessorio";
	
	}
		
	public void listar(){
		listaAcessorios = Fachada.obterInstancia().listarAcessorio();
	}
		
	public List<ModeloCarro> listarModelo() {
		try {
			modeloCarros = f.listarModelo();
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
		f.removerAcessorio(acessorio);
		MsgPrimeFaces.exibirMensagemInfomativa("Acessório Excluído com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listar();
	}
	
	    public String novo(){
	    	acessorio = new AcessorioCarro();
			return "acessorio-prop";
		}           
	    
	    public String alterar(){
	    	if (acessorioSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione um acessório para alterar!");
	    		return "acessorio";
	    	}
	    	else{
	    	acessorio = acessorioSelecionado;
	    	acessorio.setModeloCarro(Fachada.obterInstancia().pesquisarModeloCarro(acessorioSelecionado.getModeloCarro().getCodigo()));
	    	return "acessorio-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	acessorio = new AcessorioCarro();
	    	acessorioSelecionado = null;
	    	return "acessorio";
	    }  
	    
	    public String consultar(){
	    	
		 	 listaAcessorios = Fachada.obterInstancia().pesquisarAcessorio(acessorio);
			 acessorio= new AcessorioCarro();  

			 return  "acessorio";
	    }
	    

}
