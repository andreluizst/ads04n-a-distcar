package dao;

import classesBasicas.Pedido;

public interface IPedidoDAO {
	//public IPedidoDAO() {
	public void inserirPedido(Pedido pedido);

	public void removerPedido(Integer codigo);

	public Pedido pesquisarNumeroPedido(Integer codigo);
	
}
