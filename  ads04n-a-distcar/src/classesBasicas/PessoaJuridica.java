package classesBasicas;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class PessoaJuridica extends Pessoa {
	@Column(length=14, nullable=false, unique=true)
	private String cnpj;
	
	@Column(nullable=false)
	private String inscricaoEstatdual;
	
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	
	public PessoaJuridica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PessoaJuridica(String cnpj, String inscricaoEstatdual,
			Date dataAbertura) {
		super();
		this.cnpj = cnpj;
		this.inscricaoEstatdual = inscricaoEstatdual;
		this.dataAbertura = dataAbertura;
	}

	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstatdual() {
		return inscricaoEstatdual;
	}

	public void setInscricaoEstatdual(String inscricaoEstatdual) {
		this.inscricaoEstatdual = inscricaoEstatdual;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	
	
}
