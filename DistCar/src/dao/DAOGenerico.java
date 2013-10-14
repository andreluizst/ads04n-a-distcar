package dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;



/**
 * PSC
 * 
 * @param <Entidade, PK>
 */
public abstract class DAOGenerico<Entidade> implements IDAOGenerico<Entidade>{

	protected EntityManager entityManager;
	protected Class<Entidade> classePersistente;
//	protected CriteriaBuilder criteriaBuilder;

	@SuppressWarnings("unchecked")
	public DAOGenerico(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DistCar");
		entityManager = emf.createEntityManager();
//		criteriaBuilder = entityManager.getCriteriaBuilder();
		
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
	    classePersistente = (Class<Entidade>) parameterizedType.getActualTypeArguments()[0];  
	}
	
	/**
	 * Executa o merge do objeto que se encontra em memória.
	 * 
	 * @param objeto
	 *            a ser realizado o merge
	 * @return objeto que foi executado o merge
	 */
	public void alterar(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			
			objeto = getEntityManager().merge(objeto);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()){
				tx.rollback();
			}
		}
	}

	/**
	 * Salva o objeto atual na base de dados.
	 * 
	 * @param objeto a ser salvo
	 */
	public void inserir(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();		
		try {
			tx.begin();
			getEntityManager().persist(objeto);
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()){
				tx.rollback();
			}
		}
	}

	/**
	 * Salva o objeto atual na base de dados.
	 * 
	 * @param objeto
	 *            a ser salvo
	 */
	public final void inserirColecao(Collection<Entidade> colecao) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();

			for (Entidade entidade : colecao) {
				getEntityManager().persist(entidade);	
			}
			
			tx.commit();
			
			System.out.println(classePersistente.getSimpleName() + " salvos com sucesso: " + colecao.size());
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()){
				tx.rollback();
			}
		}
	}

	/**
	 * Remove o objeto da base de dados.
	 * 
	 * @param objeto
	 *            a ser removido
	 */
	public void remover(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
	
			refresh(objeto);
			getEntityManager().remove(objeto);
			
			tx.commit();
			
			System.out.println(classePersistente.getSimpleName() + " removido com sucesso");
		} catch (Exception e){
			e.printStackTrace();
			if (tx != null && tx.isActive()){
				tx.rollback();
			}
		}
	}

	
	
	/**
	 * Busca o objeto uma vez passado sua chave como parâmetro.
	 * 
	 * @param chave
	 *            identificador
	 * @return Objeto do tipo T
	 */
	public final Entidade consultarPorId(Integer chave) {
		Entidade instance = null;
		try {
			instance = (Entidade) getEntityManager().find(classePersistente, chave);
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return instance;
	}

	public List<Entidade> consultarTodos() {
		try {
			String sql = "from " + classePersistente.getSimpleName();
			TypedQuery<Entidade> query = entityManager.createQuery(sql, classePersistente);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Atualiza o objeto que se encontra em memória.
	 * 
	 * @param object
	 *            objeto a ser atualizado
	 */
	public final void refresh(Entidade object) {
		getEntityManager().refresh(object);
	}
	
	/**
	 * Utilizado para se injetar o Entity manager no DAO.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

			
}
