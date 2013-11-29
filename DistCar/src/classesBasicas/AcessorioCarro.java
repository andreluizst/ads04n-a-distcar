package classesBasicas;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AcessorioCarro extends ObjetoGeral{
	
	private String descricao;
	private double valor;
	@ManyToOne
	private ModeloCarro modelo;
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
	public ModeloCarro getModelo() {
		return modelo;
	}
	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}
	
	public AcessorioCarro() {
		super();
	}
	public AcessorioCarro(String descricao, double valor, ModeloCarro modelo) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return descricao +" "+ valor;
	}
	
}
