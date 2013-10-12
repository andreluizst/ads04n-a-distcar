package dao;

import java.util.List;
import classesBasicas.Funcao;


public interface IFuncaoDAO<Entidade> extends IDAOGenerico<Entidade> {

	public List<Funcao> pesquisarFuncao(Funcao f);
	
}
