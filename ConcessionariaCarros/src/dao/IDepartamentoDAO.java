package dao;

import classesBasicas.Departamento;

public interface IDepartamentoDAO {

	void inserirDepartamento(Departamento departamento);

	void removerDepartamento(Integer codigo);

	Departamento pesquisarNomeDepartamento(String nome);

}
