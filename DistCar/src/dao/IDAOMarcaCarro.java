package dao;

import java.util.List;


import classesBasicas.MarcaCarro;

public interface IDAOMarcaCarro extends IDAOGenerico<MarcaCarro> {
	
	public List<MarcaCarro> pesquisarMarcaPorFab(Integer codigo);

}
