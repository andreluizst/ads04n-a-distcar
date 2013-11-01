package negocio;

import java.util.List;

import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;
import dao.DAOCarro;
import dao.DAOItemSerieCarro;
import dao.DAOModeloCarro;
import dao.DAOVersaoModeloCarro;
import dao.IDAOCarro;
import dao.IDAOItemSerieCarro;
import dao.IDAOModeloCarro;
import dao.IDAOVersaoModeloCarro;

public class ControladorCarro {
	
	private IDAOCarro carroDAO;
	private IDAOItemSerieCarro itemSerieCarroDAO;
	private IDAOVersaoModeloCarro versaoModeloCarroDAO;
	private IDAOModeloCarro modeloCarroDAO;
	
	public ControladorCarro(){
		super();
		this.itemSerieCarroDAO = new DAOItemSerieCarro();
		this.carroDAO = new DAOCarro();
		this.versaoModeloCarroDAO = new DAOVersaoModeloCarro();
		this.modeloCarroDAO = new DAOModeloCarro();
		
	}
	//Carro
	
	public void inserir(Carro carro) {
		carroDAO.inserir(carro);
	}
	public void alterar(Carro entidade) {
		carroDAO.alterar(entidade);
	}
	public void remover(Carro entidade) {
		carroDAO.remover(entidade);
	}
	public List<Carro> pesquisarCarroPorChassi(String chassi) {
		return carroDAO.pesquisarCarroPorChassi(chassi);
	}
	public List<Carro> consultarTodos() {
		return carroDAO.consultarTodos();
	}
	
	//Modelo Carro
	public void inserir(ModeloCarro entidade) {
		modeloCarroDAO.inserir(entidade);
	}

	public void alterar(ModeloCarro entidade) {
		modeloCarroDAO.alterar(entidade);
	}

	public void remover(ModeloCarro entidade) {
		modeloCarroDAO.remover(entidade);
	}
	
	public ModeloCarro pesquisarModeloCarro(int codigo) {
		return modeloCarroDAO.consultarPorId(codigo);
	}
	
	public List<ModeloCarro> consultarTodosModelos() {
		return modeloCarroDAO.consultarTodos();
	}
	
	//Versao de carro
	
	public void inserir(VersaoCarro entidade) {
		versaoModeloCarroDAO.inserir(entidade);
	}
	public void alterar(VersaoCarro entidade) {
		versaoModeloCarroDAO.alterar(entidade);
	}
	public void remover(VersaoCarro entidade) {
		versaoModeloCarroDAO.remover(entidade);
	}
	
	public List<VersaoCarro> consultarTodasVersao() {
		return versaoModeloCarroDAO.consultarTodos();
	}
	
	public VersaoCarro pesquisarVersao(int codigo){
		return versaoModeloCarroDAO.consultarPorId(codigo);
	}
	//Item de Serie
	
	public void inserir(ItemSerieCarro entidade) {
		itemSerieCarroDAO.inserir(entidade);
	}
	
	public void alterar(ItemSerieCarro entidade) {
		itemSerieCarroDAO.alterar(entidade);
	}
	public void remover(ItemSerieCarro entidade) {
		itemSerieCarroDAO.remover(entidade);
	}
	
	public List<ItemSerieCarro> listarItem(){
		return itemSerieCarroDAO.consultarTodos();
	}
	//Acessório Carro
	


}
