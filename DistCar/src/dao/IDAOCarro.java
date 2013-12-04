package dao;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.Carro;
import classesBasicas.Fabricante;
import classesBasicas.MarcaCarro;
import classesBasicas.ModeloCarro;
import classesBasicas.VersaoCarro;


public interface IDAOCarro extends IDAOGenerico<Carro>{

	public List<Carro> pesquisarCarroPorChassi(String chassi);
	public List<Carro> pesquisarCarroPorModelo(ModeloCarro modeloCarro);
	public List<Carro> pesquisarCarroPorVersaoCarro(VersaoCarro versaoCarro);
	public List<Carro> pesquisarCarroPorItemSerie(String descricao);
	public List<Carro> pesquisarCarroPorAcessorioCarro(AcessorioCarro acessorioCarro);
	public List<Carro> emitirRelatorio();
	public Carro pegarCarroPeloChassi(String chassi) throws Exception;
	public List<Carro> consultar(Carro carro,Fabricante f, MarcaCarro m, ModeloCarro modelo) throws Exception;
	
}
