package classesBasicas;

//import comum.EntidadeBasica;
import javax.persistence.Entity;

@Entity
public class TipoGerencia extends EntidadeBasica {

	private double valorGratificacao;

	public double getValorGratificacao() {
		return valorGratificacao;
	}

	public void setValorGratificacao(double valorGratificacao) {
		this.valorGratificacao = valorGratificacao;
	}
}
