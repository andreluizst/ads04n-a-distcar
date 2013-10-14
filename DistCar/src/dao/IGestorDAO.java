package dao;

import java.util.List;

import classesBasicas.Gestor;



public interface IGestorDAO extends IDAOGenerico<Gestor> {

	/*void inserirGestor(Gestor Gestor);
	Gestor pesquisarCodigoGestor(Integer codigo);
	void removerGestor(Integer codigo);*/
	public List<Gestor> pesquisarGestor(Gestor g);

}
