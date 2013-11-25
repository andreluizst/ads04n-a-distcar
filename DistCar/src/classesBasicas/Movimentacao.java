package classesBasicas;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer numero;
	
	private Integer notaFiscal;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codCtoOrigem", insertable=true, updatable=true, nullable=false)
	private Centro ctoOrigem;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codCtoDestino", insertable=true, updatable=true)
	private Centro ctoDestino;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=15)
	private TipoMovimentacao tipoMovimentacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataMovimentacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataUltimaAtualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=10)
	private SituacaoMovimentacao situacao;

	public Movimentacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movimentacao(Integer numero, Integer notaFiscal, Centro ctoOrigem,
			Centro ctoDestino, TipoMovimentacao tipoMovimentacao,
			Date dataMovimentacao, Date dataUltimaAtualizacao,
			SituacaoMovimentacao situacao) {
		super();
		this.numero = numero;
		this.notaFiscal = notaFiscal;
		this.ctoOrigem = ctoOrigem;
		this.ctoDestino = ctoDestino;
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.situacao = situacao;
	}

	public Movimentacao(Integer notaFiscal, Centro ctoOrigem,
			Centro ctoDestino, TipoMovimentacao tipoMovimentacao,
			Date dataMovimentacao, Date dataUltimaAtualizacao,
			SituacaoMovimentacao situacao) {
		super();
		this.notaFiscal = notaFiscal;
		this.ctoOrigem = ctoOrigem;
		this.ctoDestino = ctoDestino;
		this.tipoMovimentacao = tipoMovimentacao;
		this.dataMovimentacao = dataMovimentacao;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
		this.situacao = situacao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Centro getCtoOrigem() {
		return ctoOrigem;
	}

	public void setCtoOrigem(Centro ctoOrigem) {
		this.ctoOrigem = ctoOrigem;
	}

	public Centro getCtoDestino() {
		return ctoDestino;
	}

	public void setCtoDestino(Centro ctoDestino) {
		this.ctoDestino = ctoDestino;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public SituacaoMovimentacao getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoMovimentacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Movimentacao [numero=" + numero + ", notaFiscal=" + notaFiscal
				+ ", ctoOrigem=" + ctoOrigem + ", ctoDestino=" + ctoDestino
				+ ", tipoMovimentacao=" + tipoMovimentacao
				+ ", dataMovimentacao=" + dataMovimentacao
				+ ", dataUltimaAtualizacao=" + dataUltimaAtualizacao
				+ ", situacao=" + situacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ctoDestino == null) ? 0 : ctoDestino.hashCode());
		result = prime * result
				+ ((ctoOrigem == null) ? 0 : ctoOrigem.hashCode());
		result = prime
				* result
				+ ((dataMovimentacao == null) ? 0 : dataMovimentacao.hashCode());
		result = prime
				* result
				+ ((dataUltimaAtualizacao == null) ? 0 : dataUltimaAtualizacao
						.hashCode());
		result = prime * result
				+ ((notaFiscal == null) ? 0 : notaFiscal.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime
				* result
				+ ((tipoMovimentacao == null) ? 0 : tipoMovimentacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Movimentacao))
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (ctoDestino == null) {
			if (other.ctoDestino != null)
				return false;
		} else if (!ctoDestino.equals(other.ctoDestino))
			return false;
		if (ctoOrigem == null) {
			if (other.ctoOrigem != null)
				return false;
		} else if (!ctoOrigem.equals(other.ctoOrigem))
			return false;
		if (dataMovimentacao == null) {
			if (other.dataMovimentacao != null)
				return false;
		} else if (!dataMovimentacao.equals(other.dataMovimentacao))
			return false;
		if (dataUltimaAtualizacao == null) {
			if (other.dataUltimaAtualizacao != null)
				return false;
		} else if (!dataUltimaAtualizacao.equals(other.dataUltimaAtualizacao))
			return false;
		if (notaFiscal == null) {
			if (other.notaFiscal != null)
				return false;
		} else if (!notaFiscal.equals(other.notaFiscal))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (situacao != other.situacao)
			return false;
		if (tipoMovimentacao != other.tipoMovimentacao)
			return false;
		return true;
	}
	
	
	
	
	
	
}
