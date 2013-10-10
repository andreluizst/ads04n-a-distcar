package negocio;

import java.util.List;

import classesBasicas.Gestor;
import dao.GestorDAO;
import dao.IGestorDAO;
import erro.NegocioExceptionGestor;


public class ControladorGestor {
	private IGestorDAO<Gestor> gestorDAO;
	
	public ControladorGestor() {
		// TODO Auto-generated constructor stub
			super();
		
		this.setGestorDAO ( new GestorDAO<Gestor>());
		}
	public void inserirGestor (Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inválidos");
				}
				
		gestorDAO.inserir(gestor);
	}
	
	public void alterarGestor (Gestor gestor) throws NegocioExceptionGestor {
		if(	gestor.getCodigo()==null||gestor.getCodigo().equals("")||
				gestor.getNome()==null||gestor.getNome().equals("")||
						gestor.getSituacao()==null||gestor.getSituacao().equals("")||
								gestor.getDataUltimaAtualizacao()==null||gestor.getDataUltimaAtualizacao().equals(""))
				{
					throw new NegocioExceptionGestor ("Campos inválidos");
				}
				
		Gestor g = gestorDAO.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor não cadastrado");
		}
		
		gestorDAO.inserir(g);
		}


	public void removerGestor(Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		Gestor g = gestorDAO.consultarPorId(gestor.getCodigo());
		if(g==null){
			throw new NegocioExceptionGestor("Gestor não encontrado!");
		}
		gestorDAO.remover(g);
	}

	public List<Gestor> pesquisarGestor(Gestor gestor) throws NegocioExceptionGestor {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IGestorDAO<Gestor> getGestorDAO() {
		return gestorDAO;
	}

	public void setGestorDAO(IGestorDAO<Gestor> gestorDAO) {
		this.gestorDAO = gestorDAO;
	}
	
		
	}
