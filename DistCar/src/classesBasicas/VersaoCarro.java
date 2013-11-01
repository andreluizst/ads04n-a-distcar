package classesBasicas;


import javax.persistence.*;


@Entity
public class VersaoCarro extends ObjetoGeral {
	
	private double valor;
	@Column(unique=true)
	private String descricao;
	@ManyToOne
	private ModeloCarro modeloCarro;
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public VersaoCarro() {
		super();
	}
	public VersaoCarro(double valor, String descricao, ModeloCarro modeloCarro) {
		super();
		this.valor = valor;
		this.descricao = descricao;
		this.modeloCarro = modeloCarro;
	}
	@Override
	public String toString() {
		return "VersaoCarro [valor=" + valor + ", descricao=" + descricao
				+ ", modeloCarro=" + modeloCarro + "]";
	}
	
	
}
