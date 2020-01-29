/**
 * 
 */
package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Emerson Santos
 *
 */
@Entity
@Table(name = "peladas")
public class Pelada {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false, unique=true)
	private int id;
	@Column(name="nome_evento", nullable=false)
	private String nomeEvento;
	@Column(name="data", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Column(name="local", nullable=false)
	private String local;
	@ManyToOne(cascade=CascadeType.DETACH)
	private Usuario owner;
	@ManyToMany
    @JoinTable(
        name = "pelada_usuario",
        joinColumns = @JoinColumn(name = "pelada_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
	private List<Usuario> jogadores;
	@ManyToMany
    @JoinTable(
        name = "pelada_usuario_convidados",
        joinColumns = @JoinColumn(name = "pelada_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
	private List<Usuario> convidados;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public List<Usuario> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<Usuario> jogadores) {
		this.jogadores = jogadores;
	}
	public List<Usuario> getConvidados() {
		return convidados;
	}
	public void setConvidados(List<Usuario> convidados) {
		this.convidados = convidados;
	}
	public Usuario getOwner() {
		return owner;
	}
	public void setOwner(Usuario owner) {
		this.owner = owner;
	}
}
