package classesBasicas;

import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Centro extends ObjetoGeral {
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codPJ", insertable=true, updatable=true)
	private PessoaJuridica dadosPJ;
	
	@Column(length=40, nullable=false, unique=true)
	private String alias;

	private Integer capacidadeArmazenamento;
	
	@Enumerated(EnumType.STRING)
	private TipoCentro tipoCentro;
	
	
	
	public Centro() {
		super();
		dadosPJ = new PessoaJuridica();
	}

	public Centro(PessoaJuridica dadosPJ, String alias,
			Integer capacidadeArmazenamento, TipoCentro tipoCentro) {
		super();
		this.dadosPJ = dadosPJ;
		this.alias = alias;
		this.capacidadeArmazenamento = capacidadeArmazenamento;
		this.tipoCentro = tipoCentro;
	}
	
	public Centro(PessoaJuridica dadosPJ, String alias, Integer capacidadeArmazenamento, 
			TipoCentro tipoCentro, Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		this.dadosPJ = dadosPJ;
		dadosPJ.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		dadosPJ.setSituacao(situacao);
		this.alias = alias;
		this.capacidadeArmazenamento = capacidadeArmazenamento;
		this.tipoCentro = tipoCentro;
	}
	
	
	public PessoaJuridica getDadosPJ() {
		return dadosPJ;
	}

	public void setDadosPJ(PessoaJuridica dadosPJ) {
		this.dadosPJ = dadosPJ;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getCapacidadeArmazenamento() {
		return capacidadeArmazenamento;
	}

	public void setCapacidadeArmazenamento(Integer capacidadeArmazenamento) {
		this.capacidadeArmazenamento = capacidadeArmazenamento;
	}

	public TipoCentro getTipoCentro() {
		return tipoCentro;
	}

	public void setTipoCentro(TipoCentro tipoCentro) {
		this.tipoCentro = tipoCentro;
	}

	@Override
	public String toString() {
		return alias + "[" + tipoCentro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime
				* result
				+ ((capacidadeArmazenamento == null) ? 0
						: capacidadeArmazenamento.hashCode());
		result = prime * result + ((dadosPJ == null) ? 0 : dadosPJ.hashCode());
		result = prime * result
				+ ((tipoCentro == null) ? 0 : tipoCentro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Centro))
			return false;
		Centro other = (Centro) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (capacidadeArmazenamento == null) {
			if (other.capacidadeArmazenamento != null)
				return false;
		} else if (!capacidadeArmazenamento
				.equals(other.capacidadeArmazenamento))
			return false;
		if (dadosPJ == null) {
			if (other.dadosPJ != null)
				return false;
		} else if (!dadosPJ.equals(other.dadosPJ))
			return false;
		if (tipoCentro != other.tipoCentro)
			return false;
		return true;
	}
	
	
	
	
}
