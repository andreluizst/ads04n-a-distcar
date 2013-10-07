package negocio;

import java.util.List;

import classesBasicas.NotaFiscal;
import dao.INotaFiscalDAO;
import dao.NotaFiscalDAO;
import erro.NegocioExceptionNotaFiscal;


public class ControladorNotaFiscal {
	
	private INotaFiscalDAO notaFiscalDAO;
	
	public ControladorNotaFiscal() {
		super();
		
		this.notaFiscalDAO = new NotaFiscalDAO();	
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
				
		notaFiscalDAO.inserirNotaFiscal(notaFiscal);
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
		NotaFiscal nf = notaFiscalDAO.pesquisarNumeroNotaFiscal(notaFiscal.getNumeroNotaFiscal());
		
		if(nf==null){
			throw new NegocioExceptionNotaFiscal("Nota Fiscal não cadastrada");
		}
		
		notaFiscalDAO.inserirNotaFiscal(nf);
	}
	
	public void removerNotaFiscal(NotaFiscal notaFiscal) throws NegocioExceptionNotaFiscal {
		// TODO Auto-generated method stub
		NotaFiscal nf = notaFiscalDAO.pesquisarNumeroNotaFiscal(notaFiscal.getNumeroNotaFiscal());
		if(nf==null){
			throw new NegocioExceptionNotaFiscal("Nota Fiscal não cadastrada");
		}
		notaFiscalDAO.removerNotaFiscal(nf.getNumeroNotaFiscal());
	}
	
	public List<NotaFiscal> pesquisarNotaFiscal(NotaFiscal notaFiscal) throws NegocioExceptionNotaFiscal {
		// TODO Auto-generated method stub
		return null;
	}

	
	}


