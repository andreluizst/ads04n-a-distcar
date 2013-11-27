package dao;

import java.util.List;
import classesBasicas.VersaoCarro;

public interface IDAOVersaoCarro extends IDAOGenerico<VersaoCarro> {
	
	public List<VersaoCarro> pesquisarVersaoPorModelo(Integer codigo);
	public List<VersaoCarro> consultar(VersaoCarro versao) throws Exception;
	public VersaoCarro pesquisarVersaoDesc(VersaoCarro versao);
}
