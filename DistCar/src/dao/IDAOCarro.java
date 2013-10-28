package dao;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoModeloCarro;


public interface IDAOCarro extends IDAOGenerico<Carro>{

	public List<Carro> pesquisarCarroPorChassi(String chassi);
	public List<Carro> pesquisarCarroPorModelo(ModeloCarro modeloCarro);
	public List<Carro> pesquisarCarroPorVersaoCarro(VersaoModeloCarro versaoModeloCarro);
	public List<Carro> pesquisarCarroPorItemSerie(String descricao);
	public List<Carro> pesquisarCarroPorAcessorioCarro(AcessorioCarro acessorioCarro);
	public List<Carro> emitirRelatorio();
	
}
