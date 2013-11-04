package classesBasicas;


import javax.persistence.*;

import java.util.*;

@Entity
public class ModeloCarro extends ObjetoGeral {
	
	@Column(length=100, nullable=false)
	private String descricao;
	@Column(length=10, nullable=false)
	private Integer ano;
	@ManyToOne
	private MarcaCarro marcaCarro;
	@OneToMany
	private List<ItemSerieCarro> itemSerieCarros;
	@OneToMany
	private List<AcessorioCarro> acessorioCarros;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public MarcaCarro getMarcaCarro() {
		return marcaCarro;
	}
	public void setMarcaCarro(MarcaCarro marcaCarro) {
		this.marcaCarro = marcaCarro;
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
	public ModeloCarro(String descricao, Integer ano, MarcaCarro marcaCarro,
			List<ItemSerieCarro> itemSerieCarros,
			List<AcessorioCarro> acessorioCarros) {
		super();
		this.descricao = descricao;
		this.ano = ano;
		this.marcaCarro = marcaCarro;
		this.itemSerieCarros = itemSerieCarros;
		this.acessorioCarros = acessorioCarros;
	}
	@Override
	public String toString() {
		return "ModeloCarro [descricao=" + descricao + ", ano=" + ano
				+ ", marcaCarro=" + marcaCarro + ", itemSerieCarros="
				+ itemSerieCarros + ", acessorioCarros=" + acessorioCarros
				+ ", getCodigo()=" + getCodigo()
				+ ", getDataUltimaAtualizacao()=" + getDataUltimaAtualizacao()
				+ ", getSituacao()=" + getSituacao() + "]";
	}

	
}
