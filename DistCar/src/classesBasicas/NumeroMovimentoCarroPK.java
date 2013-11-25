package classesBasicas;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class NumeroMovimentoCarroPK {
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codCarro", insertable=true, updatable=true, nullable=false)
	private Carro carro;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="numeroMovementacao", insertable=true, updatable=false, nullable=false)
	private Integer numeroMovimentacao;

	public NumeroMovimentoCarroPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NumeroMovimentoCarroPK(Carro carro, Integer numeroMovimentacao) {
		super();
		this.carro = carro;
		this.numeroMovimentacao = numeroMovimentacao;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Integer getNumeroMovimentacao() {
		return numeroMovimentacao;
	}

	public void setNumeroMovimentacao(Integer numeroMovimentacao) {
		this.numeroMovimentacao = numeroMovimentacao;
	}

	@Override
	public String toString() {
		return "NumeroMovimentoCarroPK [carro=" + carro
				+ ", numeroMovimentacao=" + numeroMovimentacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		result = prime
				* result
				+ ((numeroMovimentacao == null) ? 0 : numeroMovimentacao
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof NumeroMovimentoCarroPK))
			return false;
		NumeroMovimentoCarroPK other = (NumeroMovimentoCarroPK) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		if (numeroMovimentacao == null) {
			if (other.numeroMovimentacao != null)
				return false;
		} else if (!numeroMovimentacao.equals(other.numeroMovimentacao))
			return false;
		return true;
	}
	
	
	
}
