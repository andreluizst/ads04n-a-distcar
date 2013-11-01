package fachada;

import java.util.List;

import negocio.ControladorCarro;
import negocio.ControladorFuncao;
import classesBasicas.Carro;
import classesBasicas.Funcao;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;

public class Fachada implements IFachada {
	private static Fachada instancia;
	private ControladorFuncao ctrlFuncao;
	private ControladorCarro controladorCarro;
	
	public Fachada(){
		ctrlFuncao = new ControladorFuncao();
		this.controladorCarro = new ControladorCarro();
	}
	
	public static Fachada obterInstancia(){
		if (instancia == null)
			instancia = new Fachada();
		return instancia;
	}
	
	//***********************************************************************************
	//***********************  C R U D    Organizacional ********************************
	//***********************************************************************************
	
	//******************** Função ******************************
	@Override
	public void salvarFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		//ctrlFuncao
		ctrlFuncao.inserirFuncao(funcao);
	}
	
	@Override
	public void excluirFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		if (ctrlFuncao.funcaoExiste(funcao))
			ctrlFuncao.removerFuncao(funcao);
	}

	@Override
	public List<Funcao> listarFuncoes() throws Exception {
		// TODO Auto-generated method stub
		return ctrlFuncao.listarFuncoes();
	}
	
	@Override
	public List<Funcao> consultarFuncao(Funcao funcao) throws Exception{
		return ctrlFuncao.pesquisarFuncao(funcao);
	}
	
	
	//***********************************************************************************
	//**************** F I M   C R U D    Organizacional ********************************
	//***********************************************************************************

	//Carro - Felipe Carlos
	
	@Override
	public void salvarCarro(Carro carro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(carro);
	}

	@Override
	public void salvarItemSerie(ItemSerieCarro itemSerieCarro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(itemSerieCarro);
	}

	@Override
	public void salvarVersaoModeloCarro(VersaoCarro versaoCarro)
			throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(versaoCarro);
		
	}

	@Override
	public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(modeloCarro);
	}

	@Override
	public List<VersaoCarro> listarVersao() throws Exception {
		return this.controladorCarro.consultarTodasVersao();
	}
	
	public List<ModeloCarro> listarModelo() throws Exception {
		return this.controladorCarro.consultarTodosModelos();
	}

	@Override
	public VersaoCarro pesquisarVersao(int codigo) {
		return this.controladorCarro.pesquisarVersao(codigo);
	}

	@Override
	public ModeloCarro pesquisarModeloCarro(int codigo) {
		return this.controladorCarro.pesquisarModeloCarro(codigo);
	}

	@Override
	public List<ItemSerieCarro> listarItem() {
		// TODO Auto-generated method stub
		return this.controladorCarro.listarItem();
	}
	
	@Override
	public void excluirFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
