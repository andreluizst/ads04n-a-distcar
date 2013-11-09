package gui.managedBeans;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import fachada.Fachada;
import gui.MsgPrimeFaces;



@ManagedBean
@SessionScoped
public class ItemSerieBean {

	private ItemSerieCarro itemSerieCarro;
	private List<ModeloCarro> modeloCarros;
	public String mensagem;
	private Date data;
	private List<ItemSerieCarro> listaItens;
	private ItemSerieCarro itemSelecionado;
	private ItemSerieCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private int codigoSelecionado;
	
	
	
	public int getCodigoSelecionado() {
		return codigoSelecionado;
	}


	public void setCodigoSelecionado(int codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
	}


	@PostConstruct
	public void init() {
		itemSerieCarro = new ItemSerieCarro();
		itemSerieCarro.setModeloCarro(new ModeloCarro());
		listarModelo();
		data = Calendar.getInstance().getTime();
		listarItens();
	}


	public ItemSerieCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(ItemSerieCarro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	


	public ItemSerieCarro getItemSelecionado() {
		return itemSelecionado;
	}



	public void setItemSelecionado(ItemSerieCarro itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}



	public void novo(ActionEvent actionEvent) {
		init();
	}

	public ItemSerieCarro getItemSerieCarro() {
		return itemSerieCarro;
	}

	public void setItemSerieCarro(ItemSerieCarro itemSerieCarro) {
		this.itemSerieCarro = itemSerieCarro;
	}

	public List<ModeloCarro> getModeloCarros() {
		return modeloCarros;
	}

	public void setModeloCarros(List<ModeloCarro> modeloCarros) {
		this.modeloCarros = modeloCarros;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItemSerieCarro> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemSerieCarro> listaItens) {
		this.listaItens = listaItens;
	}

	private List<ItemSerieCarro> listarItens() {  
      listaItens = Fachada.obterInstancia().listarItens();
      return listaItens;
      } 
	
	public String salvar() throws Exception {
		
		itemSerieCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		Fachada.obterInstancia().salvarItemSerie(itemSerieCarro);
		MsgPrimeFaces.exibirMensagemInfomativa("Item Séria salvo com sucesso!");
		init();
		return "item";
	
	}
		
	public void listar(){
		listaItens = Fachada.obterInstancia().listarItens();
	}
		
	public List<ModeloCarro> listarModelo() {
		try {
			modeloCarros = Fachada.obterInstancia().listarModelosCarros();
			return modeloCarros;
		} catch (Exception ex) {
			mensagem = ex.getMessage();
		}
		return modeloCarros;
	}


	public void excluir(){
		if(itemSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um item para exclusão!");
		}
		else{
		Fachada.obterInstancia().removerItem(itemSelecionado);
		MsgPrimeFaces.exibirMensagemInfomativa("Item Excluído com sucesso!");
		consulta();
		}
	}
	
	public void consulta(){
		listaItens = Fachada.obterInstancia().listarItens();
	}
	
		  /* public void onEdit(RowEditEvent event) throws Exception {  
		   
		   ItemSerieCarro i = (ItemSerieCarro) event.getObject();
		   itemSerieCarro = Fachada.obterInstancia().pesquisarItem(i.getCodigo());
		   Fachada.obterInstancia().salvarItemSerie(itemSerieCarro);
      
	    }  
	      
	    public void onCancel(RowEditEvent event) {  
	        FacesMessage msg = new FacesMessage("Car Cancelled", "Cancelado");    
	  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    } */
	    public String novo(){
	    	itemSerieCarro = new ItemSerieCarro();
			return "novoItem";
		}           
	    
	    public String alterar(){
	    	if (itemSelecionado==null){
	    		MsgPrimeFaces.exibirMensagemInfomativa("Selecione um item para alterar!");
	    		return "item";
	    	}
	    	else{
	    	itemSerieCarro = itemSelecionado;
	    	itemSerieCarro.setModeloCarro(Fachada.obterInstancia().pesquisarModelosCarroCodigo(itemSelecionado.getModeloCarro().getCodigo()));
	    	return "item-prop";
	    	}
	    }
	    
	    public String cancelar(){
	    	itemSerieCarro = new ItemSerieCarro();
	    	itemSelecionado = null;
	    	return "item";
	    }  
	    
	    public String consultar(){
	  
		 	 listaItens = Fachada.obterInstancia().consultarItens(itemSerieCarro);
			 itemSerieCarro = new ItemSerieCarro();  
			 return  "item";
	    }
	    
}
