package gui.managedBeans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.Situacao;
import fachada.Fachada;

@ManagedBean
@SessionScoped
public class ItemSerieBean {

	private ItemSerieCarro itemSerieCarro = new ItemSerieCarro();
	private List<ModeloCarro> modeloCarros;
	private int codigoSelecionado;
	public String mensagem;
	private Date data;
	private List<ItemSerieCarro> listaItens;



	public ItemSerieBean(ItemSerieCarro itemSerieCarro,
			List<ModeloCarro> modeloCarros, int codigoSelecionado,
			String mensagem, Date data) {
		super();
		this.itemSerieCarro = itemSerieCarro;
		this.modeloCarros = modeloCarros;
		this.codigoSelecionado = codigoSelecionado;
		this.mensagem = mensagem;
		this.data = data;
	}

	public List<ItemSerieCarro> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemSerieCarro> listaItens) {
		this.listaItens = listaItens;
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

	public void salvar() throws Exception {

		Fachada f = new Fachada();
		itemSerieCarro.setModeloCarro(f
				.pesquisarModeloCarro(codigoSelecionado));
		//Adicionar os setSituação e setDataAtualizacao na regra de negocio;
		itemSerieCarro.setSituacao(Situacao.ATIVO);
		itemSerieCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		f.salvarItemSerie(itemSerieCarro);
		mensagem = "Item de série salvo som Sucesso";
		itemSerieCarro = new ItemSerieCarro();
		listaItens = f.listarItem();
	
		
	}

	public List<ModeloCarro> listarModelo() {
		try {
			modeloCarros = Fachada.obterInstancia().listarModelo();
			return modeloCarros;
		} catch (Exception ex) {
			mensagem = ex.getMessage();
		}
		return modeloCarros;
	}

	public ItemSerieBean() {

		itemSerieCarro = new ItemSerieCarro();
		listarModelo();
		data = Calendar.getInstance().getTime();
		listarItens();
	}

	public void novo(ActionEvent actionEvent) {
		itemSerieCarro = new ItemSerieCarro();
	}

	private List<ItemSerieCarro> listarItens() {  
      listaItens = Fachada.obterInstancia().listarItem();
      return listaItens;
         } 
	
	public void salvarAjax(ActionEvent actionEvent) throws Exception {
		salvar();
	}
	
}
