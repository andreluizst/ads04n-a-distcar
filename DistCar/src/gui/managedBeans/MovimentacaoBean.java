package gui.managedBeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.Carro;
import classesBasicas.Centro;
import classesBasicas.Movimentacao;
import classesBasicas.MovimentacaoItem;
import classesBasicas.SituacaoMovimentacao;
import classesBasicas.TipoMovimentacao;
import classesBasicas.VersaoCarro;
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
	private MovimentacaoItem itemMovimentacaoSelecionada;
	private MovimentacaoItem itemMovimentacao;
	private Movimentacao movimentacao;
	private List<Movimentacao> lista;
	private List<MovimentacaoItem> itensDaMovimentacao;
	//private List<MovimentacaoItem> itensOriginais;
	private List<Centro> centrosPesquisa;
	private List<Centro> centros;
	private List<VersaoCarro> versoesDeCarros;
	private SituacaoMovimentacao[] situacoes = SituacaoMovimentacao.values();
	private TipoMovimentacao[] tiposDeMovimentacoes = TipoMovimentacao.values();
	private boolean temCentros;
	private boolean centroDestinoRequerido;
	private String chassi = "";
	private boolean temVersoesDeCarros;
	private Carro carro;
	
	
	
	public MovimentacaoBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		movimentacaoSelecionada = null;
		itemMovimentacaoSelecionada = null;
		itemMovimentacao = new MovimentacaoItem();
		carro = new Carro();
		novaMovimentacao();
		iniciarObjParaPesquisa();
		if (lista==null)
			lista = new ArrayList<Movimentacao>();
		else
			lista.clear();
		centroDestinoRequerido = false;
		temVersoesDeCarros = false;
		temCentros = false;
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
		//itensOriginais = obj.getItens();
		if (itensDaMovimentacao == null)
			itensDaMovimentacao = new ArrayList<MovimentacaoItem>();
		if (obj.getItens() != null && obj.getItens().size() > 0){
			listaItemEstaVazia = false;
			itensDaMovimentacao.addAll(obj.getItens());
		}else{
			listaItemEstaVazia = true;
			itensDaMovimentacao.clear();
		}
		try{
			centros = fachada.listarCentros();
			versoesDeCarros = fachada.listarVersoes();
			temCentros = centros != null ? centros.size() > 0 : false;
			temVersoesDeCarros = versoesDeCarros != null ? centros.size() > 0 : false;
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (movimentacaoSelecionada == null)
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
		if (movimentacaoSelecionada == null)
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
		if (movimentacaoSelecionada == null)
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
		versoesDeCarros.clear();
		return resourceBundle.getString("linkMovimentacao");
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkMovimentacao");
	}
	
	public void mudarTipoMovimentacao(ValueChangeEvent obj){
		TipoMovimentacao tpMov;
		try{
			tpMov = (TipoMovimentacao)obj.getNewValue();
			if (tpMov != null)
				centroDestinoRequerido = tpMov == TipoMovimentacao.ENTRE_CENTROS?true:false; 
		}catch(Exception ex){
			centroDestinoRequerido = false;
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void adicionarItem(){
		Carro c = null;
		//boolean podeAdicionar = false;
		List<Carro> lista;// = new ArrayList<Carro>();
		MovimentacaoItem item = new MovimentacaoItem();
		if (carro.getAnoFabricacao() != null && carro.getChassi() != null
				&& carro.getChassi().length() > 0 && carro.getVersao() != null
				&& carro.getVersao().getCodigo() != null && carro.getVersao().getCodigo() > 0){
			try{
				c = fachada.pegarCarroPeloChassi(carro.getChassi());
				if (c != null){
					if (c.getAnoFabricacao() != carro.getAnoFabricacao() 
							|| c.getVersao().getCodigo() != carro.getVersao().getCodigo()){
						carro = c;
						MsgPrimeFaces.exibirMensagemDeAviso("O carro chassi "+ carro.getChassi()
								+ " já existe. Os dados do carro informado serão alterado para os"
								+ " dados já existentes no banco de dados!");
					}
				}
				for (MovimentacaoItem m : movimentacao.getItens()){
					if (m.getNumeroMovimentoCarroPK().getCarro().getChassi().equals(c.getChassi()))
						throw new Exception("O carro [" + carro.getChassi() + "] informado já existe na lista");
				}
				
				item.getNumeroMovimentoCarroPK().setCarro(c);
				movimentacaoSelecionada.getItens().add(item);
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
			}
		}
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

	public MovimentacaoItem getItemMovimentacaoSelecionada() {
		return itemMovimentacaoSelecionada;
	}

	public void setItemMovimentacaoSelecionada(
			MovimentacaoItem itemMovimentacaoSelecionada) {
		this.itemMovimentacaoSelecionada = itemMovimentacaoSelecionada;
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

	public TipoMovimentacao[] getTiposDeMovimentacoes() {
		return tiposDeMovimentacoes;
	}

	public boolean isCentroDestinoRequerido() {
		return centroDestinoRequerido;
	}

	public boolean isListaItemEstaVazia() {
		return listaItemEstaVazia;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public boolean isTemCentros() {
		return temCentros;
	}

	public boolean isTemVersoesDeCarros() {
		return temVersoesDeCarros;
	}

	public List<VersaoCarro> getVersoesDeCarros() {
		return versoesDeCarros;
	}

	public MovimentacaoItem getItemMovimentacao() {
		return itemMovimentacao;
	}

	public void setItemMovimentacao(MovimentacaoItem itemMovimentacao) {
		this.itemMovimentacao = itemMovimentacao;
	}
	
	
}
