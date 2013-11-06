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
import classesBasicas.Cidade;
import classesBasicas.Endereco;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoCentro;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;

@ManagedBean
@SessionScoped
public class CentroBean {
	private static final String OP_NOVA = "NOVO Centro";
	private static final String OP_ALTERAR = "Alterar Centro";
	private static final String OP_VISUALIZAR = "Propriedades do Centro";
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
	private TipoCentro[] tiposCentros = TipoCentro.values();
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	//private List<UnidadeFederativa> unidadesFederativas;
	private TipoCentro tipoCentroSelecionado;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	
	
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
		iniciarObjParaPesquisa();
		centroSelecionado = null;
		tituloOperacao = CentroBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidades = fachada.listarCidades();
			//unidadesFederativas = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		centroParaPesquisa = new Centro();
		centroParaPesquisa.setDadosPJ(new PessoaJuridica());
		centroParaPesquisa.getDadosPJ().setEndereco(new Endereco());
		centroParaPesquisa.getDadosPJ().getEndereco().setCidade(new Cidade());
	}

	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (centroSelecionado != null)
			centro = centroSelecionado;
		tituloOperacao = CentroBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "centro-prop";
	}
	
	public String novo(){
		novoCentro();
		tituloOperacao = CentroBean.OP_NOVA;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "centro-prop";
	}
	
	private void novoCentro(){
		centro = new Centro();
		centro.setAlias("Digite um nome aqui");
		centro.setDadosPJ(new PessoaJuridica());
		centro.getDadosPJ().setEndereco(new Endereco());
		centro.getDadosPJ().getEndereco().setTipoLogradouro(new TipoLogradouro());
		centro.getDadosPJ().getEndereco().setCidade(new Cidade());
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
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			fachada.salvarCentro(centro);
			MsgPrimeFaces.exibirMensagemInfomativa("Centro salvo com sucesso!");
			novoCentro();
			somenteLeitura = true;
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
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (centroSelecionado != null)
			centro = centroSelecionado;
		tituloOperacao = CentroBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = CentroBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return "centro-prop";
	}
	

	private void atualizarLista(List<Centro> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
		situacaoSelecionada = null;
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

	public TipoCentro[] getTiposCentros() {
		return tiposCentros;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}
	
	public boolean isSomenteLeitura(){
		return somenteLeitura;
	}

	public boolean isNenhumItemSelecionado(){
		boolean desabilitado = true;
		if ((!listaEstaVazia) && (centroSelecionado != null)){
			desabilitado = false;
		}
		return desabilitado;
	}
	
	public List<TipoLogradouro> getTiposLogradouros(){
		return tiposLogradouros;
	}
	
	public List<Cidade> getCidades(){
		return cidades;
	}
		
}
