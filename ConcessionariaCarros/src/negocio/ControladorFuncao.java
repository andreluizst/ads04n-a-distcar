package negocio;

import java.util.List;

import classesBasicas.Funcao;
import dao.FuncaoDAO;
import dao.IFuncaoDAO;
import erro.NegocioExceptionFuncao;

public class ControladorFuncao {
	private IFuncaoDAO funcaoDAO;
	
	public ControladorFuncao() {
		// TODO Auto-generated constructor stub
			super();
		
		this.setFuncaoDAO( new FuncaoDAO());
		}

	public void inserirFuncao (Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		if(	funcao.getCodigo()==null||funcao.getCodigo().equals("")||
				funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null||funcao.getSituacao().equals("")||
							funcao.getDataUltimaAtualizacao()==null||funcao.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncao("Campos inválidos");
				}
				
		funcaoDAO.inserirFuncao(funcao);
	}
	
	public void alterarFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		if(	funcao.getCodigo()==null||funcao.getCodigo().equals("")||
				funcao.getDescricao()==null||funcao.getDescricao().equals("")||
						funcao.getSituacao()==null||funcao.getSituacao().equals("")||
							funcao.getDataUltimaAtualizacao()==null||funcao.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionFuncao("Campos inválidos");
				}
				
		Funcao f = funcaoDAO.pesquisarCodigoFuncao(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Funcao não cadastrada");
		}
		
		funcaoDAO.inserirFuncao(f);
			
	}
	
	
	public void removerFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		Funcao f = funcaoDAO.pesquisarCodigoFuncao(funcao.getCodigo());
		if(f==null){
			throw new NegocioExceptionFuncao("Departamento não encontrado!");
		}
		funcaoDAO.removerFuncao(f.getCodigo());
	}

	public List<Funcao> pesquisarFuncao(Funcao funcao) throws NegocioExceptionFuncao {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public IFuncaoDAO getFuncaoDAO() {
		return funcaoDAO;
	}

	public void setFuncaoDAO(IFuncaoDAO funcaoDAO) {
		this.funcaoDAO = funcaoDAO;
	}

}
