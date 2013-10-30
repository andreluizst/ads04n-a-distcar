package classesBasicas;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ItemSerieCarro extends EntidadeBasica{

	private double valorItemSerie;
	@ManyToOne
	private ModeloCarro modeloCarro;

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

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
		return "ItemSerieCarro [valorItemSerie=" + valorItemSerie
				+ ", modeloCarro=" + modeloCarro + "]";
	}

	
}
