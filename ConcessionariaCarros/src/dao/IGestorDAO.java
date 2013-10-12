package dao;

import java.util.List;

import classesBasicas.Gestor;



public interface IGestorDAO<Entidade> extends IDAOGenerico<Entidade> {

	/*void inserirGestor(Gestor Gestor);
	Gestor pesquisarCodigoGestor(Integer codigo);
	void removerGestor(Integer codigo);*/
	public List<Gestor> pesquisarGestor(Gestor g);

}
