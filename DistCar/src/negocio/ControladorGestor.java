package negocio;

import java.util.List;

import classesBasicas.Gestor;
import dao.DAOGestor;
import dao.IGestorDAO;
import erro.NegocioExceptionGestor;


public class ControladorGestor {
	private IGestorDAO gestorDAO;
	
	public ControladorGestor() {
		// TODO Auto-generated constructor stub
			super();
		
		this.setGestorDAO ( new DAOGestor());
		}
	public void inserirGestor (Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inv�lidos");
				}
				
		gestorDAO.inserir(gestor);
	}
	
	public void alterarGestor (Gestor gestor) throws NegocioExceptionGestor {
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inv�lidos");
				}
				
		Gestor g = gestorDAO.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor n�o cadastrado");
		}
		
		gestorDAO.inserir(g);
		}


	public void removerGestor(Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		Gestor g = gestorDAO.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor n�o encontrado!");
		}
		gestorDAO.remover(g);
	}

	public List<Gestor> pesquisarGestor(Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IGestorDAO getGestorDAO() {
		return gestorDAO;
	}

	public void setGestorDAO(IGestorDAO gestorDAO) {
		this.gestorDAO = gestorDAO;
	}
	
		
	}
