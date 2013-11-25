package classesBasicas;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MovimentacaoItem {
	
	@EmbeddedId
	private NumeroMovimentoCarroPK numeroMovimentoCarroPK;

	public MovimentacaoItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovimentacaoItem(NumeroMovimentoCarroPK numeroMovimentoCarroPK) {
		super();
		this.numeroMovimentoCarroPK = numeroMovimentoCarroPK;
	}

	public NumeroMovimentoCarroPK getNumeroMovimentoCarroPK() {
		return numeroMovimentoCarroPK;
	}

	public void setNumeroMovimentoCarroPK(
			NumeroMovimentoCarroPK numeroMovimentoCarroPK) {
		this.numeroMovimentoCarroPK = numeroMovimentoCarroPK;
	}

	@Override
	public String toString() {
		return "MovimentacaoItem [numeroMovimentoCarroPK="
				+ numeroMovimentoCarroPK + "]";
	}
	
	
}
