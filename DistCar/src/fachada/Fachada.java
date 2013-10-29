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
	
	private Fachada(){
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
		if (ctrlFuncao.funcaoExiste(funcao))
			ctrlFuncao.alterarFuncao(funcao);
		else
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
		
	}

	@Override
	public void salvarModeloCarro(ModeloCarro modeloCarro) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
