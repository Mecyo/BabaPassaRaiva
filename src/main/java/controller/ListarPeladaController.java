/**
 * 
 */
package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import DAO.PeladaDAO;
import DAO.UsuarioDAO;
import model.Pelada;
import model.Usuario;
import utils.Uteis;

/**
 * @author Emerson Santos
 *
 */
	@ManagedBean(name = "listarPeladaController")
	@ViewScoped
	public class ListarPeladaController {
		private PeladaDAO peladaDAO = new PeladaDAO();
		private UsuarioDAO usuarioDAO = new UsuarioDAO();
		private List<Pelada> peladas;
		private List<Usuario> jogadoresDisponiveis;
		private Pelada selectedPelada;

		@PostConstruct
		public void init() {
			Uteis.verificarLogin();
			if (peladas == null) {
				peladas = peladaDAO.listar();
			}
		}

	    public void cancelarConvite(Usuario user) {
	        selectedPelada.getConvidados().remove(user);
	        
	        peladaDAO.salvar(selectedPelada);
	    }
	    
	    public void Jogadores(Pelada pelada) {
	        List<Usuario> users = pelada.getJogadores();
	        
	        RequestContext.getCurrentInstance().execute("openModal('modalJogadores');");
	    }
	    
	    public void Convidados(Pelada pelada) {
	    	List<Usuario> users = pelada.getConvidados();
	    	
	    	RequestContext.getCurrentInstance().execute("openModal('modalConvidados');");
	    }
	    
	    public void Convidar(Pelada pelada) {
	    	jogadoresDisponiveis = usuarioDAO.listarNaoVinculados(pelada);
	        
	        RequestContext.getCurrentInstance().execute("openModal('modalConvidar');");
	    }
	    
	    public void ConfirmarConvite(Usuario user) {
	    	selectedPelada.getConvidados().add(user);
	    	peladaDAO.salvar(selectedPelada);
	    }
	    
	    public boolean exibirVincular(Pelada pelada) {
	    	Usuario usuario = (Usuario)Uteis.pegarObjetoDaSessao("usuario");
	    	for (Usuario user : pelada.getJogadores()) {
				if(usuario.getId() == user.getId())
					return false;
			}
	    	
	    	return true;
	    }
	    
		public void Editar(Usuario user) {
			peladaDAO.deletar(selectedPelada);
		}
		
		public void removerDoEvento(Usuario user) {
			peladaDAO.deletar(selectedPelada);
		}
		
		public void Vincular() {
			selectedPelada.getJogadores().add((Usuario)Uteis.pegarObjetoDaSessao("usuario"));
			peladaDAO.salvar(selectedPelada);
			Uteis.criarAviso("Vinculado com sucesso!");
		}

		public void Cadastrar() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarPelada.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void Voltar() {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public List<Pelada> getPeladas() {
			return peladas;
		}

		public void setPeladas(List<Pelada> peladas) {
			this.peladas = peladas;
		}

		public PeladaDAO getPeladaDAO() {
			return peladaDAO;
		}

		public void setPeladaDAO(PeladaDAO peladaDAO) {
			this.peladaDAO = peladaDAO;
		}

		public Pelada getSelectedPelada() {
			return selectedPelada;
		}

		public void setSelectedPelada(Pelada selectedPelada) {
			this.selectedPelada = selectedPelada;
		}

		public UsuarioDAO getUsuarioDAO() {
			return usuarioDAO;
		}

		public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
			this.usuarioDAO = usuarioDAO;
		}

		public List<Usuario> getJogadoresDisponiveis() {
			return jogadoresDisponiveis;
		}

		public void setJogadoresDisponiveis(List<Usuario> jogadoresDisponiveis) {
			this.jogadoresDisponiveis = jogadoresDisponiveis;
		}
}
