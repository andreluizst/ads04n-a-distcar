package classesBasicas;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.*;




@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa  extends ObjetoGeral {
	
	@Column(length=130, nullable=false)
	private String nome;
	
	//A classe Endereco é "@Embeddable"
	private Endereco endereco;
	
	@ElementCollection
	private Collection<String> telefones;
	
	private String email;
	
	
	public Pessoa() {
		super();
		endereco = new Endereco();
	}
	
	public Pessoa(Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}
	public Pessoa(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Collection<String> getTelefones() {
		return telefones;
	}
	public void setTelefones(Collection<String> telefones) {
		this.telefones = telefones;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
