package classesBasicas;


import java.util.List;
import javax.persistence.*;


@Entity
public class VersaoModeloCarro extends ObjetoGeral {
	
	private double valorVersao;
	@Column(unique=true)
	private String descricaoVersaoModeloCarro;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private ModeloCarro modeloCarro;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<ItemSerieCarro> itemSerieCarros;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<AcessorioCarro> acessorioCarros;
	
	public double getValorVersao() {
		return valorVersao;
	}
	public void setValorVersao(double valorVersao) {
		this.valorVersao = valorVersao;
	}
	public String getDescricaoVersaoModeloCarro() {
		return descricaoVersaoModeloCarro;
	}
	public void setDescricaoVersaoModeloCarro(String descricaoVersaoModeloCarro) {
		this.descricaoVersaoModeloCarro = descricaoVersaoModeloCarro;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public List<ItemSerieCarro> getItemSerieCarros() {
		return itemSerieCarros;
	}
	public void setItemSerieCarros(List<ItemSerieCarro> itemSerieCarros) {
		this.itemSerieCarros = itemSerieCarros;
	}
	public List<AcessorioCarro> getAcessorioCarros() {
		return acessorioCarros;
	}
	public void setAcessorioCarros(List<AcessorioCarro> acessorioCarros) {
		this.acessorioCarros = acessorioCarros;
	}
	public VersaoModeloCarro(double valorVersao,
			String descricaoVersaoModeloCarro, ModeloCarro modeloCarro,
			List<ItemSerieCarro> itemSerieCarros,
			List<AcessorioCarro> acessorioCarros) {
		super();
		this.valorVersao = valorVersao;
		this.descricaoVersaoModeloCarro = descricaoVersaoModeloCarro;
		this.modeloCarro = modeloCarro;
		this.itemSerieCarros = itemSerieCarros;
		this.acessorioCarros = acessorioCarros;
	}
	
	public VersaoModeloCarro() {
		super();
	}
	@Override
	public String toString() {
		return "VersaoModeloCarro [valorVersao=" + valorVersao
				+ ", descricaoVersaoModeloCarro=" + descricaoVersaoModeloCarro
				+ ", modeloCarro=" + modeloCarro + ", itemSerieCarros="
				+ itemSerieCarros + ", acessorioCarros=" + acessorioCarros
				+ ", getItemSerieCarros()=" + getItemSerieCarros()
				+ ", getAcessorioCarros()=" + getAcessorioCarros() + "]";
	}
	
	
	
}
