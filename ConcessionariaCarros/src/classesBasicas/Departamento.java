package classesBasicas;

import javax.persistence.*;


/*
	FALTA fazer o relacionamento N:N entre Departamento e Centro 

*/


@Entity
public class Departamento extends ObjetoGeral {
	@Column(length=20, nullable=false)
	private String nome;
	
	/* Necessario para montar o organograma da empresa 
	   Diretoria Financeira > Controladoria > Gerencia de Faturamento
	*/
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codDepSup", insertable=true, updatable=true)
	private Departamento departamentoSuperior;
	
	@OneToOne(fetch = FetchType.EAGER, 
				cascade = {CascadeType.MERGE, CascadeType.DETACH, 
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="codGestor", insertable=true, updatable=true)
	private Gestor gestor;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codCentro", insertable=true, updatable=true, nullable=false)
	private Centro centro;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Departamento getDepartamentoSuperior() {
		return departamentoSuperior;
	}
	public void setDepartamentoSuperior(Departamento departamentoSuperior) {
		this.departamentoSuperior = departamentoSuperior;
	}
	public Gestor getGestor() {
		return gestor;
	}
	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}
}
