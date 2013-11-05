package classesBasicas;

import java.util.Date;

import javax.persistence.*;

import seguranca.Usuario;


@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class Funcionario extends PessoaFisica {
	// +++ ATRIBUTOS ++++
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codFuncao", insertable=true, updatable=true)
	private Funcao funcao;
	
	@Column(nullable=false, unique=true)
	private String cpts;
	
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	@Temporal(TemporalType.DATE)
	private Date dataDemissao;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codDepartamento", insertable=true, updatable=true)
	private Departamento departamento;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codUsuario", insertable=true, updatable=true)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codEescolaridade", insertable=true, updatable=true)
	private Escolaridade escolaridade;

	
	//**** CONSTRUTORES ****
	public Funcionario(){
		/*funcao = new Funcao();
		escolaridade = new Escolaridade();
		//departamento = new Departamento();
		usuario = new Usuario();*/
	}
	
	public Funcionario(Funcao funcao, String cpts, Date dataAdmissao,
			Date dataDemissao, Departamento departamento, Usuario usuario,
			Escolaridade escolaridade) {
		super();
		this.funcao = funcao;
		this.cpts = cpts;
		this.dataAdmissao = dataAdmissao;
		this.dataDemissao = dataDemissao;
		this.departamento = departamento;
		this.usuario = usuario;
		this.escolaridade = escolaridade;
	}


	// --- GETs e SETs
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public String getCpts() {
		return cpts;
	}
	public void setCpts(String cpts) {
		this.cpts = cpts;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Date getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Escolaridade getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	@Override
	public String toString(){
		return getNome() + ", " + getCpf();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpts == null) ? 0 : cpts.hashCode());
		result = prime * result
				+ ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result
				+ ((dataDemissao == null) ? 0 : dataDemissao.hashCode());
		result = prime * result
				+ ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result
				+ ((escolaridade == null) ? 0 : escolaridade.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Funcionario))
			return false;
		Funcionario other = (Funcionario) obj;
		if (cpts == null) {
			if (other.cpts != null)
				return false;
		} else if (!cpts.equals(other.cpts))
			return false;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (dataDemissao == null) {
			if (other.dataDemissao != null)
				return false;
		} else if (!dataDemissao.equals(other.dataDemissao))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (escolaridade == null) {
			if (other.escolaridade != null)
				return false;
		} else if (!escolaridade.equals(other.escolaridade))
			return false;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
