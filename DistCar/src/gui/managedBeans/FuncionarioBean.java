package gui.managedBeans;

import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import classesBasicas.Cidade;
import classesBasicas.Departamento;
import classesBasicas.Escolaridade;
import classesBasicas.Funcao;
import classesBasicas.Funcionario;
import classesBasicas.Situacao;
import classesBasicas.TipoLogradouro;
import classesBasicas.UnidadeFederativa;

@ManagedBean
@SessionScoped
public class FuncionarioBean {
	private static final String OP_NOVA = "NOVO Funcionário";
	private static final String OP_ALTERAR = "Alterar Funcionário";
	private static final String OP_VISUALIZAR = "Propriedades do Funcionário";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	
	private Funcionario funcionario;
	private Funcionario funcionarioParaPesquisa;
	private List<Funcionario> lista;
	private Funcionario funcionarioSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private List<TipoLogradouro> tiposLogradouros; 
	private List<Cidade> cidades;
	private List<UnidadeFederativa> ufs;
	private List<Departamento> departamentos;
	private List<Funcao> funcoes;
	private List<Escolaridade> escolaridades;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private Integer codigoTipoLogradouroSelecionado;
	private Integer codigoFuncaoSelecionada;
	private Integer codigoUfSelecionada;
	private Integer codigoCidadeSelecionada;
	private Integer codigoDepartamentoSelecionado;
	private Integer codigoEscolaridadeSelecionada;
	
	
	
	public FuncionarioBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializarObjParaPesquisa(){
		funcionarioParaPesquisa = new Funcionario();
		/*funcionarioParaPesquisa.setDepartamento(new Departamento());
		funcionarioParaPesquisa.setFuncao(new Funcao());
		funcionarioParaPesquisa.setEscolaridade(new Escolaridade());
		funcionarioParaPesquisa.setEndereco(new Endereco());
		funcionarioParaPesquisa.getEndereco().setTipoLogradouro(new TipoLogradouro());
		funcionarioParaPesquisa.getEndereco().setCidade(new Cidade());*/
	}
	
	private void inicializar(){
		novoFuncionario();
		if (lista==null)
			lista = new ArrayList<Funcionario>();
		else
			lista.clear();
		listaEstaVazia = true;
		inicializarObjParaPesquisa();
		funcionarioSelecionado = null;
		tituloOperacao = FuncionarioBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		codigoCidadeSelecionada = null;
		codigoEscolaridadeSelecionada = null;
		codigoTipoLogradouroSelecionado = null;
		codigoDepartamentoSelecionado = null;
		codigoUfSelecionada = null;
		codigoFuncaoSelecionada = null;
		try{
			tiposLogradouros = fachada.listarTiposLogradouros();
			cidades = fachada.listarCidades();
			ufs = fachada.listarUFs();
			departamentos = fachada.listarDepartamentos();
			funcoes = fachada.listarFuncoes();
			escolaridades = fachada.listarEscolaridades();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}

	private void prepararParaExibirDados(Funcionario obj){
		funcionario = obj;
		funcionario.setEscolaridade(obj.getEscolaridade());
		funcionario.setDepartamento(obj.getDepartamento());
		funcionario.setSituacao(obj.getSituacao());
		funcionario.setFuncao(obj.getFuncao());
		funcionario.setEndereco(obj.getEndereco());
		if (obj.getFuncao() != null && obj.getFuncao().getCodigo() != null)
			codigoFuncaoSelecionada = obj.getFuncao().getCodigo();
		else
			codigoFuncaoSelecionada = null;
		if (obj.getDepartamento() != null && obj.getDepartamento().getCodigo() != null
				&& obj.getDepartamento().getCodigo() > 0)
			codigoDepartamentoSelecionado = obj.getDepartamento().getCodigo();
		else
			codigoDepartamentoSelecionado = null;
		if (obj.getEscolaridade() != null && obj.getEscolaridade().getCodigo() != null &&
				obj.getEscolaridade().getCodigo() > 0)
			codigoEscolaridadeSelecionada = obj.getEscolaridade().getCodigo();
		else
			codigoEscolaridadeSelecionada = null;
		if (obj.getEndereco() != null){
			if (obj.getEndereco().getTipoLogradouro() != null && 
					obj.getEndereco().getTipoLogradouro().getCodigo() > 0)
				codigoTipoLogradouroSelecionado = obj.getEndereco().getTipoLogradouro().getCodigo();
			else
				codigoTipoLogradouroSelecionado = null;
			if (obj.getEndereco().getCidade() != null && obj.getEndereco().getCidade().getCodigo() > 0)
				codigoCidadeSelecionada = obj.getEndereco().getCidade().getCodigo();
			else
				codigoCidadeSelecionada = null;
			if (obj.getEndereco().getCidade().getUnidadeFederativa() != null 
					&& obj.getEndereco().getCidade().getUnidadeFederativa().getCodigo() > 0)
				codigoUfSelecionada = obj.getEndereco().getCidade().getUnidadeFederativa().getCodigo();
			else
				codigoUfSelecionada = null;
		}
		UnidadeFederativa uf = new UnidadeFederativa();
		uf.setCodigo(codigoUfSelecionada);
		try{
			cidades = fachada.consultarCidadesPorUF(uf);
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtrar as cidades pelo estado selecionado!");
		}
	}
	
	public String alterar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(funcionarioSelecionado);
		tituloOperacao = FuncionarioBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "funcionario-prop";
	}
	
	public String novo(){
		novoFuncionario();
		tituloOperacao = FuncionarioBean.OP_NOVA;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "funcionario-prop";
	}
	
	private void novoFuncionario(){
		funcionario = new Funcionario();
		/*funcionario.setDepartamento(new Departamento());
		funcionario.setFuncao(new Funcao());
		funcionario.setEscolaridade(new Escolaridade());
		funcionario.setEndereco(new Endereco());
		funcionario.getEndereco().setTipoLogradouro(new TipoLogradouro());
		funcionario.getEndereco().setCidade(new Cidade());*/
		codigoCidadeSelecionada = null;
		codigoEscolaridadeSelecionada = null;
		codigoTipoLogradouroSelecionado = null;
		codigoDepartamentoSelecionado = null;
		codigoUfSelecionada = null;
		codigoFuncaoSelecionada = null;
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			funcionario = funcionarioSelecionado;
			fachada.excluirFuncionario(funcionario);
			MsgPrimeFaces.exibirMensagemInfomativa("Funcionário " + funcionarioSelecionado.getNome() + " excluido com sucesso!");
			novoFuncionario();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		funcionarioSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		try{
			//funcionario.getOrgaoExpedidor()
			if (codigoDepartamentoSelecionado == null)
				funcionario.setDepartamento(null);
			else
				funcionario.setDepartamento(fachada.pegarDepartamentoPorId(codigoDepartamentoSelecionado));
			if (codigoFuncaoSelecionada == null)
				funcionario.setFuncao(null);
			else
				funcionario.setFuncao(fachada.pegarFuncaoPorId(codigoFuncaoSelecionada));
			if (codigoEscolaridadeSelecionada == null)
				funcionario.setEscolaridade(null);
			else
				funcionario.setEscolaridade(fachada.pegarEscolaridadePorId(codigoEscolaridadeSelecionada));
			if (codigoTipoLogradouroSelecionado == null)
				funcionario.getEndereco().getTipoLogradouro().setCodigo(null);
			else
				funcionario.getEndereco().setTipoLogradouro(fachada.pegarTipoLogradouroPorId(codigoTipoLogradouroSelecionado));
			if (codigoCidadeSelecionada == null)
				funcionario.getEndereco().getCidade().setCodigo(null);
			else
				funcionario.getEndereco().setCidade(fachada.pegarCidadePorId(codigoCidadeSelecionada));
			if (funcionario.getCodigo() == null || funcionario.getCodigo() == 0)
				funcionario.setCodigo(null);
			funcionario.setUsuario(null);
			funcionario.setCpf(funcionario.getCpf().replace(".", "").replace("-", ""));
			funcionario.getEndereco().setCep(funcionario.getEndereco().getCep().replace("-", "").replace(".", ""));
			fachada.salvarFuncionario(funcionario);
			MsgPrimeFaces.exibirMensagemInfomativa("Funcionário salvo com sucesso!");
			novoFuncionario();
			somenteLeitura = true;
			return "funcionario";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarFuncionario(funcionarioParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		prepararParaExibirDados(funcionarioSelecionado);
		tituloOperacao = FuncionarioBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = FuncionarioBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return "funcionario-prop";
	}
	
	private void atualizarLista(List<Funcionario> lista) {
		if (lista == null)
			this.lista.clear();
		else
			this.lista = lista;
		listaEstaVazia = this.lista.size()>0?false:true;
	}
	
	public void limpar(){
		inicializarObjParaPesquisa();
		situacaoSelecionada = null;
	}
	
	public String carregarPagina(){
		inicializar();
		return "funcionario.xhtml?faces-redirect=true";
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
				if (funcionario.getEndereco() != null && funcionario.getEndereco().getCidade().getCodigo() != null
						&& funcionario.getEndereco().getCidade().getCodigo() > 0)
					codigoCidadeSelecionada = funcionario.getEndereco().getCidade().getCodigo();
			}catch(Exception ex){
				MsgPrimeFaces.exibirMensagemDeErro("Não foi possível filtar as cidades pela UF!");
			}
		}
	}
	
	
	// GETs e SETs

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioParaPesquisa() {
		return funcionarioParaPesquisa;
	}

	public void setFuncionarioParaPesquisa(Funcionario funcionarioParaPesquisa) {
		this.funcionarioParaPesquisa = funcionarioParaPesquisa;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public List<Funcionario> getLista() {
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

	public List<TipoLogradouro> getTiposLogradouros() {
		return tiposLogradouros;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<UnidadeFederativa> getUfs() {
		return ufs;
	}

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public Integer getCodigoTipoLogradouroSelecionado() {
		return codigoTipoLogradouroSelecionado;
	}

	public void setCodigoTipoLogradouroSelecionado(
			Integer codigoTipoLogradouroSelecionado) {
		this.codigoTipoLogradouroSelecionado = codigoTipoLogradouroSelecionado;
	}

	public Integer getCodigoFuncaoSelecionada() {
		return codigoFuncaoSelecionada;
	}

	public void setCodigoFuncaoSelecionada(Integer codigoFuncaoSelecionada) {
		this.codigoFuncaoSelecionada = codigoFuncaoSelecionada;
	}

	public Integer getCodigoCidadeSelecionada() {
		return codigoCidadeSelecionada;
	}

	public void setCodigoCidadeSelecionada(Integer codigoCidadeSelecionada) {
		this.codigoCidadeSelecionada = codigoCidadeSelecionada;
	}

	public Integer getCodigoDepartamentoSelecionado() {
		return codigoDepartamentoSelecionado;
	}

	public void setCodigoDepartamentoSelecionado(
			Integer codigoDepartamentoSelecionado) {
		this.codigoDepartamentoSelecionado = codigoDepartamentoSelecionado;
	}

	public Integer getCodigoEscolaridadeSelecionada() {
		return codigoEscolaridadeSelecionada;
	}

	public void setCodigoEscolaridadeSelecionada(
			Integer codigoEscolaridadeSelecionada) {
		this.codigoEscolaridadeSelecionada = codigoEscolaridadeSelecionada;
	}

	public Integer getCodigoUfSelecionada() {
		return codigoUfSelecionada;
	}

	public void setCodigoUfSelecionada(Integer codigoUfSelecionada) {
		this.codigoUfSelecionada = codigoUfSelecionada;
	}

	public List<Escolaridade> getEscolaridades() {
		return escolaridades;
	}
	
	
	
}
