package negocio;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;
import dao.DAOAcessorio;
import dao.DAOCarro;
import dao.DAOItemSerieCarro;
import dao.DAOModeloCarro;
import dao.DAOVersaoModeloCarro;
import dao.IDAOAcessorio;
import dao.IDAOCarro;
import dao.IDAOItemSerieCarro;
import dao.IDAOModeloCarro;
import dao.IDAOVersaoModeloCarro;

public class ControladorCarro {
	
	private IDAOCarro carroDAO;
	private IDAOItemSerieCarro itemSerieCarroDAO;
	private IDAOVersaoModeloCarro versaoModeloCarroDAO;
	private IDAOModeloCarro modeloCarroDAO;
	private IDAOAcessorio acessorioDAO;
	
	public ControladorCarro(){
		super();
		this.itemSerieCarroDAO = new DAOItemSerieCarro();
		this.carroDAO = new DAOCarro();
		this.versaoModeloCarroDAO = new DAOVersaoModeloCarro();
		this.modeloCarroDAO = new DAOModeloCarro();
		this.acessorioDAO = new DAOAcessorio();
		
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
	
	public void inserir(ItemSerieCarro itemSerieCarro) {
		itemSerieCarroDAO.inserir(itemSerieCarro);
	}
	
	public void alterar(ItemSerieCarro itemSerieCarro) {
		itemSerieCarroDAO.alterar(itemSerieCarro);
	}
	public void remover(ItemSerieCarro itemSerieCarro) {
		itemSerieCarroDAO.remover(itemSerieCarro);
	}
	
	public List<ItemSerieCarro> listarItem(){
		return itemSerieCarroDAO.consultarTodos();
	}
	public ItemSerieCarro pesquisarItem(int codigo) {
		return itemSerieCarroDAO.consultarPorId(codigo);
	}
	
	public List<ItemSerieCarro> pesquisarItens(ItemSerieCarro itemSerieCarro){
		return itemSerieCarroDAO.pesquisar(itemSerieCarro);
	}
	
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo){
		return itemSerieCarroDAO.pesquisarPorModelo(codigo);
	}
	//Acessório Carro
	

	public List<AcessorioCarro> listarAcessorio() {
		return acessorioDAO.consultarTodos();
	}

	public void inserir(AcessorioCarro acessorio) {
		acessorioDAO.inserir(acessorio);
		
	}

	public AcessorioCarro pesquisarAcessorioCarro(int codigo) {
		return acessorioDAO.consultarPorId(codigo);
	}


	public void removerAcessorio(AcessorioCarro acessorio) {
		acessorioDAO.remover(acessorio);
		
	}

	public List<AcessorioCarro> pesquisarAcessorio(AcessorioCarro acessorio) {
		return acessorioDAO.pesquisar(acessorio);
	}


}
