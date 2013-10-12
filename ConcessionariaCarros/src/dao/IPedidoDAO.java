package dao;

import java.util.List;
import classesBasicas.Pedido;


public interface IPedidoDAO<Entidade> extends IDAOGenerico<Entidade> {

	public List<Pedido> pesquisarPedido(Pedido p);
	
}
