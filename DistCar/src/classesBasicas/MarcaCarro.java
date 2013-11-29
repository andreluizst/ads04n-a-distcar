package classesBasicas;


import javax.persistence.*;

@Entity
public class MarcaCarro extends ObjetoGeral {
	
	@Column(unique=true)
	private String descricao;
	@ManyToOne(fetch=FetchType.EAGER)
	private Fabricante fabricante;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public MarcaCarro() {
		super();
	}
	public MarcaCarro(String descricao, Fabricante fabricante) {
		super();
		this.descricao = descricao;
		this.fabricante = fabricante;
	}
	@Override
	public String toString() {
		return "MarcaCarro [descricao=" + descricao + ", fabricante="
				+ fabricante + "]";
	}
		
}
