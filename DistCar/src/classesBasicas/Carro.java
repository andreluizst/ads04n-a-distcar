package classesBasicas;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Carro extends ObjetoGeral {
	
	@Column(unique=true)
	private String chassi;
	@Column(length=50, nullable=false)
	private String cor;
	@Column(length=10, nullable=false)
	private Integer anoFabricacao;
	@ManyToOne
	private VersaoCarro versao;
	private double valorCarro;
	@ManyToOne
	private Centro centro;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public VersaoCarro getVersao() {
		return versao;
	}
	public void setVersao(VersaoCarro versao) {
		this.versao = versao;
	}
	public Double getValorCarro() {
		return valorCarro;
	}
	public void setValorCarro(Double valorCarro) {
		this.valorCarro = valorCarro;
	}
	public void setValorCarro(double valorCarro) {
		this.valorCarro = valorCarro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((anoFabricacao == null) ? 0 : anoFabricacao.hashCode());
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorCarro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
		Carro other = (Carro) obj;
		if (anoFabricacao == null) {
			if (other.anoFabricacao != null)
				return false;
		} else if (!anoFabricacao.equals(other.anoFabricacao))
			return false;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (chassi == null) {
			if (other.chassi != null)
				return false;
		} else if (!chassi.equals(other.chassi))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(valorCarro) != Double
				.doubleToLongBits(other.valorCarro))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}
	public Carro(String chassi, String cor, Integer anoFabricacao,
			VersaoCarro versao, double valorCarro, Centro centro, Status status) {
		super();
		this.chassi = chassi;
		this.cor = cor;
		this.anoFabricacao = anoFabricacao;
		this.versao = versao;
		this.valorCarro = valorCarro;
		this.centro = centro;
		this.status = status;
	}
	public Carro() {
		super();
	}
	@Override
	public String toString() {
		return "Carro [chassi=" + chassi + ", cor=" + cor + ", anoFabricacao="
				+ anoFabricacao + ", versao=" + versao + ", valorCarro="
				+ valorCarro + ", centro=" + centro + ", status=" + status
				+ "]";
	}
	
}
