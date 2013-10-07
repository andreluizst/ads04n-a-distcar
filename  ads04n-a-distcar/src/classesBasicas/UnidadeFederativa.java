package classesBasicas;

//import comum.ObjetoGeral;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UnidadeFederativa extends ObjetoGeral {

	@Column(length=30, nullable=false)
	private String nome;
	
	@Column(length=2, nullable=false)
	private String sigla;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
