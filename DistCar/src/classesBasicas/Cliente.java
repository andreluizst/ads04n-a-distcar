package classesBasicas;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente extends ObjetoGeral{
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(15) default 'PESSOA_FISICA'", nullable=false)
	private TipoCliente tipoCliente;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codPessoa", insertable=true, updatable=true, nullable=false)
	private Pessoa dadosPessoa;
	
	public Cliente() {
		super();
		tipoCliente = TipoCliente.PESSOA_FISICA;
		dadosPessoa = new PessoaFisica();
	}

	public Cliente(Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nome, Endereco endereco,
			Collection<String> telefones, String email, TipoCliente tipoCliente,
			Calendar dataUltimaAtualizacao, Situacao situacao) {
		//super(nome, endereco, telefones, email, dataUltimaAtualizacao, situacao);
		this.dadosPessoa.setNome(nome);
		this.dadosPessoa.setEndereco(endereco);
		this.dadosPessoa.setTelefones(telefones);
		this.dadosPessoa.setEmail(email);
		this.dadosPessoa.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		this.dadosPessoa.setSituacao(situacao);
		setDataUltimaAtualizacao(dataUltimaAtualizacao);
		setSituacao(situacao);
		this.tipoCliente = tipoCliente;
	}

	public Cliente(String nome, Endereco endereco,
			Collection<String> telefones, String email, TipoCliente tipoCliente) {
		//super(nome, endereco, telefones, email);
		this.dadosPessoa.setNome(nome);
		this.dadosPessoa.setEndereco(endereco);
		this.dadosPessoa.setTelefones(telefones);
		this.dadosPessoa.setEmail(email);
		this.tipoCliente = tipoCliente;
	}
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}

	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}
	
	
	
}
