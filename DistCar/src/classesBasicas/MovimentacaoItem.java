package classesBasicas;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MovimentacaoItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private MovimentoCarroPK movimentoCarroPK;

	public MovimentacaoItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovimentacaoItem(MovimentoCarroPK movimentoCarroPK) {
		super();
		this.movimentoCarroPK = movimentoCarroPK;
	}

	public MovimentoCarroPK getNumeroMovimentoCarroPK() {
		return movimentoCarroPK;
	}

	public void setNumeroMovimentoCarroPK(
			MovimentoCarroPK movimentoCarroPK) {
		this.movimentoCarroPK = movimentoCarroPK;
	}

	@Override
	public String toString() {
		return "MovimentacaoItem [numeroMovimentoCarroPK="
				+ movimentoCarroPK + "]";
	}
	
	
}
