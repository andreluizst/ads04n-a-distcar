package seguranca;

import java.util.List;
import javax.persistence.*;

import comum.ObjetoGeral;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class Usuario extends ObjetoGeral {

	@Column(length=15, nullable=false, unique=true)
	private String login;
	
	@Column(length=25, nullable=false)
	private String senhaAtual;
	
	@ElementCollection
	private List<String> ultimasSenhas;
	
	@Enumerated(EnumType.STRING)
	private SituacaoSenha situacaoSenha;
	
	// Um usuário pode ter vários perfis e um perfil pode conter vários usuários
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="perfil_usuario", 
				joinColumns=@JoinColumn(name="codUsuario", insertable=true, updatable=true), 
				inverseJoinColumns=@JoinColumn(name="codPerfil", insertable=true, updatable=true))
	private List<Perfil> listaPerfis;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public List<String> getUltimasSenhas() {
		return ultimasSenhas;
	}
	public void setUltimasSenhas(List<String> ultimasSenhas) {
		this.ultimasSenhas = ultimasSenhas;
	}
	public SituacaoSenha getSituacaoSenha() {
		return situacaoSenha;
	}
	public void setSituacaoSenha(SituacaoSenha situacaoSenha) {
		this.situacaoSenha = situacaoSenha;
	}
	public List<Perfil> getListaPerfis() {
		return listaPerfis;
	}
	public void setListaPerfis(List<Perfil> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}
}
