package classesBasicas;

//import comum.EntidadeBasica;

import javax.persistence.Entity;

@Entity
public class Funcao extends EntidadeBasica {

	private double salario;

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	} 
}
