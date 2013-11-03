package classesBasicas;

import java.util.Date;

import javax.persistence.*;

import seguranca.Usuario;

@Entity
public class Gestor extends Funcionario {

	// Diretor, Superintendente, Gerente, Coordenador ,etc..
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="codTipoGerencia", insertable=true, updatable=true)
	private TipoGerencia tipoGerencia;
	
	

	public Gestor() {
		super();
		tipoGerencia = new TipoGerencia();
	}

	public Gestor(Funcao funcao, String cpts, Date dataAdmissao,
			Date dataDemissao, Departamento departamento, Usuario usuario,
			Escolaridade escolaridade) {
		super(funcao, cpts, dataAdmissao, dataDemissao, departamento, usuario,
				escolaridade);
		// TODO Auto-generated constructor stub
	}

	public TipoGerencia getTipoGerencia() {
		return tipoGerencia;
	}

	public void setTipoGerencia(TipoGerencia tipoGerencia) {
		this.tipoGerencia = tipoGerencia;
	}
	
	
}
