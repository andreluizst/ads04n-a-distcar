package gui.managedBeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.Centro;
import classesBasicas.Movimentacao;
import classesBasicas.MovimentacaoItem;
import classesBasicas.SituacaoMovimentacao;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;


@ManagedBean
@SessionScoped
public class MovimentacaoBean {
	private static final String OP_NOVA = "NOVA Movimentação";
	private static final String OP_ALTERAR = "Alterar Movimentação";
	private static final String OP_VISUALIZAR = "Propriedades da Movimentação";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private boolean listaEstaVazia;
	private boolean listaItemEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private FacesMessage msgPendente;
	private Movimentacao movimentacaoParaPesquisa;
	private Date dataFinalPesquisa;
	private Movimentacao movimentacaoSelecionada;
	private Movimentacao movimentacao;
	private List<Movimentacao> lista;
	private List<MovimentacaoItem> itensDaMovimentacao;
	private List<Centro> centrosPesquisa;
	private List<Centro> centros;
	private SituacaoMovimentacao[] situacoes = SituacaoMovimentacao.values();
	
	
	
	public MovimentacaoBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novaMovimentacao();
		iniciarObjParaPesquisa();
		if (lista==null)
			lista = new ArrayList<Movimentacao>();
		else
			lista.clear();
		listaEstaVazia = true;
		listaItemEstaVazia = true;
		tituloOperacao = MovimentacaoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = MovimentacaoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			centrosPesquisa = fachada.listarCentros();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		movimentacaoParaPesquisa = new Movimentacao();
		movimentacaoParaPesquisa.setDataMovimentacao(Calendar.getInstance().getTime());
		dataFinalPesquisa = Calendar.getInstance().getTime();
	}

	private void prepararParaExibirDados(Movimentacao obj){
		this.movimentacao = obj;
		try{
			centros = fachada.listarCentros();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(movimentacaoSelecionada);
		tituloOperacao = MovimentacaoBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = MovimentacaoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkMovimentacaoProp");
	}
	
	public String novo(){
		novaMovimentacao();
		prepararParaExibirDados(movimentacao);
		tituloOperacao = MovimentacaoBean.OP_NOVA;
		textoBotaoFecharOuCancelar = MovimentacaoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkMovimentacaoProp");
	}
	
	private void novaMovimentacao(){
		movimentacao = new Movimentacao();
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			movimentacao = movimentacaoSelecionada;
			fachada.excluirMovimentacao(movimentacao);
			MsgPrimeFaces.exibirMensagemInfomativa("Movimentação " + movimentacaoSelecionada.getNumero() + " excluida com sucesso!");
			novaMovimentacao();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String salvar(){
		try{
			fachada.salvarMovimentacao(movimentacao);
			msgPendente = MsgPrimeFaces.criarMsgInfo("Movimentação salva com sucesso!");
			novaMovimentacao();
			somenteLeitura = true;
			return resourceBundle.getString("linkMovimentacao");
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public String getExibirMensagemPendente(){
		if (msgPendente != null){
			MsgPrimeFaces.exibirMensagem(msgPendente);
			msgPendente = null;
		}
		return "";
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarMovimentacao(movimentacaoParaPesquisa, dataFinalPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(movimentacaoSelecionada);
		tituloOperacao = MovimentacaoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = MovimentacaoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkMovimentacaoProp");
	}
	

	private void atualizarLista(List<Movimentacao> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public String cancelar(){
		somenteLeitura = true;
		centros.clear();
		return resourceBundle.getString("linkMovimentacao");
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkMovimentacao");
	}
	
	
	// GETs e SETs

	public Movimentacao getMovimentacaoParaPesquisa() {
		return movimentacaoParaPesquisa;
	}

	public void setMovimentacaoParaPesquisa(Movimentacao movimentacaoParaPesquisa) {
		this.movimentacaoParaPesquisa = movimentacaoParaPesquisa;
	}

	public Date getDataFinalPesquisa() {
		return dataFinalPesquisa;
	}

	public void setDataFinalPesquisa(Date dataFinalPesquisa) {
		this.dataFinalPesquisa = dataFinalPesquisa;
	}

	public Movimentacao getMovimentacaoSelecionada() {
		return movimentacaoSelecionada;
	}

	public void setMovimentacaoSelecionada(Movimentacao movimentacaoSelecionada) {
		this.movimentacaoSelecionada = movimentacaoSelecionada;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public boolean isListaEstaVazia() {
		return listaEstaVazia;
	}

	public String getTituloOperacao() {
		return tituloOperacao;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public List<Movimentacao> getLista() {
		return lista;
	}

	public List<Centro> getCentrosPesquisa() {
		return centrosPesquisa;
	}

	public List<Centro> getCentros() {
		return centros;
	}

	public SituacaoMovimentacao[] getSituacoes() {
		return situacoes;
	}
	
	
}
