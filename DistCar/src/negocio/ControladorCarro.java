package negocio;

import java.util.List;

import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;
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
	
	//Versao de carro
	
	public void inserir(VersaoModeloCarro entidade) {
		versaoModeloCarroDAO.inserir(entidade);
	}
	public void alterar(VersaoModeloCarro entidade) {
		versaoModeloCarroDAO.alterar(entidade);
	}
	public void remover(VersaoModeloCarro entidade) {
		versaoModeloCarroDAO.remover(entidade);
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
	
	//Acessório Carro
	


}
