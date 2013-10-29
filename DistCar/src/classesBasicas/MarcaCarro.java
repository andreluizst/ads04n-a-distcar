package classesBasicas;


import javax.persistence.*;

@Entity
public class MarcaCarro extends ObjetoGeral {
	
	@Column(unique=true)
	private String descricaoMarca;
	@ManyToOne
	private Fabricante fabricante;
	public String getDescricaoMarca() {
		return descricaoMarca;
	}
	public void setDescricaoMarca(String descricaoMarca) {
		this.descricaoMarca = descricaoMarca;
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
	public MarcaCarro(String descricaoMarca, Fabricante fabricante) {
		super();
		this.descricaoMarca = descricaoMarca;
		this.fabricante = fabricante;
	}
	
}
