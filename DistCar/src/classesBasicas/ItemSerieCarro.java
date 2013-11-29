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
		return descricao +" "+ valorItemSerie;
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
