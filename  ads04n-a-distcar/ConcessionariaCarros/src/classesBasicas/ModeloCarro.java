package classesBasicas;

import javax.persistence.*;

@Entity
public class ModeloCarro extends ObjetoGeral {
	
	private String descricaoModelocarro;
	private Integer anoModelo;
	
	
	public ModeloCarro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ModeloCarro(String descricaoModelocarro, Integer anoModelo) {
		super();
		this.descricaoModelocarro = descricaoModelocarro;
		this.anoModelo = anoModelo;
	}



	public String getDescricaoModelocarro() {
		return descricaoModelocarro;
	}

	public void setDescricaoModelocarro(String descricaoModelocarro) {
		this.descricaoModelocarro = descricaoModelocarro;
	}
	
	public Integer getAnoModelo() {
		return anoModelo;
	}
	
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	
}
