package classesBasicas;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class PessoaJuridica extends Pessoa {
	@Column(length=14, nullable=false, unique=true)
	private String cnpj;
	
	@Column(nullable=false)
	private String inscricaoEstadual;
	
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	
	//**** COSTRUTORES *****
	public PessoaJuridica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PessoaJuridica(String nome, String cnpj, String inscricaoEstadual,
			Date dataAbertura) {
		this(nome, cnpj, inscricaoEstadual, dataAbertura, null);
	}
	
	public PessoaJuridica(String nome, String cnpj, String inscricaoEstadual,
			Date dataAbertura, Endereco endereco) {
		super();
		super.setNome(nome);
		super.setEndereco(endereco);
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.dataAbertura = dataAbertura;
	}


	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstatdual) {
		this.inscricaoEstadual = inscricaoEstatdual;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", inscricaoEstadual="
				+ inscricaoEstadual + ", dataAbertura=" + dataAbertura + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result
				+ ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
		result = prime
				* result
				+ ((inscricaoEstadual == null) ? 0 : inscricaoEstadual
						.hashCode());
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
		PessoaJuridica other = (PessoaJuridica) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (dataAbertura == null) {
			if (other.dataAbertura != null)
				return false;
		} else if (!dataAbertura.equals(other.dataAbertura))
			return false;
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		return true;
	}
	
	
	
}
