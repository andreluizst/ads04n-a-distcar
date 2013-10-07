package classesBasicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Carro extends ObjetoGeral {
	@Column(unique=true)
	private String chassi;
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private MarcaCarro marca;
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private ModeloCarro modeloCarro;
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private VersaoModeloCarro versaoModeloCarro;
	private String cor;
	private Double valorCarro;
	private Integer anoFabricacao;
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public MarcaCarro getMarca() {
		return marca;
	}
	public void setMarca(MarcaCarro marca) {
		this.marca = marca;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public VersaoModeloCarro getVersaoModeloCarro() {
		return versaoModeloCarro;
	}
	public void setVersaoModeloCarro(VersaoModeloCarro versaoModeloCarro) {
		this.versaoModeloCarro = versaoModeloCarro;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Double getValorCarro() {
		return valorCarro;
	}
	public void setValorCarro(Double valorCarro) {
		this.valorCarro = valorCarro;
	}
	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public Carro(String chassi, MarcaCarro marca, ModeloCarro modeloCarro,
			VersaoModeloCarro versaoModeloCarro, String cor, Double valorCarro,
			Integer anoFabricacao) {
		super();
		this.chassi = chassi;
		this.marca = marca;
		this.modeloCarro = modeloCarro;
		this.versaoModeloCarro = versaoModeloCarro;
		this.cor = cor;
		this.valorCarro = valorCarro;
		this.anoFabricacao = anoFabricacao;
	}
	public Carro() {
		super();
	}
	
	
}
