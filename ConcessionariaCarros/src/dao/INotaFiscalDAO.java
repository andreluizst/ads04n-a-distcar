package dao;

import classesBasicas.NotaFiscal;

public interface INotaFiscalDAO<Entidade> extends IDAOGenerico<Entidade> {

	public NotaFiscal pesquisarNumeroNotaFiscal(String numeroNotaFiscal);

}
