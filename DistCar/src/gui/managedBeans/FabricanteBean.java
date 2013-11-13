package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.Cidade;
import classesBasicas.Endereco;
import classesBasicas.Fabricante;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class FabricanteBean {
	private static final String OP_NOVA = "NOVO Fabricante";
	private static final String OP_ALTERAR = "Alterar Fabricante";
	private static final String OP_VISUALIZAR = "Propriedades do Fabricante";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	private Fabricante fabricante;
	private Fabricante fabricanteParaPesquisa;
	private List<Fabricante> lista;
	private Fabricante fabricanteSelecionado;
	private Situacao[] situacoes = Situacao.values();
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<UnidadeFederativa> ufs;
	private Integer codigoUfSelecionada;
	private Integer codigoCidadeSelecionada;
	private Integer codigoTipoLogradouroSelecionado;
	
	
	public FabricanteBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoFabricante();
		if (lista==null)
			lista = new ArrayList<Fabricante>();
		else
			lista.clear();
		listaEstaVazia = true;
		iniciarObjParaPesquisa();
		fabricanteSelecionado = null;
		tituloOperacao = FabricanteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidades = fachada.listarCidades();
			ufs = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		fabricanteParaPesquisa = new Fabricante();
		/*fabricanteParaPesquisa.setPj(new PessoaJuridica());
		fabricanteParaPesquisa.getPj().setEndereco(new Endereco());
		fabricanteParaPesquisa.getPj().getEndereco().setCidade(new Cidade());*/
	}

	private void prepararParaExibirDados(Fabricante obj){
		this.fabricante = obj;
		this.fabricante.setPj(obj.getPj());
		this.fabricante.getPj().setSituacao(obj.getPj().getSituacao());
		codigoCidadeSelecionada = obj.getPj().getEndereco().getCidade().getCodigo();
		codigoUfSelecionada = obj.getPj().getEndereco().getCidade().getUnidadeFederativa().getCodigo();
		codigoTipoLogradouroSelecionado = obj.getPj().getEndereco().getTipoLogradouro().getCodigo();
		UnidadeFederativa uf = new UnidadeFederativa();
		uf.setCodigo(codigoUfSelecionada);
		try{
		cidades = fachada.consultarCidadesPorUF(uf);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("N�o foi poss�vel filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(fabricanteSelecionado);
		tituloOperacao = FabricanteBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkFabricanteProp");//"fabricante-prop";
	}
	
	public String novo(){
		novoFabricante();
		tituloOperacao = FabricanteBean.OP_NOVA;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkFabricanteProp");//"fabricante-prop";
	}
	
	private void novoFabricante(){
		fabricante = new Fabricante();
		/*fabricante.setPj(new PessoaJuridica());
		fabricante.getPj().setEndereco(new Endereco());
		fabricante.getPj().getEndereco().setTipoLogradouro(new TipoLogradouro());
		fabricante.getPj().getEndereco().setCidade(new Cidade());*/
		codigoCidadeSelecionada = null;//new Cidade();
		codigoUfSelecionada = null;//new UnidadeFederativa();
		codigoTipoLogradouroSelecionado = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			fabricante = fabricanteSelecionado;
			fachada.excluirFabricante(fabricante);
			MsgPrimeFaces.exibirMensagemInfomativa("Fabricante " + fabricanteSelecionado.getPj().getNome() + " excluido com sucesso!");
			novoFabricante();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		fabricanteSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			fabricante.getPj().getEndereco().setCidade(fachada.pegarCidadePorId(codigoCidadeSelecionada));
			fabricante.getPj().getEndereco().setTipoLogradouro(fachada.pegarTipoLogradouroPorId(codigoTipoLogradouroSelecionado));
			fabricante.getPj().getEndereco().setCep(fabricante.getPj().getEndereco().getCep().replace("-", ""));
			fabricante.getPj().setCnpj(fabricante.getPj().getCnpj().replace(".", "").replace("/", "").replace("-", ""));
			if (fabricante.getCodigo() == null || fabricante.getCodigo() == 0)
				fabricante.setCodigo(null);
			fachada.salvarFabricante(fabricante);
			MsgPrimeFaces.exibirMensagemInfomativa("Fabricante salvo com sucesso!");
			novoFabricante();
			somenteLeitura = true;
			return resourceBundle.getString("linkFabricante");//"fabricante";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarFabricante(fabricanteParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(fabricanteSelecionado);
		tituloOperacao = FabricanteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FabricanteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkFabricanteProp");//"fabricante-prop";
	}
	

	private void atualizarLista(List<Fabricante> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkFabricante");//"fabricante.xhtml?faces-redirect=true";
	}
	
	public void filtrarCidades(ValueChangeEvent evento){
		if (!somenteLeitura){
			try{
				codigoUfSelecionada = (Integer)evento.getNewValue();
				UnidadeFederativa uf = new UnidadeFederativa();
				uf.setCodigo(codigoUfSelecionada);
				if (codigoUfSelecionada != null){
					cidades = fachada.consultarCidadesPorUF(uf);
				}else
					MsgPrimeFaces.exibirMensagemDeErro("N�o foi poss�vel filtar as cidades da UF = null.");
				if (fabricante.getPj().getEndereco().getCidade().getCodigo() != null && fabricante.getPj().getEndereco().getCidade().getCodigo() > 0)
					codigoCidadeSelecionada = fabricante.getPj().getEndereco().getCidade().getCodigo();
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro("N�o foi poss�vel filtar as cidades pela UF!");
			}
		}
	}
	
	// GETs e SETs

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Fabricante getFabricanteParaPesquisa() {
		return fabricanteParaPesquisa;
	}

	public void setFabricanteParaPesquisa(Fabricante fabricanteParaPesquisa) {
		this.fabricanteParaPesquisa = fabricanteParaPesquisa;
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public Integer getCodigoUfSelecionada() {
		return codigoUfSelecionada;
	}

	public void setCodigoUfSelecionada(Integer codigoUfSelecionada) {
		this.codigoUfSelecionada = codigoUfSelecionada;
	}

	public Integer getCodigoCidadeSelecionada() {
		return codigoCidadeSelecionada;
	}

	public void setCodigoCidadeSelecionada(Integer codigoCidadeSelecionada) {
		this.codigoCidadeSelecionada = codigoCidadeSelecionada;
	}

	public Integer getCodigoTipoLogradouroSelecionado() {
		return codigoTipoLogradouroSelecionado;
	}

	public void setCodigoTipoLogradouroSelecionado(
			Integer codigoTipoLogradouroSelecionado) {
		this.codigoTipoLogradouroSelecionado = codigoTipoLogradouroSelecionado;
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

	public List<Fabricante> getLista() {
		return lista;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public List<TipoLogradouro> getTiposLogradouros() {
		return tiposLogradouros;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<UnidadeFederativa> getUfs() {
		return ufs;
	}
	
	
}