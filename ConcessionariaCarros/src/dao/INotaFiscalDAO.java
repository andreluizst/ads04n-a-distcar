package dao;

import java.util.List;

import classesBasicas.NotaFiscal;

public interface INotaFiscalDAO<Entidade> extends IDAOGenerico<Entidade> {

		public List<NotaFiscal> pesquisarNotaFiscal(NotaFiscal nf);

}
