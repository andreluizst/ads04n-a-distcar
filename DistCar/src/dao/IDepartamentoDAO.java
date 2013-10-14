package dao;

import java.util.List;

import classesBasicas.Departamento;


public interface IDepartamentoDAO extends IDAOGenerico<Departamento> {

		public List<Departamento> pesquisarNomeDepartamento(Departamento nome);
	

}
