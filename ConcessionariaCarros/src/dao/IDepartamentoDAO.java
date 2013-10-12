package dao;

import java.util.List;

import classesBasicas.Departamento;


public interface IDepartamentoDAO<Entidade> extends IDAOGenerico<Entidade> {

		public List<Departamento> pesquisarNomeDepartamento(Departamento nome);
	

}
