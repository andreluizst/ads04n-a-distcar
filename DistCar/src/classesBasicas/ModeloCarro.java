package classesBasicas;


import javax.persistence.*;

import java.util.*;

@Entity
public class ModeloCarro extends ObjetoGeral {
	
	@Column(length=100, nullable=false)
	private String descricaoModeloCarro;
	@Column(length=10, nullable=false)
	private Integer anoModelo;
	@ManyToOne
	private MarcaCarro marcaCarro;
	@OneToMany
	private List<ItemSerieCarro> itemSerieCarros;
	@OneToMany
	private List<AcessorioCarro> acessorioCarros;
	
	public String getDescricaoModeloCarro() {
		return descricaoModeloCarro;
	}
	public void setDescricaoModeloCarro(String descricaoModeloCarro) {
		this.descricaoModeloCarro = descricaoModeloCarro;
	}
	public Integer getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
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
	public ModeloCarro() {
		super();
	}
	public ModeloCarro(String descricaoModeloCarro, Integer anoModelo,
			List<ItemSerieCarro> itemSerieCarros,
			List<AcessorioCarro> acessorioCarros) {
		super();
		this.descricaoModeloCarro = descricaoModeloCarro;
		this.anoModelo = anoModelo;
		this.itemSerieCarros = itemSerieCarros;
		this.acessorioCarros = acessorioCarros;
	}
		
}
