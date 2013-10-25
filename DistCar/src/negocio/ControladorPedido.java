package negocio;


import java.util.List;
import classesBasicas.Pedido;
import dao.IPedidoDAO;
import dao.DAOPedido;
import erro.NegocioExceptionPedido;


public class ControladorPedido {
	
private IPedidoDAO pedidoDAO;
	
public ControladorPedido() {

	super();
		
	this.pedidoDAO = new DAOPedido();	
	}

public void inserirPedido(Pedido pedido) throws NegocioExceptionPedido {
	// TODO Auto-generated method stub
	if(		pedido.getCodigo()==null||pedido.getCodigo().equals("")||
			pedido.getNumeroPedido()==null||pedido.getNumeroPedido().equals("")||
			pedido.getDataCompra()==null||pedido.getDataCompra().equals("")||
			pedido.getDataUltimaAtualizacao()==null||pedido.getDataUltimaAtualizacao().equals(""))
			{
				throw new NegocioExceptionPedido("Campos inválidos");
			}
			
	pedidoDAO.inserir(pedido);
}

public void alterarPedido(Pedido pedido) throws NegocioExceptionPedido {
	// TODO Auto-generated method stub
	if(		pedido.getCodigo()==null||pedido.getCodigo().equals("")||
			pedido.getNumeroPedido()==null||pedido.getNumeroPedido().equals("")||
			pedido.getDataCompra()==null||pedido.getDataCompra().equals("")||
			pedido.getDataUltimaAtualizacao()==null||pedido.getDataUltimaAtualizacao().equals(""))
			{
				throw new NegocioExceptionPedido("Campos inválidos");
			}
			
	Pedido p = pedidoDAO.consultarPorId(pedido.getCodigo());
	if(p==null){
		throw new NegocioExceptionPedido("Pedido não cadastrado");
	}
	
	pedidoDAO.inserir(p);

}

public void removerPedido(Pedido pedido) throws NegocioExceptionPedido {
	// TODO Auto-generated method stub
	Pedido p = pedidoDAO.consultarPorId(pedido.getCodigo());
	if(p==null){
		throw new NegocioExceptionPedido("Pedido não encontrado!");
	}
	pedidoDAO.remover(p);
}

public List<Pedido> pesquisarPedido(Pedido pedido) throws NegocioExceptionPedido {
	// TODO Auto-generated method stub
	return null;
}

}