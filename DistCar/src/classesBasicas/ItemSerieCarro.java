package classesBasicas;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ItemSerieCarro extends EntidadeBasica{

	private double valorItemSerie;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private VersaoModeloCarro versaoModeloCarro;
	public double getValorItemSerie() {
		return valorItemSerie;
	}

	public void setValorItemSerie(double valorItemSerie) {
		this.valorItemSerie = valorItemSerie;
	}

	public ItemSerieCarro(double valorItemSerie) {
		super();
		this.valorItemSerie = valorItemSerie;
	}

	public ItemSerieCarro() {
		super();
	}

	@Override
	public String toString() {
		return "ItemSerieCarro [Descricção=" + getDescricao()
				+ ", valorItemSerie=" + valorItemSerie + "]";
	}

	
	
}
