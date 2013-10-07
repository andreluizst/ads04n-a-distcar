package classesBasicas;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;

@MappedSuperclass
public abstract class EntidadeBasica extends ObjetoGeral {
	
	@Column(nullable=false)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
