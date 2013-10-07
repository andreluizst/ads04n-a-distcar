package classesBasicas;

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

	public AcessorioCarro() {
		super();
	}
	
	
}
