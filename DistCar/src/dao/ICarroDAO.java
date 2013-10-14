package dao;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ItemSerieCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;


public interface ICarroDAO extends IDAOGenerico<Carro>{

	public List<Carro> pesquisarCarroPorChassi(String chassi);
	public List<Carro> pesquisarCarroPorModelo(ModeloCarro modeloCarro);
	public List<Carro> pesquisarCarroPorVersaoCarro(VersaoModeloCarro versaoModeloCarro);
	public List<Carro> pesquisarCarroPorItemSerie(ItemSerieCarro itemSerieCarro);
	public List<Carro> pesquisarCarroPorAcessorioCarro(AcessorioCarro acessorioCarro);
	public List<Carro> emitirRelatorio();
	
}
