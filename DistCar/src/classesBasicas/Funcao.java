package classesBasicas;

import java.util.Calendar;

import javax.persistence.Entity;


@Entity
public class Funcao extends EntidadeBasica {

	private double salario;
	
	//*** CONSTRUTORES ***
	public Funcao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Funcao(String nome, double salario) {
		this(nome, salario, null, null);
	}
	
	public Funcao(String nome, double salario, Calendar dataUltimaAtualizacao, Situacao situacao) {
		super(nome, dataUltimaAtualizacao, situacao);
		this.salario = salario;
	}


	// *** GETs e SETs 
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	

	@Override
	public String toString() {
		return getDescricao() + " [" + salario +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Funcao))
			return false;
		Funcao other = (Funcao) obj;
		if (Double.doubleToLongBits(salario) != Double
				.doubleToLongBits(other.salario))
			return false;
		return true;
	} 
	
	
	
}
