package dao;

import java.util.List;

import classesBasicas.Gestor;



public interface IDAOGestor extends IDAOGenerico<Gestor> {

	/*void inserirGestor(Gestor Gestor);
	Gestor pesquisarCodigoGestor(Integer codigo);
	void removerGestor(Integer codigo);*/
	public List<Gestor> pesquisarGestor(Gestor g);
	public void tornarFuncionarioEmGestor(Gestor gestor) throws Exception;

}
