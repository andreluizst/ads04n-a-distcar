package negocio;

import java.util.List;

import classesBasicas.Departamento;
import dao.DepartamentoDAO;
import dao.IDepartamentoDAO;
import erro.NegocioExceptionDepartamento;

public class ControladorDepartamento {
	private IDepartamentoDAO<Departamento> departamentoDAO;

	public ControladorDepartamento() {
		// TODO Auto-generated constructor stub
		super();
		
		this.setDepartamentoDAO(new DepartamentoDAO<Departamento>());	
		}

	public void inserirDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		if(		departamento.getCodigo()==null||departamento.getCodigo().equals("")||
					departamento.getNome()==null||departamento.getNome().equals("")||
						departamento.getSituacao()==null||departamento.getSituacao().equals("")||
							departamento.getDepartamentoSuperior()==null||departamento.getDepartamentoSuperior().equals("")||
								departamento.getGestor()==null||departamento.getGestor().equals("")||
									departamento.getDataUltimaAtualizacao()==null||departamento.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionDepartamento("Campos inválidos");
				}
				
		departamentoDAO.inserir(departamento);
	}
	
	public void alterarDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		if(	departamento.getCodigo()==null||departamento.getCodigo().equals("")||
				departamento.getNome()==null||departamento.getNome().equals("")||
					departamento.getSituacao()==null||departamento.getSituacao().equals("")||
						departamento.getDepartamentoSuperior()==null||departamento.getDepartamentoSuperior().equals("")||
							departamento.getGestor()==null||departamento.getGestor().equals("")||
								departamento.getDataUltimaAtualizacao()==null||departamento.getDataUltimaAtualizacao().equals(""))
			{
				throw new NegocioExceptionDepartamento("Campos inválidos");
			}
							
	
		Departamento d = departamentoDAO.pesquisarNomeDepartamento(departamento.getNome());
		if(d==null){
			throw new NegocioExceptionDepartamento("Departamento não cadastrado");
		}
		
		departamentoDAO.inserir(d);
	}
	
	public void removerDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		Departamento d = departamentoDAO.pesquisarNomeDepartamento(departamento.getNome());
		if(d==null){
			throw new NegocioExceptionDepartamento("Departamento não encontrado!");
		}
		departamentoDAO.remover(d);
	}

	public List<Departamento> pesquisarDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IDepartamentoDAO<Departamento> getDepartamentoDAO() {
		return departamentoDAO;
	}

	public void setDepartamentoDAO(IDepartamentoDAO<Departamento> departamentoDAO) {
		this.departamentoDAO = departamentoDAO;
	}
	

}
