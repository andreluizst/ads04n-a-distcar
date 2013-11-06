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
	public ModeloCarro(Integer codigo, Calendar dataUltimaAtualizacao,
			Situacao situacao, String descricao, Integer ano,
			MarcaCarro marcaCarro, List<ItemSerieCarro> itemSerieCarros,
			List<AcessorioCarro> acessorioCarros) {
		super(codigo, dataUltimaAtualizacao, situacao);
		this.descricao = descricao;
		this.ano = ano;
		this.marcaCarro = marcaCarro;
		this.itemSerieCarros = itemSerieCarros;
		this.acessorioCarros = acessorioCarros;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((acessorioCarros == null) ? 0 : acessorioCarros.hashCode());
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((itemSerieCarros == null) ? 0 : itemSerieCarros.hashCode());
		result = prime * result
				+ ((marcaCarro == null) ? 0 : marcaCarro.hashCode());
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
		ModeloCarro other = (ModeloCarro) obj;
		if (acessorioCarros == null) {
			if (other.acessorioCarros != null)
				return false;
		} else if (!acessorioCarros.equals(other.acessorioCarros))
			return false;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (itemSerieCarros == null) {
			if (other.itemSerieCarros != null)
				return false;
		} else if (!itemSerieCarros.equals(other.itemSerieCarros))
			return false;
		if (marcaCarro == null) {
			if (other.marcaCarro != null)
				return false;
		} else if (!marcaCarro.equals(other.marcaCarro))
			return false;
		return true;
		
		
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
