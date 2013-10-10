package dao;

import classesBasicas.Departamento;

public interface IDepartamentoDAO<Entidade> extends IDAOGenerico<Entidade> {

	/*void inserirDepartamento(Departamento departamento);
	void removerDepartamento(Integer codigo);
	Departamento pesquisarNomeDepartamento(String nome);*/
	
	public Departamento pesquisarNomeDepartamento(String nome);

}
