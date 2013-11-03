package classesBasicas;

import javax.persistence.*;

@Embeddable
public class Endereco {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codTipoLogradouro", insertable=true, updatable=true)
	private TipoLogradouro tipoLogradouro;
	
	@Column(length=100, nullable=false)
	private String logradouro;
	
	@Column(length=10)
	private String numero;
	
	@Column(length=80, nullable=false)
	private String bairro;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codCidade", insertable=true, updatable=true)
	private Cidade cidade;
	
	
	
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Endereco(TipoLogradouro tipoLogradouro, String logradouro,
			String numero, String bairro, Cidade cidade) {
		super();
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}



	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString(){
		return getTipoLogradouro() + " " + getLogradouro() + " " + getNumero() + " " + getBairro()
				+ " " + getCidade();
	}
	
}
