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
	
	public Carro pesquisarCarro(Integer codigo) {
		return carroDAO.consultarPorId(codigo);
	}
	
	public List<Carro> pesquisarCarros(Carro carro) {
		return carroDAO.pesquisar(carro);
	}

	
	//Modelo Carro
	public void inserir(ModeloCarro modelo) throws Exception {
		if(modeloCarroDAO.pesquisarModeloDescAno(modelo)!=null &&
				modeloCarroDAO.pesquisarModeloDescAno(modelo).getCodigo()!=modelo.getCodigo()){
			throw new Exception("Modelo já cadastrado");
		}
		modeloCarroDAO.inserir(modelo);
	}

	public void alterar(ModeloCarro modelo) {
		modeloCarroDAO.alterar(modelo);
	}

	public void remover(ModeloCarro modelo) throws Exception {
		if(modeloCarroDAO.consultarPorId(modelo.getCodigo())==null){
			throw new Exception("Modelo não encontrado");
		}
		modeloCarroDAO.remover(modelo);
	}
	
	public List<ModeloCarro> listarModelos() {
		return modeloCarroDAO.consultarTodos();
	}
	

	public ModeloCarro pesquisarModeloCarro(Integer codigo) {
		return modeloCarroDAO.consultarPorId(codigo);
	}
	
	public List<ModeloCarro> pesquisarModeloCarros(ModeloCarro modelo) throws Exception {
		return modeloCarroDAO.consultar(modelo);
	}
	
	public List<ModeloCarro> pesquisarModeloPorMarca(Integer codigo) {
		return modeloCarroDAO.pesquisarModeloPorMarca(codigo);
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
	
	public VersaoCarro pesquisarVersaoCodigo(Integer codigo){
		return versaoCarroDAO.consultarPorId(codigo);
	}
	
	public List<VersaoCarro> pesquisarVersoes(VersaoCarro versao) throws Exception {
		return versaoCarroDAO.consultar(versao);
	}
	
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo){
		return versaoCarroDAO.pesquisarVersaoPorModelo(codigo);
	}
	
	// Marca
	
	public void inserir(MarcaCarro marcaCarro) throws Exception {
		if(marcaCarroDAO.pesquisarMarcaDesc(marcaCarro)!=null &&
				marcaCarroDAO.pesquisarMarcaDesc(marcaCarro).getCodigo()!= marcaCarro.getCodigo()){
			throw new Exception("Marca já cadastrada");
		}
		marcaCarroDAO.inserir(marcaCarro);
		
	}
	
	public void removerMarca(MarcaCarro marcaCarro) throws Exception {
		if(marcaCarroDAO.pesquisarMarcaPorFab(marcaCarro.getCodigo())==null){
			throw new Exception("Marca não cadastrada");
		}
		marcaCarroDAO.remover(marcaCarro);
	}

	public List<MarcaCarro> listarMarcas() {
		return marcaCarroDAO.consultarTodos();
	}


	public MarcaCarro pesquisarMarcaCodigo(Integer codigo) {
		return marcaCarroDAO.consultarPorId(codigo);
	}

	public List<MarcaCarro> pesquisarMarcas(MarcaCarro marcaCarro) throws Exception {
		return marcaCarroDAO.consultar(marcaCarro);
	}
	
	public List<MarcaCarro> pesquisarMarcaPorFabr(Integer codigo ){
		return marcaCarroDAO.pesquisarMarcaPorFab(codigo);
	}
	
	//Item de Serie
	
	public void inserir(ItemSerieCarro itemSerieCarro) throws Exception {
		if(itemSerieCarroDAO.pesquisarItemDescModelo(itemSerieCarro)!=null &&
				itemSerieCarroDAO.pesquisarItemDescModelo(itemSerieCarro).getCodigo()!=itemSerieCarro.getCodigo()){
			throw new Exception("Item série cadastrado!");
		}
		else{
			itemSerieCarroDAO.inserir(itemSerieCarro);
		}	
	}
	
	public void alterar(ItemSerieCarro itemSerieCarro) {
		itemSerieCarroDAO.alterar(itemSerieCarro);
	}
	public void remover(ItemSerieCarro itemSerieCarro) throws Exception {
		if(itemSerieCarroDAO.consultarPorId(itemSerieCarro.getCodigo())==null){
			throw new Exception("Item de série não cadastrado!");
		}
		{
			itemSerieCarroDAO.remover(itemSerieCarro);
		}
	}
	
	public List<ItemSerieCarro> listarItens(){
		return itemSerieCarroDAO.consultarTodos();
	}
	public ItemSerieCarro pesquisarItemCodigo(Integer codigo) {
		return itemSerieCarroDAO.consultarPorId(codigo);
	}
	
	public List<ItemSerieCarro> pesquisarItens(ItemSerieCarro itemSerieCarro) throws Exception{
		return itemSerieCarroDAO.consultar(itemSerieCarro);
	}
	
	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo){
		return itemSerieCarroDAO.pesquisarPorModelo(codigo);
	}
	
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo){
		return itemSerieCarroDAO.listarItensPorModelo(modelo);
	}
	
	//Acessório Carro

	public void inserir(AcessorioCarro acessorioCarro) throws Exception {
		
		if(acessorioDAO.pesquisarAcessorioDescModelo(acessorioCarro)!=null && 
				acessorioDAO.pesquisarAcessorioDescModelo(acessorioCarro).getCodigo()!=acessorioCarro.getCodigo()){
			 throw new Exception("Acessório já cadastrado");
		}
		else{
			acessorioDAO.inserir(acessorioCarro);
		}
	}
	
	public void removerAcessorio(AcessorioCarro acessorio) throws Exception {
		if(acessorioDAO.consultarPorId(acessorio.getCodigo())==null){
			throw new Exception("Acessório não cadastrado.");
		}
		acessorioDAO.remover(acessorio);
	}

	public List<AcessorioCarro> listarAcessorios() {
		return acessorioDAO.consultarTodos();
	}

	public AcessorioCarro pesquisarAcessorioCarroCodigo(Integer codigo) {
		return acessorioDAO.consultarPorId(codigo);
	}

	public List<AcessorioCarro> pesquisarAcessorios(AcessorioCarro acessorio) throws Exception {
		return acessorioDAO.consultar(acessorio);
	}
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo){
		return acessorioDAO.listarAcessoriosPorModelo(modelo);
	}

}
