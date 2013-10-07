package negocio;

import java.util.List;

import classesBasicas.Departamento;
import dao.DepartamentoDAO;
import dao.IDepartamentoDAO;
import erro.NegocioExceptionDepartamento;

public class ControladorDepartamento {
	private IDepartamentoDAO departamentoDAO;

	public ControladorDepartamento() {
		// TODO Auto-generated constructor stub
		super();
		
		this.setDepartamentoDAO(new DepartamentoDAO());	
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
				
		departamentoDAO.inserirDepartamento(departamento);
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
		
		departamentoDAO.inserirDepartamento(d);
	}
	
	public void removerDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		Departamento d = departamentoDAO.pesquisarNomeDepartamento(departamento.getNome());
		if(d==null){
			throw new NegocioExceptionDepartamento("Departamento não encontrado!");
		}
		departamentoDAO.removerDepartamento(d.getCodigo());
	}

	public List<Departamento> pesquisarDepartamento(Departamento departamento) throws NegocioExceptionDepartamento {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IDepartamentoDAO getDepartamentoDAO() {
		return departamentoDAO;
	}

	public void setDepartamentoDAO(IDepartamentoDAO departamentoDAO) {
		this.departamentoDAO = departamentoDAO;
	}
	

}
