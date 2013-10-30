package classesBasicas;

import classesBasicas.EntidadeBasica;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class AcessorioCarro extends EntidadeBasica{
	
	private double valorAcessorioCarro;
	@ManyToOne
	private ModeloCarro modeloCarro;
	
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public double getValorAcessorioCarro() {
		return valorAcessorioCarro;
	}

	public void setValorAcessorioCarro(double valorAcessorioCarro) {
		this.valorAcessorioCarro = valorAcessorioCarro;
	}

	@Override
	public String toString() {
		return "AcessorioCarro [valorAcessorioCarro=" + valorAcessorioCarro
				+ ", modeloCarro=" + modeloCarro + "]";
	}

	
}
