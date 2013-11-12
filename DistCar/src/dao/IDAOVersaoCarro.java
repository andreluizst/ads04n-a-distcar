package dao;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;

public interface IDAOVersaoCarro extends IDAOGenerico<VersaoCarro> {
	
	public List<ItemSerieCarro> listarItensPorModelo(ModeloCarro modelo);
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo);

}
