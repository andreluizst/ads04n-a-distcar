package gui.managedBeans;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
	private int codigoSelecionado;
	public String mensagem;
	private Date data;
	private List<ItemSerieCarro> listaItens;
	private ItemSerieCarro itemSelecionado;
	private Fachada f;
	private ItemSerieCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	
	
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
		itemSerieCarro = new ItemSerieCarro();
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

	public int getCodigoSelecionado() {
		return codigoSelecionado;
	}

	public void setCodigoSelecionado(int codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
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
      listaItens = Fachada.obterInstancia().listarItem();
      return listaItens;
      } 
	
	public String salvar() throws Exception {
		
		itemSerieCarro.setModeloCarro(f.pesquisarModeloCarro(codigoSelecionado));
		//Adicionar os setSituação e setDataAtualizacao na regra de negocio;
		//itemSerieCarro.setSituacao(situacaoSelecionada);
		itemSerieCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		f.salvarItemSerie(itemSerieCarro);
		MsgPrimeFaces.exibirMensagemInfomativa("Item Séria salvo com sucesso!");
		itemSerieCarro = new ItemSerieCarro();
		consulta();
		return "item";
	
	}
		
	public void listar(){
		listaItens = Fachada.obterInstancia().listarItem();
	}
		
	public List<ModeloCarro> listarModelo() {
		try {
			modeloCarros = f.listarModelo();
			return modeloCarros;
		} catch (Exception ex) {
			mensagem = ex.getMessage();
		}
		return modeloCarros;
	}

	public ItemSerieBean() {
		f = new Fachada();
		itemSerieCarro = new ItemSerieCarro();
		listarModelo();
		data = Calendar.getInstance().getTime();
		listarItens();
	}

	public void excluir(){
		if(itemSelecionado==null){
			MsgPrimeFaces.exibirMensagemInfomativa("Selecione um item para exclusão!");
		}
		else{
		itemSerieCarro = f.pesquisarItem(itemSelecionado.getCodigo());
		f.removerItem(itemSerieCarro);
		consulta();
		}
	}
	
	public void consulta(){
		listaItens = f.listarItem();
	}
	public void Alterar(ItemSerieCarro itemSerieCarro) throws Exception{
		
		f.alterarItem(itemSerieCarro);
		MsgPrimeFaces.exibirMensagemInfomativa("Item Série alterado com sucesso!");
		this.itemSerieCarro = new ItemSerieCarro();
		consulta();
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
	    		System.out.println(itemSelecionado.toString());
	    	itemSerieCarro = itemSelecionado;
	    	
	    	return "novoItem";
	    	}
	    }
	    
	    /*public String cancelar(){
	    	itemSerieCarro = new ItemSerieCarro();
	    	consulta();
	    	return "item";
	    }   */
	    
	    public void consultar(){
	    
	    	itemSerieCarro.setModeloCarro(f.pesquisarModeloCarro(codigoSelecionado));
	    //	itemSerieCarro.setSituacao(situacaoSelecionada);
	    	System.out.println(itemSerieCarro.toString());
	    	listaItens = f.pesquisarItens(itemSerieCarro);
			 itemSerieCarro = new ItemSerieCarro();   	
	    }
	    
}
