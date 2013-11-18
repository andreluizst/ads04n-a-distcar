package dao;

import java.util.List;

import classesBasicas.AcessorioCarro;
import classesBasicas.ModeloCarro;

public interface IDAOAcessorio extends IDAOGenerico<AcessorioCarro> {
	
	public List<AcessorioCarro> listarAcessoriosPorModelo(ModeloCarro modelo);

}
