package negocio;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;
import dao.DAOAcessorio;
import dao.DAOCarro;
import dao.DAOItemSerieCarro;
import dao.DAOMarcaCarro;
import dao.DAOModeloCarro;
import dao.DAOVersaoCarro;
import dao.IDAOAcessorio;
import dao.IDAOCarro;
import dao.IDAOItemSerieCarro;
import dao.IDAOMarcaCarro;
import dao.IDAOModeloCarro;
import dao.IDAOVersaoCarro;


public class ControladorCarro {
	
	private IDAOCarro carroDAO;
	private IDAOItemSerieCarro itemSerieCarroDAO;
	private IDAOVersaoCarro versaoCarroDAO;
	private IDAOModeloCarro modeloCarroDAO;
	private IDAOAcessorio acessorioDAO;
	private IDAOMarcaCarro marcaCarroDAO;
	
	public ControladorCarro(){
		super();
		this.itemSerieCarroDAO = new DAOItemSerieCarro();
		this.carroDAO = new DAOCarro();
		this.versaoCarroDAO = new DAOVersaoCarro();
		this.modeloCarroDAO = new DAOModeloCarro();
		this.acessorioDAO = new DAOAcessorio();
		this.marcaCarroDAO = new DAOMarcaCarro();
		
	}
	//Carro
	
	public void inserir(Carro carro) {
		carroDAO.inserir(carro);
	}
	public void alterar(Carro carro) {
		carroDAO.alterar(carro);
	}
	public void remover(Carro carro) {
		carroDAO.remover(carro);
	}
	public List<Carro> pesquisarCarroPorChassi(String chassi) {
		return carroDAO.pesquisarCarroPorChassi(chassi);
	}
	public List<Carro> listarCarros() {
		return carroDAO.consultarTodos();
	}
	
	public Carro pesquisarCarro(int codigo) {
		return carroDAO.consultarPorId(codigo);
	}
	
	public List<Carro> pesquisarCarros(Carro carro) {
		return carroDAO.pesquisar(carro);
	}

	
	//Modelo Carro
	public void inserir(ModeloCarro modeloCarro) {
		modeloCarroDAO.inserir(modeloCarro);
	}

	public void alterar(ModeloCarro modeloCarro) {
		modeloCarroDAO.alterar(modeloCarro);
	}

	public void remover(ModeloCarro modeloCarro) {
		modeloCarroDAO.remover(modeloCarro);
	}
	
	public List<ModeloCarro> listarModelos() {
		return modeloCarroDAO.consultarTodos();
	}
	

	public ModeloCarro pesquisarModeloCarro(int codigo) {
		return modeloCarroDAO.consultarPorId(codigo);
	}
	
	public List<ModeloCarro> pesquisarModeloCarros(ModeloCarro modeloCarro) {
		return modeloCarroDAO.pesquisar(modeloCarro);
	}
	
	//Versao de carro
	
	public void inserir(VersaoCarro versaoCarro) {
		versaoCarroDAO.inserir(versaoCarro);
	}
	public void alterar(VersaoCarro versaoCarro) {
		versaoCarroDAO.alterar(versaoCarro);
	}
	public void remover(VersaoCarro versaoCarro) {
		versaoCarroDAO.remover(versaoCarro);
	}
	public List<VersaoCarro> listarVersoes() {
		return versaoCarroDAO.consultarTodos();
	}
	
	public VersaoCarro pesquisarVersaoCodigo(int codigo){
		return versaoCarroDAO.consultarPorId(codigo);
	}
	
	public List<VersaoCarro> pesquisarVersoes(VersaoCarro versaoCarro) {
		return versaoCarroDAO.pesquisar(versaoCarro);
	}
	
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo){
		return versaoCarroDAO.listarItensPorModelo(modelo);
	}
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo){
		return versaoCarroDAO.listarAcessoriosPorModelo(modelo);
	}
	// Marca
	
	public void inserir(MarcaCarro marcaCarro) {
		marcaCarroDAO.inserir(marcaCarro);
		
	}
	
	public void removerMarca(MarcaCarro marcaCarro) {
		marcaCarroDAO.remover(marcaCarro);
	}

	public List<MarcaCarro> listarMarcas() {
		return marcaCarroDAO.consultarTodos();
	}


	public MarcaCarro pesquisarMarcaCodigo(int codigo) {
		return marcaCarroDAO.consultarPorId(codigo);
	}

	public List<MarcaCarro> pesquisarMarcas(MarcaCarro marcaCarro) {
		return marcaCarroDAO.pesquisar(marcaCarro);
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
	
	public List<ItemSerieCarro> listarItens(){
		return itemSerieCarroDAO.consultarTodos();
	}
	public ItemSerieCarro pesquisarItemCodigo(int codigo) {
		return itemSerieCarroDAO.consultarPorId(codigo);
	}
	
	public List<ItemSerieCarro> pesquisarItens(ItemSerieCarro itemSerieCarro){
		return itemSerieCarroDAO.pesquisar(itemSerieCarro);
	}
	
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo){
		return itemSerieCarroDAO.pesquisarPorModelo(codigo);
	}
	//Acessório Carro

	public void inserir(AcessorioCarro acessorioCarro) {
		acessorioDAO.inserir(acessorioCarro);
		
	}
	
	public void removerAcessorio(AcessorioCarro acessorio) {
		acessorioDAO.remover(acessorio);
	}

	public List<AcessorioCarro> listarAcessorios() {
		return acessorioDAO.consultarTodos();
	}

	public AcessorioCarro pesquisarAcessorioCarroCodigo(int codigo) {
		return acessorioDAO.consultarPorId(codigo);
	}

	public List<AcessorioCarro> pesquisarAcessorios(AcessorioCarro acessorio) {
		return acessorioDAO.pesquisar(acessorio);
	}

}
