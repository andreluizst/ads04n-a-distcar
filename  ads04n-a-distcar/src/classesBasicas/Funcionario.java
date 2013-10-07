package classesBasicas;

import java.util.Date;

import javax.persistence.*;

import seguranca.Usuario;


@Entity
@PrimaryKeyJoinColumn(name="codigo")
public class Funcionario extends PessoaFisica {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codFuncao", insertable=true, updatable=true)
	private Funcao funcao;
	
	@Column(nullable=false, unique=true)
	private String cpts;
	
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	@Temporal(TemporalType.DATE)
	private Date dataDemissao;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codDepartamento", insertable=true, updatable=true)
	private Departamento departamento;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codUsuario", insertable=true, updatable=true)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codEescolaridade", insertable=true, updatable=true)
	private Escolaridade escolaridade;

	
	public Funcionario(){
		
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
	
}
