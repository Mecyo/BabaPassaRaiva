package utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import model.Usuario;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean
public class Uteis {

	/**
	 * Verifica se uma string é vazia ou nula
	 * 
	 * @param str - String a ser validada
	 */
	public static boolean isEmptyOrNull(String str) {
		return (str == null || str.isEmpty());
	}

	/**
	 * Exibe uma mensagem na tela com o texto informado
	 * 
	 * @param mensagem - String que será usada como texto da mensagem
	 */
	public static void Mensagem(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	/**
	 * Exibe uma mensagem na tela com o texto e título informados
	 * 
	 * @param titulo   - String que será usada como titulo da mensagem
	 * @param mensagem - String que será usada como texto da mensagem
	 */
	public static void Mensagem(String titulo, String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	/**
	 * Exibe uma mensagem do tipo de Severity determinado, com o texto e título
	 * informados.
	 * 
	 * @param titulo   - String que será usada como titulo da mensagem
	 * @param mensagem - String que será usada como texto da mensagem
	 * @param severity - Tipo de severidade que será usada como texto da
	 *                 mensagem(Info, Warn, Error, etc.)
	 */
	private static void Mensagem(Severity severity, String titulo, String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(severity, titulo, mensagem));
	}

	/**
	 * Exibe uma mensagem de atenção na tela com o texto informado
	 * 
	 * @param mensagem - String que será usada como texto da mensagem
	 */
	public static void MensagemAtencao(String mensagem) {

		Mensagem(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem);
	}

	/**
	 * Exibe uma mensagem de informação na tela com texto e título informados
	 * 
	 * @param titulo   - String que será usada como titulo da mensagem
	 * @param mensagem - String que será usada como texto da mensagem
	 */
	public static void MensagemInfo(String titulo, String mensagem) {

		Mensagem(FacesMessage.SEVERITY_INFO, titulo, mensagem);
	}

	public static void criarAviso(String txt) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, txt, txt);
		FacesContext.getCurrentInstance().addMessage(txt, msg);
	}

	public static void criarAvisoErro(String txt) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, txt, txt);
		FacesContext.getCurrentInstance().addMessage(txt, msg);
	}

	public static void executarJavaScript(String comando) {
		RequestContext.getCurrentInstance().execute(comando);
	}

	public static void criarObjetoDeSessao(Object obj, String nome) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().setAttribute(nome, obj);
	}

	public static Object pegarObjetoDaSessao(String nomeSessao) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getSession().getAttribute(nomeSessao);
	}

	public static void removerObjetoDaSessao(String nomeSessao) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().removeAttribute(nomeSessao);
	}

	public static void redirecionarPagina(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (IOException ex) {
			MensagemAtencao(ex.getMessage());
		}
	}

	public static String pegarApelido() {
		return ((Usuario) pegarObjetoDaSessao("usuario")).getApelido();
	}

	public static void verificarLogin() {
		Usuario userLogado = (Usuario) Uteis.pegarObjetoDaSessao("usuario");

		if (userLogado == null || userLogado.getId() == 0) {
			redirecionarPagina("login.xhtml");
		}
	}
}
