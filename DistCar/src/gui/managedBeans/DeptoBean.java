package gui.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import classesBasicas.Centro;
import classesBasicas.Departamento;
import classesBasicas.Gestor;
import classesBasicas.Situacao;
import fachada.Fachada;
import fachada.IFachada;
import gui.MsgPrimeFaces;


@ManagedBean
@SessionScoped
public class DeptoBean {
	private static final String OP_NOVA = "NOVO Departamento";
	private static final String OP_ALTERAR = "Alterar Departamento";
	private static final String OP_VISUALIZAR = "Propriedades do Departamento";
	private static final String TXT_BTN_CANCELAR = "Cancelar";
	private static final String TXT_BTN_FECHAR = "Fechar";
	
	private IFachada fachada;
	
	private Departamento departamento;
	private Departamento departamentoParaPesquisa;
	private List<Departamento> lista;
	private Departamento departamentoSelecionado;
	private Situacao situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();
	private boolean listaEstaVazia;
	private String tituloOperacao;
	private String textoBotaoFecharOuCancelar;
	private boolean somenteLeitura;
	private List<Centro> centros;
	private List<Departamento> deptosSuperiores;
	private List<Gestor> gestores;
	private boolean temGestores;
	private boolean temCentros;
	private boolean temDeptosSuperiores;
	
	
	
	public DeptoBean(){
		fachada = Fachada.obterInstancia();
		inicializar();
	}
	
	private void inicializarObjParaPesquisa(){
		departamentoParaPesquisa = new Departamento();
		departamentoParaPesquisa.setDepartamentoSuperior(new Departamento());
		departamentoParaPesquisa.setGestor(new Gestor());
		departamentoParaPesquisa.setCentro(new Centro());
	}
	
	private void inicializar(){
		novoDepartamento();
		if (lista==null)
			lista = new ArrayList<Departamento>();
		else
			lista.clear();
		listaEstaVazia = true;
		inicializarObjParaPesquisa();
		departamentoSelecionado = null;
		tituloOperacao = DeptoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		try{
			centros = fachada.listarCentros();
			deptosSuperiores = fachada.listarDepartamentos();
			gestores = fachada.listarGestores();
			temGestores = gestores.size()>0?true:false;
			temCentros = centros.size()>0?true:false;
			temDeptosSuperiores = deptosSuperiores.size()>0?true:false;
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}


	public String alterar(){
		if (listaEstaVazia)
			return null;
		if (departamentoSelecionado != null)
			departamento = departamentoSelecionado;
		iniciarFKdoDepartamentoSeNulo();
		tituloOperacao = DeptoBean.OP_ALTERAR;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "departamento-prop";
	}
	
	public String novo(){
		novoDepartamento();
		tituloOperacao = DeptoBean.OP_NOVA;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_CANCELAR;
		somenteLeitura = false;
		return "departamento-prop";
	}
	
	private void novoDepartamento(){
		departamento = new Departamento();
		departamento.setNome("Digite um nome aqui");
		departamento.setDepartamentoSuperior(new Departamento());
		departamento.setCentro(new Centro());
		departamento.setGestor(new Gestor());
	}
	
	public void excluir(){
		if (listaEstaVazia)
			return;
		try{
			departamento = departamentoSelecionado;
			fachada.excluirDepartamento(departamento);
			MsgPrimeFaces.exibirMensagemInfomativa("Departamento " + departamentoSelecionado.getNome() + " excluido com sucesso!");
			novoDepartamento();
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	public void fechar(ActionEvent actionevent){
		lista.clear();
		listaEstaVazia = true;
		departamentoSelecionado = null;
		somenteLeitura = true;
	}
	
	public String salvar(){
		/*
		musica.setArtista(Fachada.getInstancia().
				consultarArtistaPorId(artistaSelecionado));
		if (musica.getCodigo() == null || musica.getCodigo() == 0){
			musica.setCodigo(null);
			Fachada.getInstancia().inserirMusica(musica);	
		} else {
			Fachada.getInstancia().alterarMusica(musica);
		} 
		*/
		try{
			//atlzCentroSelecionado();
			departamento.setCentro(fachada.pegarCentroPorId(departamento.getCentro().getCodigo()));
			if (departamento.getDepartamentoSuperior().getCodigo() == null 
					|| departamento.getDepartamentoSuperior().getCodigo() == 0){
				departamento.setDepartamentoSuperior(null);
			}else{
				departamento.setDepartamentoSuperior(fachada.pegarDepartamentoPorId(departamento.getDepartamentoSuperior().getCodigo()));
			}
			if (departamento.getGestor().getCodigo() == null 
					|| departamento.getGestor().getCodigo() == 0){
				departamento.setGestor(null);
			}else{
				departamento.setGestor(fachada.pegarGestorPorId(departamento.getGestor().getCodigo()));
			}
			if (departamento.getCodigo() == null || departamento.getCodigo() == 0)
				departamento.setCodigo(null);
			fachada.salvarDepartamento(departamento);
			MsgPrimeFaces.exibirMensagemInfomativa("Departamento salvo com sucesso!");
			novoDepartamento();
			somenteLeitura = true;
			return "departamento";
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
		return null;
	}
	
	private void atlzCentroSelecionado(){
		for (Centro c : centros){
			if (c.getCodigo() == departamento.getCentro().getCodigo()){
				departamento.setCentro(c);
				break;
			}
		}
	}
	
	public void consultar(){
		try{
			atualizarLista(fachada.consultarDepartamento(departamentoParaPesquisa));
		}catch(Exception ex){
			MsgPrimeFaces.exibirMensagemDeErro(ex.getMessage());
		}
	}
	
	private void iniciarFKdoDepartamentoSeNulo(){
		if (departamento.getCentro() == null)
			departamento.setCentro(new Centro());
		if (departamento.getDepartamentoSuperior() == null)
			departamento.setDepartamentoSuperior(new Departamento());
		if (departamento.getGestor() == null)
			departamento.setGestor(new Gestor());
	}
	
	public String visualizar(){
		if (listaEstaVazia)
			return null;
		if (departamentoSelecionado != null){
			departamento = departamentoSelecionado;
		iniciarFKdoDepartamentoSeNulo();
		}
		tituloOperacao = DeptoBean.OP_VISUALIZAR;
		textoBotaoFecharOuCancelar = DeptoBean.TXT_BTN_FECHAR;
		somenteLeitura = true;
		return "departamento-prop";
	}
	

	private void atualizarLista(List<Departamento> lista) {
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
		return "departamento";
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Departamento getDepartamentoParaPesquisa() {
		return departamentoParaPesquisa;
	}

	public void setDepartamentoParaPesquisa(Departamento departamentoParaPesquisa) {
		this.departamentoParaPesquisa = departamentoParaPesquisa;
	}

	public Departamento getDepartamentoSelecionado() {
		return departamentoSelecionado;
	}

	public void setDepartamentoSelecionado(Departamento departamentoSelecionado) {
		this.departamentoSelecionado = departamentoSelecionado;
	}

	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public List<Departamento> getLista() {
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

	public String getTextoBotaoFecharOuCancelar() {
		return textoBotaoFecharOuCancelar;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public List<Centro> getCentros() {
		return centros;
	}

	public List<Departamento> getDeptosSuperiores() {
		return deptosSuperiores;
	}

	public List<Gestor> getGestores() {
		return gestores;
	}

	public boolean isTemGestores() {
		return temGestores;
	}

	public boolean isTemCentros() {
		return temCentros;
	}

	public boolean isTemDeptosSuperiores() {
		return temDeptosSuperiores;
	}
	
	
	
}
