package gui.managedBeans;

import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Centro;
import classesBasicas.Situacao;
import classesBasicas.TipoCentro;

@ManagedBean
@SessionScoped
public class CentroBean {
	private static final String OP_NOVA = "  NOVA  ";
	private static final String OP_ALTERAR = "Alterar";
	private static final String OP_VISUALIZAR = "Propriedades do";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	
	private Centro centro;
	private Centro centroParaPesquisa;
	private List<Centro> lista;
	private Centro centroSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private TipoCentro[] tiposCentro = TipoCentro.values();
	private TipoCentro tipoCentroSelecionado;
	private String textoBotaoFecharOuCancelar;
	
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
		tituloOperacao = CentroBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_FECHAR;
		//centroParaPesquisa.getDadosPJ().getEndereco().getCidade()
		//centroParaPesquisa.getDadosPJ()
	}


	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (centroSelecionado != null)
			centro = centroSelecionado;
		tituloOperacao = CentroBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_CANCELAR;
		return "centro-prop";
	}
	
	public String novo(){
		novoCentro();
		tituloOperacao = CentroBean.OP_NOVA;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_CANCELAR;
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
	
	
	/*
	public void salvarAjax(ActionEvent actionEvent){
		try{
			fachada.salvarCentro(centro);
			novoCentro();
			MsgPrimeFaces.exibirMensagemInfomativa("Centro salvo com sucesso!");
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}*/
	
	public String salvar(){
		try{
			fachada.salvarCentro(centro);
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
	
	public String carregarPagina(){
		inicializar();
		return "centro";
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Centro getCentroParaPesquisa() {
		return centroParaPesquisa;
	}

	public void setCentroParaPesquisa(Centro centroParaPesquisa) {
		this.centroParaPesquisa = centroParaPesquisa;
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

	public TipoCentro getTipoCentroSelecionado() {
		return tipoCentroSelecionado;
	}

	public void setTipoCentroSelecionado(TipoCentro tipoCentroSelecionado) {
		this.tipoCentroSelecionado = tipoCentroSelecionado;
	}

	public List<Centro> getLista() {
		return lista;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public boolean isListaEstaVazia() {
		return listaEstaVazia;
	}

	public String getTituloOperacao() {
		return tituloOperacao;
	}

	public TipoCentro[] getTiposCentro() {
		return tiposCentro;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}


	
	
		
}
