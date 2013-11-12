package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.Cidade;
import classesBasicas.Cliente;
import classesBasicas.Pessoa;
import classesBasicas.PessoaFisica;
import classesBasicas.PessoaJuridica;
import classesBasicas.Situacao;
import classesBasicas.TipoCliente;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class ClienteBean {
	private static final String OP_NOVA = "NOVO Cliente";
	private static final String OP_ALTERAR = "Alterar Cliente";
	private static final String OP_VISUALIZAR = "Propriedades do Cliente";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("util.config");
	
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	
	private Cliente cliente;
	private Cliente clienteParaPesquisa;
	private List<Cliente> lista;
	private Cliente clienteSelecionado;
	private Situacao[] situacoes = Situacao.values();
	private TipoCliente[] tiposCliente = TipoCliente.values(); 
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<UnidadeFederativa> ufs;
	private List<UnidadeFederativa> ufsPesquisa;
	private List<Cidade> cidadesPesquisa;
	private Integer codigoUfPesquisa;
	private Integer codigoCidadePesquisa;
	private String cpfCnpjPesquisa;
	private Integer codigoUfSelecionada;
	private Integer codigoCidadeSelecionada;
	private Integer codigoTipoLogradouroSelecionado;
	
	
	public ClienteBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializar(){
		novoCliente();
		if (lista==null)
			lista = new ArrayList<Cliente>();
		else
			lista.clear();
		listaEstaVazia = true;
		iniciarObjParaPesquisa();
		clienteSelecionado = null;
		tituloOperacao = ClienteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidadesPesquisa = fachada.listarCidades();
			ufsPesquisa = fachada.listarUFs();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void iniciarObjParaPesquisa(){
		clienteParaPesquisa = new Cliente();
		codigoUfPesquisa = null;
		codigoCidadePesquisa = null;
		cpfCnpjPesquisa = "";
		/*clienteParaPesquisa.setDadosPessoa(new PessoaJuridica());
		clienteParaPesquisa.getDadosPessoa().setEndereco(new Endereco());
		clienteParaPesquisa.getDadosPessoa().getEndereco().setCidade(new Cidade());*/
	}

	private void prepararParaExibirDados(Cliente obj){
		this.cliente = obj;
		this.cliente.setDadosPessoa(obj.getDadosPessoa());
		this.cliente.getDadosPessoa().setSituacao(obj.getDadosPessoa().getSituacao());
		codigoTipoLogradouroSelecionado = obj.getDadosPessoa().getEndereco().getTipoLogradouro().getCodigo();	
		codigoCidadeSelecionada = obj.getDadosPessoa().getEndereco().getCidade().getCodigo();
		codigoUfSelecionada = obj.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().getCodigo();
		codigoTipoLogradouroSelecionado = obj.getDadosPessoa().getEndereco().getTipoLogradouro().getCodigo();
		UnidadeFederativa uf = new UnidadeFederativa();
		uf.setCodigo(codigoUfSelecionada);
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			ufs = fachada.listarUFs();
			cidades = fachada.consultarCidadesPorUF(uf);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(clienteSelecionado);
		tituloOperacao = ClienteBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkClienteProp");//"cliente-prop";
	}
	
	public String novo(){
		novoCliente();
		tituloOperacao = ClienteBean.OP_NOVA;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return resourceBundle.getString("linkClienteProp");//"cliente-prop";
	}
	
	private void novoCliente(){
		cliente = new Cliente();
		/*cliente.setPj(new PessoaJuridica());
		cliente.getPj().setEndereco(new Endereco());
		cliente.getPj().getEndereco().setTipoLogradouro(new TipoLogradouro());
		cliente.getPj().getEndereco().setCidade(new Cidade());*/
		codigoCidadeSelecionada = null;//new Cidade();
		codigoUfSelecionada = null;//new UnidadeFederativa();
		codigoTipoLogradouroSelecionado = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			cliente = clienteSelecionado;
			fachada.excluirCliente(cliente);
			MsgPrimeFaces.exibirMensagemInfomativa("Cliente " + clienteSelecionado.getDadosPessoa().getNome() + " excluido com sucesso!");
			novoCliente();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		clienteSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			cliente.getDadosPessoa().getEndereco().setCidade(fachada.pegarCidadePorId(codigoCidadeSelecionada));
			cliente.getDadosPessoa().getEndereco().setTipoLogradouro(fachada.pegarTipoLogradouroPorId(codigoTipoLogradouroSelecionado));
			cliente.getDadosPessoa().getEndereco().setCep(cliente.getDadosPessoa().getEndereco().getCep().replace("-", ""));
			if (cliente.getDadosPessoa() instanceof PessoaJuridica)
				((PessoaJuridica)cliente.getDadosPessoa()).setCnpj(((PessoaJuridica)cliente.getDadosPessoa()).getCnpj().replace(".", "").replace("/", "").replace("-", ""));
			else
				if (cliente.getDadosPessoa() instanceof PessoaFisica)
					((PessoaFisica)cliente.getDadosPessoa()).setCpf(((PessoaFisica)cliente.getDadosPessoa()).getCpf().replace(".", "").replace("-", ""));
			if (cliente.getCodigo() == null || cliente.getCodigo() == 0)
				cliente.setCodigo(null);
			fachada.salvarCliente(cliente);
			MsgPrimeFaces.exibirMensagemInfomativa("Cliente salvo com sucesso!");
			novoCliente();
			somenteLeitura = true;
			tiposLogradouros.clear();
			cidades.clear();
			ufs.clear();
			return resourceBundle.getString("linkCliente");//"cliente";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public void consultar(){
		try{
			if (codigoCidadePesquisa != null && codigoCidadePesquisa > 0)
				clienteParaPesquisa.getDadosPessoa().getEndereco().getCidade().setCodigo(codigoCidadePesquisa);
			if (codigoUfPesquisa != null && codigoUfPesquisa > 0)
				clienteParaPesquisa.getDadosPessoa().getEndereco().getCidade().getUnidadeFederativa().setCodigo(codigoUfPesquisa);
			atualizarLista(fachada.consultarCliente(clienteParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(clienteSelecionado);
		tituloOperacao = ClienteBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = ClienteBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return resourceBundle.getString("linkClienteProp");//"cliente-prop";
	}
	

	private void atualizarLista(List<Cliente> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public String cancelar(){
		somenteLeitura = true;
		tiposLogradouros.clear();
		cidades.clear();
		ufs.clear();
		return resourceBundle.getString("linkCliente");
	}
	
	public void limpar(){
		iniciarObjParaPesquisa();
	}
	
	public String carregarPagina(){
		inicializar();
		return resourceBundle.getString("linkCliente");//"cliente.xhtml?faces-redirect=true";
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
					MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades da UF = null.");
				if (cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() != null && cliente.getDadosPessoa().getEndereco().getCidade().getCodigo() > 0)
					codigoCidadeSelecionada = cliente.getDadosPessoa().getEndereco().getCidade().getCodigo();
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
			}
		}
	}
	
	// GETs e SETs

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteParaPesquisa() {
		return clienteParaPesquisa;
	}

	public void setClienteParaPesquisa(Cliente clienteParaPesquisa) {
		this.clienteParaPesquisa = clienteParaPesquisa;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
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

	public List<Cliente> getLista() {
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

	public TipoCliente[] getTiposCliente() {
		return tiposCliente;
	}

	public Integer getCodigoUfPesquisa() {
		return codigoUfPesquisa;
	}

	public void setCodigoUfPesquisa(Integer codigoUfPesquisa) {
		this.codigoUfPesquisa = codigoUfPesquisa;
	}

	public Integer getCodigoCidadePesquisa() {
		return codigoCidadePesquisa;
	}

	public void setCodigoCidadePesquisa(Integer codigoCidadePesquisa) {
		this.codigoCidadePesquisa = codigoCidadePesquisa;
	}

	public List<UnidadeFederativa> getUfsPesquisa() {
		return ufsPesquisa;
	}

	public List<Cidade> getCidadesPesquisa() {
		return cidadesPesquisa;
	}

	public String getCpfCnpjPesquisa() {
		return cpfCnpjPesquisa;
	}

	public void setCpfCnpjPesquisa(String cpfCnpjPesquisa) {
		this.cpfCnpjPesquisa = cpfCnpjPesquisa;
	}
	
	
	
}
