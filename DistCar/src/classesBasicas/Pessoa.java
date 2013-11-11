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
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(15) default 'NAO_É_CLIENTE'", nullable=false)
	private TipoCliente tipoCliente;
	
	
	public Pessoa() {
		super();
		endereco = new Endereco();
		tipoCliente = TipoCliente.NAO_É_CLIENTE;
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
	
	public Pessoa(String nome, Endereco endereco, Collection<String> telefones,
			String email) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefones = telefones;
		this.email = email;
	}
	
	public Pessoa(String nome, Endereco endereco, Collection<String> telefones,
			String email, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefones = telefones;
		this.email = email;
		super.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		super.setSituacao(situacao);
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
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	
	

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", endereco=" + endereco
				+ ", telefones=" + telefones + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((telefones == null) ? 0 : telefones.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefones == null) {
			if (other.telefones != null)
				return false;
		} else if (!telefones.equals(other.telefones))
			return false;
		return true;
	}
	
	
	
}
