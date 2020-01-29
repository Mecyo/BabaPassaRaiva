/**
 * 
 */
package DAO;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import interfaces.ImplementDAO;
import model.Pelada;
import model.Usuario;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "usuarioDAO")
@SessionScoped
public class UsuarioDAO implements ImplementDAO<Usuario> {

	private EntityManagerFactory factory;
	private EntityManager em;

	public UsuarioDAO() {
		factory = Persistence.createEntityManagerFactory("postgresPU");
		em = factory.createEntityManager();
	}

	public Usuario buscarPorId(int id) {
		return em.find(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		return em.createQuery("SELECT u FROM Usuario u").getResultList();
	}

	public boolean salvar(Usuario usuario) {
		try {
			em.getTransaction().begin();

			if (usuario.getId() != 0)
				em.merge(usuario);
			else
				em.persist(usuario);

			em.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public boolean deletar(Usuario usuario) {
		try {
			em.remove(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Usuario logar(String email, String senha) {
		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.email = :email and u.senha = :senha")
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> listarNaoVinculados(Pelada pelada) {
		try {
			StringBuilder str = new StringBuilder("SELECT u from Usuario u ");
			str.append("WHERE u.id not in ( ");
			str.append("SELECT usuario_id FROM pelada_usuario ");
			str.append("UNION ALL ");
			str.append("SELECT usuario_id FROM pelada_usuario_convidados)");
			
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) em.createQuery(str.toString()).getResultList();

			return usuarios;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean existe(Object email) {
		try {
			Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.email = :email")
					.setParameter("email", email).getSingleResult();

			return usuario != null;
		} catch (NoResultException e) {
			e.printStackTrace();
			return false;
		}
	}
}
