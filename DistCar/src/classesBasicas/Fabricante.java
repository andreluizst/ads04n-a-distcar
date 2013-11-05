package classesBasicas;

//import java.util.Calendar;

import javax.persistence.*;

@Entity
public class Fabricante /*extends ObjetoGeral*/ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codPJ", insertable=true, updatable=true)
	private PessoaJuridica pj;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codMarca", insertable=true, updatable=true)
	private MarcaCarro marcaCarro;
	
	
	private Integer loteMinimo;
	
	
	
	// ***** CONSTRUTORES ******
	
	public Fabricante() {
		super();
		//pj = new PessoaJuridica();
	}
	/*
	public Fabricante(Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public Fabricante(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}	*/
	
	public Fabricante(PessoaJuridica pj, MarcaCarro marcaCarro,
			Integer loteMinimo) {
		super();
		this.pj = pj;
		this.marcaCarro = marcaCarro;
		this.loteMinimo = loteMinimo;
	}
	
	
	// --- GETs e SEts
	public PessoaJuridica getPj() {
		return pj;
	}

	public void setPj(PessoaJuridica pj) {
		this.pj = pj;
	}

	public Integer getLoteMinimo() {
		return loteMinimo;
	}

	public void setLoteMinimo(Integer loteMinimo) {
		this.loteMinimo = loteMinimo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public MarcaCarro getMarcaCarro() {
		return marcaCarro;
	}

	public void setMarcaCarro(MarcaCarro marcaCarro) {
		this.marcaCarro = marcaCarro;
	}
	
	
	
}
