package classesBasicas;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class AcessorioCarro extends ObjetoGeral{
	
	private String descricao;
	private double valor;
	@ManyToOne
	private ModeloCarro modeloCarro;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public AcessorioCarro() {
		super();
	}
	public AcessorioCarro(String descricao, double valor,
			ModeloCarro modeloCarro) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.modeloCarro = modeloCarro;
	}
	@Override
	public String toString() {
		return "AcessorioCarro [descricao=" + descricao + ", valor=" + valor
				+ ", modeloCarro=" + modeloCarro + "]";
	}
	
	
}
