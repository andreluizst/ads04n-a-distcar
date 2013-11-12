package classesBasicas;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ItemSerieCarro extends ObjetoGeral{

	private String descricao;
	private double valorItemSerie;
	@ManyToOne
	private ModeloCarro modelo;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorItemSerie() {
		return valorItemSerie;
	}
	public void setValorItemSerie(double valorItemSerie) {
		this.valorItemSerie = valorItemSerie;
	}
	public ModeloCarro getModelo() {
		return modelo;
	}
	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return "ItemSerieCarro [descricao=" + descricao + ", valorItemSerie="
				+ valorItemSerie + ", modelo=" + modelo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorItemSerie);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemSerieCarro other = (ItemSerieCarro) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (Double.doubleToLongBits(valorItemSerie) != Double
				.doubleToLongBits(other.valorItemSerie))
			return false;
		return true;
	}
	public ItemSerieCarro(String descricao, double valorItemSerie,
			ModeloCarro modelo) {
		super();
		this.descricao = descricao;
		this.valorItemSerie = valorItemSerie;
		this.modelo = modelo;
	}
	public ItemSerieCarro() {
		super();
	}
	
}
