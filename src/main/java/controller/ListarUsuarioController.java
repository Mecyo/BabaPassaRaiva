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

import DAO.UsuarioDAO;
import model.Usuario;
import utils.Uteis;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "listarUsuarioController")
@ViewScoped
public class ListarUsuarioController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> usuarios;
	private Usuario selectedUsuario;

	@PostConstruct
	public void init() {
		Uteis.verificarLogin();
		if (usuarios == null) {
			usuarios = usuarioDAO.listar();
		}
	}

	public String Editar(Usuario selectedUsuario) {
		if(selectedUsuario != null){
			Uteis.criarObjetoDeSessao(selectedUsuario, "selectedUsuario");
		}
		return "cadastrarUsuario.xhtml";
	}

	public void Excluir() {
		usuarioDAO.deletar(selectedUsuario);
	}

	public void Cadastrar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarUsuario.xhtml");
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
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Usuario getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}
}
