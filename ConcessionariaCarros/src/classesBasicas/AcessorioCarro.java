package classesBasicas;

import classesBasicas.EntidadeBasica;

import javax.persistence.Entity;


@Entity
public class AcessorioCarro extends EntidadeBasica{
	
	private double valorAcessorioCarro;

	public double getValorAcessorioCarro() {
		return valorAcessorioCarro;
	}

	public void setValorAcessorioCarro(double valorAcessorioCarro) {
		this.valorAcessorioCarro = valorAcessorioCarro;
	}

	@Override
	public String toString() {
		return "AcessorioCarro [Descrição=" + getDescricao()
				+ ", valorAcessorioCarro=" + valorAcessorioCarro + "]";
	}

	
}
