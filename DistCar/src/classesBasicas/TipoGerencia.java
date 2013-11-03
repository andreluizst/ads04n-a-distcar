package classesBasicas;

import java.util.Calendar;

//import comum.EntidadeBasica;
import javax.persistence.Entity;

@Entity
public class TipoGerencia extends EntidadeBasica {

	private double valorGratificacao;
	
	
	public TipoGerencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia(Integer codigo, String descricao,
			Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(codigo, descricao, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia(String descricao, Calendar dataUltimaAtualizacao,
			Situacao situacao) {
		super(descricao, dataUltimaAtualizacao, situacao);
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia(String descricao) {
		super(descricao);
		// TODO Auto-generated constructor stub
	}

	public double getValorGratificacao() {
		return valorGratificacao;
	}

	public void setValorGratificacao(double valorGratificacao) {
		this.valorGratificacao = valorGratificacao;
	}
	
	@Override
	public String toString(){
		return getDescricao();
	}
}
