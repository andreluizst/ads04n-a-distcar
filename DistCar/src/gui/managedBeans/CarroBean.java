package gui.managedBeans;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
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
public class CarroBean {


	private Carro carro;
	private List<Carro> carros;
	private Carro carroSelecionado;
	private Carro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private List<Fabricante> fabricantes;
	private List<MarcaCarro> marcas;
	private List<ModeloCarro> modelos;
	private List<VersaoCarro> versoes;
	private List<ItemSerieCarro> itens;
	private List<AcessorioCarro> acessorios;

	
	public List<ItemSerieCarro> getItens() {
		return itens;
	}

	public void setItens(List<ItemSerieCarro> itens) {
		this.itens = itens;
	}

	public List<AcessorioCarro> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<AcessorioCarro> acessorios) {
		this.acessorios = acessorios;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public List<MarcaCarro> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaCarro> marcas) {
		this.marcas = marcas;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public List<VersaoCarro> getVersoes() {
		return versoes;
	}

	public void setVersoes(List<VersaoCarro> versoes) {
		this.versoes = versoes;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public Carro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Carro situacaoSelecionada) {
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
		carro = new Carro();
		listarModelo();
		listarCarros();
		itens=null;
		acessorios=null;
		listarFabricantes();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}

	private List<Carro> listarCarros() {  
      carros = Fachada.obterInstancia().listarCarros();
      return carros;
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
	
	
	public String salvar() throws Exception {
		
		carro.setDataUltimaAtualizacao(Calendar.getInstance());
		Fachada.obterInstancia().salvarCarro(carro);
		MsgPrimeFaces.exibirMensagemInfomativa("Carro salvo com sucesso!");
		init();
		return "carro";
	
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
		if(carroSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um carro para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerCarro(carroSelecionado);
		MsgPrimeFaces.exibirMensagemInfomativa("Acessório Excluído com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listarCarros();
	}
	
	    public String novo(){
	    	carro = new Carro();
			return "carro-prop";
		}           
	    
	    public String alterar(){
	    	if (carroSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione um acessório para alterar!");
	    		return "acessorio";
	    	}
	    	else{
	    	carro = Fachada.obterInstancia().pesquisarCarroCodigo(carroSelecionado.getCodigo());
	    	//acessorioCarro.setModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(acessorioSelecionado.getModelo().getCodigo()));
	    	return "carro-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	init();
	    	carroSelecionado = null;
	    	 itens=null;
			 acessorios=null;
	    	return "carro";
	    }  
	    
	    public String consultar(){
	    	
		 	 carros = Fachada.obterInstancia().consultarCarros(carro); 
			 init();
			 itens=null;
			 acessorios=null;
			 return  "carro";
	    }
	    

	    public void filtrarMarca(ValueChangeEvent evento){
	    	
	    	try{
	    		Fabricante fab = new Fabricante();
	    		fab = (Fabricante) evento.getNewValue();
				marcas = Fachada.obterInstancia().pesquisarMarcaPorFabr(fab.getCodigo());
	    	}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeAviso("Não foi possível filtar itens por modelo, selecione um modelo válido");
			}
	    }
	    
	    public void filtrarModelo(ValueChangeEvent evento){
	    	
	    	try{
	    		MarcaCarro ma = new MarcaCarro();
	    		ma = (MarcaCarro) evento.getNewValue();
				modelos = Fachada.obterInstancia().pesquisarModeloPorMarca(ma.getCodigo());		
	    	}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeAviso("Não foi possível filtar itens por modelo, selecione um modelo válido");
			}
	    }
	    
	    public void filtrarVersao(ValueChangeEvent evento){
	    	
	    	try{
	    		ModeloCarro mc = new ModeloCarro();
	    		mc = (ModeloCarro) evento.getNewValue();
				versoes = Fachada.obterInstancia().pesquisarVersaoPorModelo(mc.getCodigo());
				acessorios = Fachada.obterInstancia().listarAcessoriosPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(mc.getCodigo()));
				itens = Fachada.obterInstancia().listarItensPorModelo(Fachada.obterInstancia().pesquisarModelosCarroCodigo(mc.getCodigo()));
	    	}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeAviso("Não foi possível filtar itens por modelo, selecione um modelo válido");
				itens=null;
				acessorios=null;
			}
	    }
}

