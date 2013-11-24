package dao;



import java.util.List;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;


public interface IDAOItemSerieCarro extends IDAOGenerico<ItemSerieCarro> {

	public List<ItemSerieCarro> pesquisarPorModelo(Integer codigo);
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo);
	public ItemSerieCarro pesquisarItemDescModelo(ItemSerieCarro item);
	public List<ItemSerieCarro> consultar(ItemSerieCarro item) throws Exception;
}
