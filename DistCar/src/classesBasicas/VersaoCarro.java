package classesBasicas;


import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
public class VersaoCarro extends ObjetoGeral {
	
	@Transient
	public static final int TO_STRING_PADRAO = 0;
	@Transient
	public static final int TO_STRING_DESCRICAO_PARA_LISTA = 1;
	
	@Transient
	private int comportamentoToString = TO_STRING_PADRAO;
	
	private double valor;
	@Column(unique=true)
	private String descricao;
	@ManyToOne(fetch = FetchType.EAGER)
	private ModeloCarro modeloCarro;
	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<AcessorioCarro> acessorios;
	@ManyToMany
	private List<ItemSerieCarro> itens;
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public List<AcessorioCarro> getAcessorios() {
		return acessorios;
	}
	public void setAcessorios(List<AcessorioCarro> acessorios) {
		this.acessorios = acessorios;
	}
	public List<ItemSerieCarro> getItens() {
		return itens;
	}
	public void setItens(List<ItemSerieCarro> itens) {
		this.itens = itens;
	}
	
	public int getComportamentoToString() {
		return comportamentoToString;
	}
	
	public void setComportamentoToString(int comportamentoToString) {
		this.comportamentoToString = comportamentoToString;
	}
	
	
	@Override
	public String toString() {
		String str = "VersaoCarro [valor=" + valor + ", descricao=" + descricao
				+ ", modeloCarro=" + modeloCarro + ", acessorios=" + acessorios
				+ ", itens=" + itens + "]";
		if (comportamentoToString == VersaoCarro.TO_STRING_DESCRICAO_PARA_LISTA)
			str = modeloCarro.getDescricao() + " " + modeloCarro.getAno() 
				+ " " + getDescricao() + "[" + acessorios + " + " + itens + "]";
		return str;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((acessorios == null) ? 0 : acessorios.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result
				+ ((modeloCarro == null) ? 0 : modeloCarro.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
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
		VersaoCarro other = (VersaoCarro) obj;
		if (acessorios == null) {
			if (other.acessorios != null)
				return false;
		} else if (!acessorios.equals(other.acessorios))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (modeloCarro == null) {
			if (other.modeloCarro != null)
				return false;
		} else if (!modeloCarro.equals(other.modeloCarro))
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	public VersaoCarro() {
		super();
	}
	public VersaoCarro(double valor, String descricao, ModeloCarro modeloCarro,
			List<AcessorioCarro> acessorios, List<ItemSerieCarro> itens) {
		super();
		this.valor = valor;
		this.descricao = descricao;
		this.modeloCarro = modeloCarro;
		this.acessorios = acessorios;
		this.itens = itens;
	}
	
	
}
