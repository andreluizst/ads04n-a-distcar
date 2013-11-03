package classesBasicas;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class PessoaFisica extends Pessoa {
	
	@Column(length=11, nullable=false, unique=true)
	private String cpf;
	
	@Column(nullable=false)
	private String rg;
	
	@Column(nullable=false)
	private String orgaoExpedidor;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	
	// *** CONSTRUTORES ***
	
	public PessoaFisica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PessoaFisica(String nome, String cpf, String rg, String orgaoExpedidor) {
		super();
		super.setNome(nome);
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpedidor = orgaoExpedidor;
	}
	
	
	
	// --- GETs e SETs ---
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", rg=" + rg + ", orgaoExpedidor="
				+ orgaoExpedidor + ", dataNascimento=" + dataNascimento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result
				+ ((orgaoExpedidor == null) ? 0 : orgaoExpedidor.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PessoaFisica))
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (orgaoExpedidor == null) {
			if (other.orgaoExpedidor != null)
				return false;
		} else if (!orgaoExpedidor.equals(other.orgaoExpedidor))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}
	
	
	
}
