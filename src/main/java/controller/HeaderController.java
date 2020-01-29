/**
 * 
 */
package controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import model.Usuario;
import utils.Uteis;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "headerController")
@SessionScoped
public class HeaderController {

	private Usuario usuario;
	private String apelido = "";
	
	@PostConstruct
	public void init() {
		usuario = (Usuario)Uteis.pegarObjetoDaSessao("usuario");
		
		if (usuario != null && usuario.getId() != 0) {
			apelido = usuario.getApelido();
		}
	}
	
	public void navegar(String url) {
		Uteis.redirecionarPagina(url);
	}
	
	public void logout() {
		usuario = new Usuario();
		
		Uteis.removerObjetoDaSessao("usuario");
		
		Uteis.redirecionarPagina("login.xhtml");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
}
