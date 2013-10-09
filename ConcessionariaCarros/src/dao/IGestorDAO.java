package dao;

import classesBasicas.Gestor;

public interface IGestorDAO {

	void inserirGestor(Gestor Gestor);

	Gestor pesquisarCodigoGestor(Integer codigo);

	void removerGestor(Integer codigo);

}
