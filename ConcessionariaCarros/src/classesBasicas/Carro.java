package classesBasicas;

import java.util.Date;

import classesBasicas.ObjetoGeral;

public class Carro extends ObjetoGeral {

	private String chassi;
	private ModeloCarro modeloCarro;
	private VersaoModeloCarro versaoCarro;
	private Date anoFabricacao;
	private String cor;
	private Double valorCarro;
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public VersaoModeloCarro getVersaoCarro() {
		return versaoCarro;
	}
	public void setVersaoCarro(VersaoModeloCarro versaoCarro) {
		this.versaoCarro = versaoCarro;
	}
	public Date getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(Date anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Double getValorCarro() {
		return valorCarro;
	}
	public void setValorCarro(Double valorCarro) {
		this.valorCarro = valorCarro;
	}
	
	
}
