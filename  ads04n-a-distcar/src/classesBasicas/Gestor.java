package classesBasicas;

import javax.persistence.*;

@Entity
public class Gestor extends Funcionario {

	// Diretor, Superintendente, Gerente, Coordenador ,etc..
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="codTipoGerencia", insertable=true, updatable=true)
	private TipoGerencia tipoGerencia;

	public TipoGerencia getTipoGerencia() {
		return tipoGerencia;
	}

	public void setTipoGerencia(TipoGerencia tipoGerencia) {
		this.tipoGerencia = tipoGerencia;
	}
}
