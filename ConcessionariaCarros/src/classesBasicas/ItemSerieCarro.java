package classesBasicas;

import javax.persistence.Entity;


@Entity
public class ItemSerieCarro extends EntidadeBasica{

	private double valorItemSerie;

	public double getValorItemSerie() {
		return valorItemSerie;
	}

	public void setValorItemSerie(double valorItemSerie) {
		this.valorItemSerie = valorItemSerie;
	}

	public ItemSerieCarro() {
		super();
	}
	
}
