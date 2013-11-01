package negocio;

import java.util.Calendar;
import java.util.List;

import classesBasicas.Funcao;
import classesBasicas.Situacao;
import dao.DAOFuncao;
import dao.IDAOFuncao;
import erro.NegocioExceptionFuncao;

public class ControladorFuncao {
	private IDAOFuncao funcaoDAO;
	
	public ControladorFuncao() {
		// TODO Auto-generated constructor stub
		super();
		funcaoDAO = new DAOFuncao();
	}
	
	public boolean funcaoExiste(Funcao funcao){
		Funcao obj = null;
		if (funcao.getCodigo() == null)
			return false;
		obj = funcaoDAO.consultarPorId(funcao.getCodigo());
		if (obj != null)
			if (obj.getCodigo() == funcao.getCodigo())
				return true;
		return false;
	}

	public void inserirFuncao (Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		if(funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null ||
							funcao.getDataUltimaAtualizacao()==null)
				{
					throw new NegocioExceptionFuncao("Campos inv�lidos");
				}
		
		funcao.setDataUltimaAtualizacao(Calendar.getInstance());
		if (funcao.getSituacao() == null)
			funcao.setSituacao(Situacao.ATIVO);
		
		funcaoDAO.inserir(funcao);
	}
	
	public void alterarFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		if(	funcao.getCodigo()==null||funcao.getCodigo().equals("")||
				funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null||funcao.getSituacao().equals("")||
							funcao.getDataUltimaAtualizacao()==null||funcao.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncao("Campos inv�lidos");
				}
				
		Funcao f = funcaoDAO.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Funcao n�o cadastrada");
		}
		
		funcaoDAO.inserir(f);
	}
	
	
	public void removerFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		Funcao f = funcaoDAO.consultarPorId(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Departamento n�o encontrado!");
		}
		funcaoDAO.remover(f);
	}

	public List<Funcao> pesquisarFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public IDAOFuncao getFuncaoDAO() {
		return funcaoDAO;
	}

	public void setFuncaoDAO(IDAOFuncao funcaoDAO) {
		this.funcaoDAO = funcaoDAO;
	}
	
	public List<Funcao> listarFuncoes() throws NegocioExceptionFuncao{
		return funcaoDAO.consultarTodos();
	}

}
