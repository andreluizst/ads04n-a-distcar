package classesBasicas;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UnidadeFederativa extends ObjetoGeral {

	@Column(length=30, nullable=false)
	private String nome;
	
	@Column(length=2, nullable=false)
	private String sigla;
	
	
	
	public UnidadeFederativa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UnidadeFederativa(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla.toUpperCase();
	}
	
	public UnidadeFederativa(String nome, String sigla, Calendar dataUltimaAtualizacao, Situacao situacao){
		this(nome, sigla);
		setDataUltimaAtualizacao(dataUltimaAtualizacao);
		setSituacao(situacao);
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla.toUpperCase();
	}
	public void setSigla(String sigla) {
		this.sigla = sigla.toUpperCase();
	}


	@Override
	public String toString() {
		return   sigla + " - " + nome;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof UnidadeFederativa))
			return false;
		UnidadeFederativa other = (UnidadeFederativa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
	
	
}
