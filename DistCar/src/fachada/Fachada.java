package fachada;

import java.util.List;

import negocio.ControladorFuncao;
import classesBasicas.Funcao;

public class Fachada implements IFachada {
	private static Fachada instancia;
	private ControladorFuncao ctrlFuncao;
	
	private Fachada(){
		ctrlFuncao = new ControladorFuncao();
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

}
