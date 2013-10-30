package gui.managedBeans;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import fachada.Fachada;

@ManagedBean
public class ItemSerieBean {
	
	private  ItemSerieCarro itemSerieCarro = new ItemSerieCarro();
	private List< ModeloCarro > selectItemsModelo;
	private int codigoModeloSelecionado;
	public String mensagem;
	private Date data;
	
	public ItemSerieBean() {
		super();
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
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
	
		
	/*public List<ModeloCarro> listarModelo(){
	try{
		selectItemsModelo = Fachada.obterInstancia().listarModelo();
		mensagem = "Modelos Listado com sucessos!";
		return selectItemsModelo;
	}catch(Exception ex){
		mensagem = ex.getMessage();
	}
	return selectItemsModelo;
}
	public ItemSerieBean(){
		
		itemSerieCarro = new ItemSerieCarro();
		listarModelo();
		data = Calendar.getInstance().getTime();
	}
	public List<ModeloCarro> getSelectItemsModelo() throws Exception {
	return selectItemsModelo;
	}
	public void novo(ActionEvent actionEvent){
		itemSerieCarro = new ItemSerieCarro();
	}
	public void salvarAjax(ActionEvent actionEvent) throws Exception{
		salvar();
	}
	*/

}
