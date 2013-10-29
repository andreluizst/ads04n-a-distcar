package fachada;

import java.util.List;

import negocio.ControladorCarro;
import negocio.ControladorFuncao;
import classesBasicas.Carro;
import classesBasicas.Funcao;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;

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

	@Override
	public void salvarFuncao(Funcao funcao) throws Exception {
		// TODO Auto-generated method stub
		//ctrlFuncao
		ctrlFuncao.inserirFuncao(funcao);
	}

	@Override
	public List<Funcao> listarFuncoes() throws Exception {
		// TODO Auto-generated method stub
		return ctrlFuncao.listarFuncoes();
	}

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
	public void salvarVersaoModeloCarro(VersaoModeloCarro versaoModeloCarro)
			throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(versaoModeloCarro);
		
	}

	@Override
	public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception {
		// TODO Auto-generated method stub
		this.controladorCarro.inserir(modeloCarro);
	}

	@Override
	public List<VersaoModeloCarro> listarVersao() throws Exception {
		return this.controladorCarro.consultarTodasVersao();
	}
	
	public List<ModeloCarro> listarModelo() throws Exception {
		return this.controladorCarro.consultarTodosModelos();
	}

	@Override
	public VersaoModeloCarro pesquisarVersao(int codigo) {
		return this.controladorCarro.pesquisarVersao(codigo);
	}

	@Override
	public ModeloCarro pesquisarModeloCarro(int codigo) {
		return this.controladorCarro.pesquisarModeloCarro(codigo);
	}

	
}
