package gui.managedBeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.AcessorioCarro;
import classesBasicas.Fabricante;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import classesBasicas.VersaoCarro;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class VersaoBean {
	
	private VersaoCarro versaoCarro;
	private List<ModeloCarro> modelos;
	private List<VersaoCarro> listarVersoes;
	private VersaoCarro versaoSelecionada;
	private VersaoCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private List<AcessorioCarro> acessorios;
	private List<ItemSerieCarro> itens;
    private List<ItemSerieCarro> itensSelecionados;
    private List<AcessorioCarro> acessoriosSelecionados;
    private List<Fabricante> fabricantes;
    private List<MarcaCarro> marcas;
    private Integer fabricante;
    private Integer marca;
    private double valorItens;
    private double valorAcessorios;


	public double getValorItens() {
		return valorItens;
	}

	public void setValorItens(double valorItens) {
		this.valorItens = valorItens;
	}

	public double getValorAcessorios() {
		return valorAcessorios;
	}

	public void setValorAcessorios(double valorAcessorios) {
		this.valorAcessorios = valorAcessorios;
	}

	public Integer getMarca() {
		return marca;
	}

	public void setMarca(Integer marca) {
		this.marca = marca;
	}

	public Integer getFabricante() {
		return fabricante;
	}

	public void setFabricante(Integer fabricante) {
		this.fabricante = fabricante;
	}

	public List<MarcaCarro> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaCarro> marcas) {
		this.marcas = marcas;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public VersaoCarro getVersaoCarro() {
		return versaoCarro;
	}

	public void setVersaoCarro(VersaoCarro versaoCarro) {
		this.versaoCarro = versaoCarro;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
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

	public List<AcessorioCarro> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<AcessorioCarro> acessorios) {
		this.acessorios = acessorios;
	}

	public List<ItemSerieCarro> getItens() {
		return itens;
	}

	public void setItens(List<ItemSerieCarro> itens) {
		this.itens = itens;
	}

	public List<ItemSerieCarro> getItensSelecionados() {
		return itensSelecionados;
	}

	public void setItensSelecionados(List<ItemSerieCarro> itensSelecionados) {
		this.itensSelecionados = itensSelecionados;
	}

	public List<AcessorioCarro> getAcessoriosSelecionados() {
		return acessoriosSelecionados;
	}

	public void setAcessoriosSelecionados(
			List<AcessorioCarro> acessoriosSelecionados) {
		this.acessoriosSelecionados = acessoriosSelecionados;
	}

	@PostConstruct
	public void init() {
		versaoCarro = new VersaoCarro();
		versaoCarro.setModeloCarro(new ModeloCarro());
		listarVersoes();
		itens=null;
		acessorios=null;
		versaoSelecionada=null;
		listarFabricantes();
		marcas =null;
		situacaoSelecionada=null;
		modelos=null;
		valorAcessorios=0;
	}

	private List<VersaoCarro> listarVersoes() {  
      listarVersoes = Fachada.obterInstancia().listarVersoes();
      return listarVersoes;
      } 
	
	public String salvar() {
		
		versaoCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		
		try {			
			
			versaoCarro.setValor(versaoCarro.getValor()+valorAcessorios+valorItens);
			Fachada.obterInstancia().salvarVersao(versaoCarro);
			MsgPrimeFaces.exibirMensagemInfomativa("Versão de carro salvo com sucesso!");
			init();
			itens=null;
			acessorios=null;
			return "versao";
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			versaoCarro.setValor(versaoCarro.getValor()-valorAcessorios-valorItens);
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
			listarVersoes();
		}
		return null;
	}
		
	public void listar(){
		listarVersoes = Fachada.obterInstancia().listarVersoes();
	}
	
	private List<Fabricante> listarFabricantes(){  
	      try {
			fabricantes = Fachada.obterInstancia().listarFabricantes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return fabricantes;
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
	    	itens=null;
	    	acessorios=null;
			return "versao-prop";
		}           
	    
	    public String alterar() throws Exception{
	    	if (versaoSelecionada==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione uma versão de carro para alterar!");
	    		return "versao";
	    	}
	    	else{
	    	itens = Fachada.obterInstancia().listarItensPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoSelecionada.getModeloCarro().getCodigo()));
			acessorios = Fachada.obterInstancia().listarAcessoriosPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoSelecionada.getModeloCarro().getCodigo()));
			marcas = Fachada.obterInstancia().pesquisarMarcaPorFabr(versaoSelecionada.getModeloCarro().getMarcaCarro().getCodigo());
			modelos = Fachada.obterInstancia().pesquisarModeloPorMarca(versaoSelecionada.getModeloCarro().getCodigo());
			versaoCarro = Fachada.obterInstancia().pesquisarVersaoCodigo(versaoSelecionada.getCodigo());
			versaoCarro.setModeloCarro(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoSelecionada.getModeloCarro().getCodigo()));
			versaoCarro.setValor(versaoCarro.getModeloCarro().getValor());
			//fabricante = versaoSelecionada.getModeloCarro().getMarcaCarro().getFabricante().getCodigo();
			//marca = versaoSelecionada.getModeloCarro().getMarcaCarro().getCodigo();
	    	//itens = versaoCarro.getItens();
	    	//acessorios = versaoCarro.getAcessorios();
	    	return "versao-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	init();
	    	versaoSelecionada=null;
	    	return "versao";
	    }  
	    
	    public String consultar(){
	    	
		 	 try {
				listarVersoes = Fachada.obterInstancia().consultarVersoes(versaoCarro);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 versaoCarro= new VersaoCarro();  
			 return  "versao";
	    }
	    
	    public void listarAcessorios(){
	    	
	    	acessorios = Fachada.obterInstancia().listarAcessoriosPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoCarro.getModeloCarro().getCodigo()));
	    }
	    
	  /*  public List<ItemSerieCarro> listarItens(){
	    	//itens = Fachada.obterInstancia().listarItensPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoCarro.getModeloCarro().getCodigo()));
	    	return itens = Fachada.obterInstancia().listarItensPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(1));
	    }*/
	    
	    public void filtrarItens(ValueChangeEvent evento){
	    	
	    	try{
	    		ModeloCarro mo = new ModeloCarro();
	    		mo = (ModeloCarro) evento.getNewValue();
	    		versaoCarro.setModeloCarro(mo);
	    		versaoCarro.setValor(mo.getValor());
				itens = Fachada.obterInstancia().listarItensPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoCarro.getModeloCarro().getCodigo()));
				acessorios = Fachada.obterInstancia().listarAcessoriosPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(versaoCarro.getModeloCarro().getCodigo()));
	    	}catch(Exception ex){
	    		versaoSelecionada=null;
	    		itens=null;
	    		acessorios=null;
				init();
			}
	    }
	    
		@SuppressWarnings("unchecked")
		public void somar(ValueChangeEvent evento){
	    	List<ItemSerieCarro> i = new ArrayList<>();
	    	List<AcessorioCarro> a = new ArrayList<>();
	    	double somaI = 0;
	    	double somaA=0;
	    	try {
	    		i = (List<ItemSerieCarro>) evento.getNewValue();
	    			for(ItemSerieCarro is :i)
		    		somaI= somaI + is.getValorItemSerie();
			} catch (Exception e) {
				// TODO: handle exception
				a = (List<AcessorioCarro>) evento.getNewValue();
		    	for(AcessorioCarro as : a){
		    		somaA= somaA + as.getValor();
		    	}
	    	}
	    	valorItens = somaI;
	    	valorAcessorios = somaA;
	    }   
		
		  public void filtrarMarca(ValueChangeEvent evento){
		    	
		    	try{
		    		Fabricante fab = new Fabricante();
		    		fab = (Fabricante) evento.getNewValue();
					marcas = Fachada.obterInstancia().pesquisarMarcaPorFabr(fab.getCodigo());
		    	}catch(Exception ex){
					init();	
				}
		    }
		    
		    public void filtrarModelo(ValueChangeEvent evento){
		    	
		    	try{
		    		MarcaCarro ma = new MarcaCarro();
		    		ma = (MarcaCarro) evento.getNewValue();
					modelos = Fachada.obterInstancia().pesquisarModeloPorMarca(ma.getCodigo());	
		    	}catch(Exception ex){
		    		init();
				}
		    }
}
