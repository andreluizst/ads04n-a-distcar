package classesBasicas;

import java.util.Calendar;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;


@MappedSuperclass
public abstract class EntidadeBasica extends ObjetoGeral {
	
	@Column(nullable=false)
	private String descricao;
	
	//****** CONSTRUTORES ******
	
	public EntidadeBasica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EntidadeBasica(String descricao) {
		super();
		this.descricao = descricao;
	}

	public EntidadeBasica(String descricao, Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(dataUltimaAtualizacao, situacao);
		this.descricao = descricao;
		// TODO Auto-generated constructor stub
	}

	public EntidadeBasica(Integer codigo, String descricao, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(codigo, dataUltimaAtualizacao, situacao);
		this.descricao = descricao;
		// TODO Auto-generated constructor stub
	}
	
	
	
	// --- GETs e SETs

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
	
}
