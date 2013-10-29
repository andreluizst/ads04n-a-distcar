package gui.managedBeans;


import java.util.List;

import javax.faces.bean.ManagedBean;


import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;

import fachada.Fachada;

@ManagedBean
public class ItemSerieBean {
	
	private  ItemSerieCarro itemSerieCarro = new ItemSerieCarro();
	private List< ModeloCarro > selectItemsModelo;
	private int codigoModeloSelecionado;
	public String mensagem;
	
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public ItemSerieCarro getItemSerieCarro() {
		return itemSerieCarro;
	}
	public void setItemSerieCarro(ItemSerieCarro itemSerieCarro) {
		this.itemSerieCarro = itemSerieCarro;
	}
	public List<ModeloCarro> getSelectItemsModelo() throws Exception {
		Fachada f = new Fachada();
		return f.listarModelo();
	}
	public void setSelectItemsModelo(List<ModeloCarro> selectItemsModelo) {
		this.selectItemsModelo = selectItemsModelo;
	}
	public int getCodigoModeloSelecionado() {
		return codigoModeloSelecionado;
	}
	public void setCodigoModeloSelecionado(int codigoModeloSelecionado) {
		this.codigoModeloSelecionado = codigoModeloSelecionado;
	}
	public ItemSerieBean() {
		super();
	}
	public ItemSerieBean(ItemSerieCarro itemSerieCarro,
			List<ModeloCarro> selectItemsModelo, int codigoModeloSelecionado) {
		super();
		this.itemSerieCarro = itemSerieCarro;
		this.selectItemsModelo = selectItemsModelo;
		this.codigoModeloSelecionado = codigoModeloSelecionado;
	}
	
	public void salvar() throws Exception{
		
		Fachada f = new Fachada();
		itemSerieCarro.setModeloCarro(f.pesquisarModeloCarro(codigoModeloSelecionado));
		f.salvarItemSerie(itemSerieCarro);
		setMensagem("Salvo com sucesso!");
	}
}
