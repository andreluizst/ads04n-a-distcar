package dao;



import java.util.List;

import classesBasicas.ItemSerieCarro;


public interface IDAOItemSerieCarro extends IDAOGenerico<ItemSerieCarro> {

	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo);
	
}
