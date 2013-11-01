package negocio;


import java.util.List;

import classesBasicas.NotaFiscal;
import classesBasicas.Pedido;
import dao.DAONotaFiscal;
import dao.INotaFiscalDAO;
import dao.IPedidoDAO;
import dao.DAOPedido;
import erro.NegocioExceptionNotaFiscal;
import erro.NegocioExceptionPedido;


public class ControladorMovimentacao {
	
private IPedidoDAO pedidoDAO;
private INotaFiscalDAO notaFiscalDAO;

public ControladorMovimentacao() {

	super();
		
	this.pedidoDAO = new DAOPedido();	
	this.notaFiscalDAO = new DAONotaFiscal();	
	
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

public void inserirNotaFiscal(NotaFiscal notaFiscal) throws NegocioExceptionNotaFiscal {
	// TODO Auto-generated method stub
	if(		notaFiscal.getCodigo()==null||notaFiscal.getCodigo().equals("")||
			notaFiscal.getNumeroPedido()==null||notaFiscal.getNumeroPedido().equals("")||
			notaFiscal.getDataCompra()==null||notaFiscal.getDataCompra().equals("")||
			notaFiscal.getDataUltimaAtualizacao()==null||notaFiscal.getDataUltimaAtualizacao().equals(""))
			{
				throw new NegocioExceptionNotaFiscal("Campos inválidos");
			}
			
	notaFiscalDAO.inserir(notaFiscal);
}

public void alterarNotaFiscal(NotaFiscal notaFiscal) throws NegocioExceptionNotaFiscal {
	// TODO Auto-generated method stub
	if(		notaFiscal.getCodigo()==null||notaFiscal.getCodigo().equals("")||
			notaFiscal.getNumeroPedido()==null||notaFiscal.getNumeroPedido().equals("")||
			notaFiscal.getDataCompra()==null||notaFiscal.getDataCompra().equals("")||
			notaFiscal.getDataUltimaAtualizacao()==null||notaFiscal.getDataUltimaAtualizacao().equals(""))
			{
				throw new NegocioExceptionNotaFiscal("Campos inválidos");
			}
	/*NotaFiscal nf = notaFiscalDAO.pesquisarNumeroNotaFiscal(notaFiscal.getNumeroNotaFiscal());
	
	if(nf==null){
		throw new NegocioExceptionNotaFiscal("Nota Fiscal não cadastrada");
	}
	
	notaFiscalDAO.inserir(nf);*/
}

public void removerNotaFiscal(NotaFiscal notaFiscal) throws NegocioExceptionNotaFiscal {
	// TODO Auto-generated method stub
	/*NotaFiscal nf = notaFiscalDAO.pesquisarNumeroNotaFiscal(notaFiscal.getNumeroNotaFiscal());
	if(nf==null){
		throw new NegocioExceptionNotaFiscal("Nota Fiscal não cadastrada");
	}
	notaFiscalDAO.remover(nf);*/
}

public List<NotaFiscal> pesquisarNotaFiscal(NotaFiscal notaFiscal) throws NegocioExceptionNotaFiscal {
	// TODO Auto-generated method stub
	return null;
}

}