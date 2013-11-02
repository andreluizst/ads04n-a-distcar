package gui.managedBeans;

import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import classesBasicas.Centro;
import classesBasicas.Situacao;
import classesBasicas.TipoCentro;

public class CentroBean {
	private static final String OP_NOVA = "  NOVA  ";
	private static final String OP_ALTERAR = "Alterar";
	
	private IFachada fachada;
	
	private Centro centro;
	private Centro centroParaPesquisa;
	private String mensagem;
	private List<Centro> lista;
	private Centro centroSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private TipoCentro[] tiposCentro = TipoCentro.values();
	private TipoCentro tipoCentroSelecionado;
	
	public CentroBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoCentro();
		if (lista==null)
			lista = new ArrayList<Centro>();
		else
			lista.clear();
		listaEstaVazia = true;
		centroParaPesquisa = new Centro();
		centroSelecionado = null;
		tituloOperacao = CentroBean.OP_NOVA;
	}


	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (centroSelecionado != null)
			centro = centroSelecionado;
		tituloOperacao = CentroBean.OP_ALTERAR;
		return "funcao-prop";
	}
	
	public String novo(){
		novoCentro();
		tituloOperacao = CentroBean.OP_NOVA;
		return "centro-prop";
	}
	
	private void novoCentro(){
		centro = new Centro();
		centro.setAlias("Digite um nome aqui");
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			centro = centroSelecionado;
			fachada.excluirCentro(centro);
			MsgPrimeFaces.exibirMensagemInfomativa("Centro " + centroSelecionado.getAlias() + " excluido com sucesso!");
			novoCentro();
			//atualizarLista(listar());
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		centroSelecionado = null;
	}
	
	
	
	public void salvarAjax(ActionEvent actionEvent){
		try{
			Fachada.obterInstancia().salvarCentro(centro);
			novoCentro();
			MsgPrimeFaces.exibirMensagemInfomativa("Centro salvo com sucesso!");
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String salvar(){
		try{
			Fachada.obterInstancia().salvarCentro(centro);
			MsgPrimeFaces.exibirMensagemInfomativa("Centro salvo com sucesso!");
			novoCentro();
			return "centro";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarCentro(centroParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	

	private void atualizarLista(List<Centro> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}

	public List<Centro> getLista(){
		return lista;
	}

	public Centro getCentroSelecionado() {
		return centroSelecionado;
	}

	public void setCentroSelecionado(Centro centroSelecionado) {
		this.centroSelecionado = centroSelecionado;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	public Centro getCentroparaPesquisa() {
		return centroParaPesquisa;
	}

	public void setCentroParaPesquisa(Centro centroParaPesquisa) {
		this.centroParaPesquisa = centroParaPesquisa;
	}

	public boolean isListaEstaVazia() {
		return listaEstaVazia;
	}

	public void setListaEstaVazia(boolean listaEstaVazia) {
		this.listaEstaVazia = listaEstaVazia;
	}
	
	public String carregarPagina(){
		inicializar();
		return "centro";
	}



	public String getTituloOperacao() {
		return tituloOperacao;
	}



	public void setTituloOperacao(String tituloOperacao) {
		this.tituloOperacao = tituloOperacao;
	}
	
		
}
