package classesBasicas;

import javax.persistence.Entity;

@Entity
public class TipoLogradouro extends EntidadeBasica {

	public TipoLogradouro() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TipoLogradouro(String descricao){
		super();
		super.setDescricao(descricao);
	}
	
	@Override
	public String toString(){
		return getDescricao();
	}
}
