package dao;

import classesBasicas.Funcao;

public interface IFuncaoDAO {

	void inserirFuncao(Funcao funcao);

	Funcao pesquisarCodigoFuncao(Integer codigo);

	void removerFuncao(Integer codigo);

}
