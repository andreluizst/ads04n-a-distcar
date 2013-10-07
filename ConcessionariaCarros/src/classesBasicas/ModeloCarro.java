package classesBasicas;


import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class ModeloCarro extends ObjetoGeral {
	
	@Column(length=100, nullable=false, unique = true)
	private String descricaoModelocarro;
	@ManyToOne
	private MarcaCarro marcaCarro;
	@Column(length=100, nullable=false)
	private Integer anoModelo;
	@ManyToOne//(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinColumn(name="codModelo", insertable=true, updatable=true, nullable=false)
	@Cascade(CascadeType.PERSIST)
	private VersaoModeloCarro versaoModeloCarro;
	@OneToMany
	@Cascade(CascadeType.PERSIST)
	private List<ItemSerieCarro> itemSerieCarros;
	@OneToMany
	@Cascade(CascadeType.PERSIST)
	private List<AcessorioCarro> acessorioCarros;
	public String getDescricaoModelocarro() {
		return descricaoModelocarro;
	}
	public void setDescricaoModelocarro(String descricaoModelocarro) {
		this.descricaoModelocarro = descricaoModelocarro;
	}
	public MarcaCarro getMarcaCarro() {
		return marcaCarro;
	}
	public void setMarcaCarro(MarcaCarro marcaCarro) {
		this.marcaCarro = marcaCarro;
	}
	public Integer getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	public VersaoModeloCarro getVersaoModeloCarro() {
		return versaoModeloCarro;
	}
	public void setVersaoModeloCarro(VersaoModeloCarro versaoModeloCarro) {
		this.versaoModeloCarro = versaoModeloCarro;
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
	public ModeloCarro(String descricaoModelocarro, MarcaCarro marcaCarro,
			Integer anoModelo, VersaoModeloCarro versaoModeloCarro,
			List<ItemSerieCarro> itemSerieCarros,
			List<AcessorioCarro> acessorioCarros) {
		super();
		this.descricaoModelocarro = descricaoModelocarro;
		this.marcaCarro = marcaCarro;
		this.anoModelo = anoModelo;
		this.versaoModeloCarro = versaoModeloCarro;
		this.itemSerieCarros = itemSerieCarros;
		this.acessorioCarros = acessorioCarros;
	}
	public ModeloCarro() {
		super();
	}
	
	
}
