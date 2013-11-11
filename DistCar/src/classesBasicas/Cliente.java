package classesBasicas;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class Cliente extends Pessoa {
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(15) default 'PESSOA_FISICA'", nullable=false)
	private TipoCliente tipoCliente;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
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
		super(nome, endereco, telefones, email, dataUltimaAtualizacao, situacao);
		this.tipoCliente = tipoCliente;
	}

	public Cliente(String nome, Endereco endereco,
			Collection<String> telefones, String email, TipoCliente tipoCliente) {
		super(nome, endereco, telefones, email);
		this.tipoCliente = tipoCliente;
	}
	
	

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	
	
}
