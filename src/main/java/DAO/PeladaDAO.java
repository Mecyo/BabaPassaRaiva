/**
 * 
 */
package DAO;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import interfaces.ImplementDAO;
import model.Pelada;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "peladaDAO")
@SessionScoped
public class PeladaDAO implements ImplementDAO<Pelada> {

	private EntityManagerFactory factory;
	private EntityManager em;

	public PeladaDAO() {
		factory = Persistence.createEntityManagerFactory("postgresPU");
		em = factory.createEntityManager();
	}

	public Pelada buscarPorId(int id) {
		return em.find(Pelada.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Pelada> listar() {
		return em.createQuery("SELECT u FROM Pelada u").getResultList();
	}

	public boolean salvar(Pelada pelada) {
		try {
			em.getTransaction().begin();    
					
			if (pelada.getId() != 0)
				em.merge(pelada);
			else
				em.persist(pelada);
			
			em.getTransaction().commit(); 
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public boolean deletar(Pelada pelada) {
		try {
			em.remove(pelada);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean existe(Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	/*public Usuario logar(String email, String senha) {
		try {
			Pelada usuario = (Pelada) em
					.createQuery("SELECT u from Pelada u where u.email = :email and u.senha = :senha")
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
