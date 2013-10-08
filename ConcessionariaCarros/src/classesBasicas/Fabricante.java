package classesBasicas;

import javax.persistence.*;

@Entity
public class Fabricante extends ObjetoGeral {
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codPJ", insertable=true, updatable=true)
	private PessoaJuridica pj;
	
	private Integer loteMinimo;
	
	
	public Fabricante() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Fabricante(PessoaJuridica pj, Integer loteMinimo) {
		super();
		this.pj = pj;
		this.loteMinimo = loteMinimo;
	}
	
	
	public PessoaJuridica getPj() {
		return pj;
	}

	public void setPj(PessoaJuridica pj) {
		this.pj = pj;
	}

	public Integer getLoteMinimo() {
		return loteMinimo;
	}

	public void setLoteMinimo(Integer loteMinimo) {
		this.loteMinimo = loteMinimo;
	}
	
	
	
}
