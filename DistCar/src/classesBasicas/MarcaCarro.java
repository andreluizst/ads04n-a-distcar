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

	public MarcaCarro(String descricaoMarca) {
		super();
		this.descricaoMarca = descricaoMarca;
	}

	public MarcaCarro() {
		super();
	}

	@Override
	public String toString() {
		return "MarcaCarro [descricaoMarca=" + descricaoMarca + "]";
	}
	
		
}
