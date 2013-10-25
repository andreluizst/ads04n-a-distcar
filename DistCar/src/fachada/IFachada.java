package fachada;

import java.util.List;

import classesBasicas.Funcao;

public interface IFachada {
	public void salvarFuncao(Funcao funcao) throws Exception;
	public List<Funcao> listarFuncoes() throws Exception;
}
