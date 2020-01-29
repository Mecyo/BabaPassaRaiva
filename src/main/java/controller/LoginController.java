/**
 * 
 */
package controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.UsuarioDAO;
import model.Usuario;
import utils.Uteis;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario;

	@PostConstruct
	public void init() {
		if (usuario == null) {
			usuario = new Usuario();
			
			Uteis.removerObjetoDaSessao("usuario");
		}
	}

	public String login() {
		Usuario user = usuarioDAO.logar(usuario.getEmail(), usuario.getSenha());
		
		if (user == null) {
			String mensagem = "Usuário não encontrado!";
			boolean existe = usuarioDAO.existe(usuario.getEmail());
			if (existe)
				mensagem = "Senha incorreta!";
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro no Login!"));
			return null;
		} else {
			Uteis.criarObjetoDeSessao(user, "usuario");
			
			return "home.xhtml";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}
