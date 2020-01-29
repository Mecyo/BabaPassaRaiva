/**
 * 
 */
package controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.PeladaDAO;
import model.Pelada;
import model.Usuario;
import utils.Uteis;

/**
 * @author Emerson Santos
 *
 */
@ManagedBean(name = "cadastrarPeladaController")
@ViewScoped
public class CadastrarPeladaController {
	private PeladaDAO peladaDAO = new PeladaDAO();
	private Pelada selectedPelada;

	@PostConstruct
	public void init() {
		Uteis.verificarLogin();
		this.selectedPelada = (Pelada) Uteis.pegarObjetoDaSessao("selectedPelada");

		if (selectedPelada == null) {
			selectedPelada = new Pelada();
		}
	}

	public void salvar() {

		validarCampos(this.selectedPelada);
		String msg = "Pelada cadastrada com sucesso!";

		if (this.selectedPelada.getId() != 0) {
			msg = "Pelada alterada com sucesso!";
		}

		this.selectedPelada.setOwner((Usuario) Uteis.pegarObjetoDaSessao("usuario"));
		peladaDAO.salvar(this.selectedPelada);

		this.selectedPelada = null;

		Uteis.MensagemInfo("Sucesso!", msg);
		Voltar();
	}

	private void validarCampos(Pelada user) {
		if (Uteis.isEmptyOrNull(user.getNomeEvento())) {
			Uteis.MensagemAtencao("Nome do evento não informado!");
		}

		if (Uteis.isEmptyOrNull(user.getLocal())) {
			Uteis.MensagemAtencao("Local não informado!");
		}

		if (user.getData() == null) {
			Uteis.MensagemAtencao("Data não informada!");
		}
	}

	public void Voltar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listarPeladas.xhtml");
		} catch (IOException e) {
			Uteis.MensagemAtencao(e.getMessage());
		}
	}

	public Pelada getPelada() {
		return selectedPelada;
	}

	public void setPelada(Pelada selectedPelada) {
		this.selectedPelada = selectedPelada;
	}

	public PeladaDAO getPeladaDAO() {
		return peladaDAO;
	}

	public void setPeladaDAO(PeladaDAO peladaDAO) {
		this.peladaDAO = peladaDAO;
	}
}