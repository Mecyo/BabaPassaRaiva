/**
 * 
 */
package controller;

import java.io.IOException;

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
@ManagedBean(name = "cadastrarUsuarioController")
@ViewScoped
public class CadastrarUsuarioController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario selectedUsuario;

	@PostConstruct
	public void init() {
		Uteis.verificarLogin();
		this.selectedUsuario = (Usuario) Uteis.pegarObjetoDaSessao("selectedUsuario");

		if (selectedUsuario == null) {
			selectedUsuario = new Usuario();
		}
	}

	public void salvar() {

		validarCampos(this.selectedUsuario);
		String msg = "Usuário cadastrado com sucesso!";

		if (this.selectedUsuario.getId() != 0) {
			msg = "Usuário alterado com sucesso!";
		}

		usuarioDAO.salvar(this.selectedUsuario);

		this.selectedUsuario = null;

		Uteis.MensagemInfo("Sucesso!", msg);
		Voltar();
	}

	private void validarCampos(Usuario user) {
		if (Uteis.isEmptyOrNull(user.getNome())) {
			Uteis.MensagemAtencao("Nome não informado!");
		}

		if (Uteis.isEmptyOrNull(user.getApelido())) {
			Uteis.MensagemAtencao("Apelido não informado!");
		}

		if (Uteis.isEmptyOrNull(user.getEmail())) {
			Uteis.MensagemAtencao("E-mail não informado!");
		}

		if (Uteis.isEmptyOrNull(user.getSenha())) {
			Uteis.MensagemAtencao("Senha não informada!");
		}
	}

	public void Voltar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarUsuarios.xhtml");
		} catch (IOException e) {
			Uteis.MensagemAtencao(e.getMessage());
		}
	}

	public Usuario getUsuario() {
		return selectedUsuario;
	}

	public void setUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}