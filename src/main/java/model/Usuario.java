/**
 * 
 */
package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Emerson Santos
 *
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false, unique=true)
	private int id;
	@Column(name="nome", nullable=false)
	private String nome;
	@Column(name="apelido", nullable=false)
	private String apelido;
	@Column(name="email", nullable=false, unique=true)
	private String email;
	@Column(name="senha", nullable=false)
	private String senha;
	@ManyToMany(mappedBy = "jogadores")
	private List<Pelada> peladas;
	@ManyToMany(mappedBy = "convidados")
	private List<Pelada> convites;
	@OneToMany(mappedBy="owner")  
    private List<Pelada> propriedades;  
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Pelada> getPeladas() {
		return peladas;
	}
	public void setPeladas(List<Pelada> peladas) {
		this.peladas = peladas;
	}
	public List<Pelada> getConvites() {
		return convites;
	}
	public void setConvites(List<Pelada> convites) {
		this.convites = convites;
	}
	public List<Pelada> getPropriedades() {
		return propriedades;
	}
	public void setPropriedades(List<Pelada> propriedades) {
		this.propriedades = propriedades;
	}
}
