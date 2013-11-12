package gui.managedBeans;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.Fabricante;
import classesBasicas.MarcaCarro;
import classesBasicas.Situacao;
import fachada.Fachada;
import gui.MsgPrimeFaces;

@ManagedBean
@SessionScoped
public class MarcaCarroBean {

	private MarcaCarro marcaCarro;
	private List<Fabricante> fabricanteCarros;
	private List<MarcaCarro> listaMarcasCarros;
	private MarcaCarro marcaSelecionada;
	private MarcaCarro situacaoSelecionada;
	private Situacao[] situacoes = Situacao.values();

	public MarcaCarro getMarcaCarro() {
		return marcaCarro;
	}

	public void setMarcaCarro(MarcaCarro marcaCarro) {
		this.marcaCarro = marcaCarro;
	}

	public List<Fabricante> getFabricanteCarros() {
		return fabricanteCarros;
	}

	public void setFabricanteCarros(List<Fabricante> fabricanteCarros) {
		this.fabricanteCarros = fabricanteCarros;
	}

	public List<MarcaCarro> getListaMarcasCarros() {
		return listaMarcasCarros;
	}

	public void setListaMarcasCarros(List<MarcaCarro> listaMarcasCarros) {
		this.listaMarcasCarros = listaMarcasCarros;
	}

	public MarcaCarro getMarcaSelecionada() {
		return marcaSelecionada;
	}

	public void setMarcaSelecionada(MarcaCarro marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;
	}

	public MarcaCarro getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(MarcaCarro situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
	}

	public Situacao[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Situacao[] situacoes) {
		this.situacoes = situacoes;
	}

	@PostConstruct
	public void init() {
		marcaCarro = new MarcaCarro();
		listarFabricanteCarro();
		listarMarcasCarros();
	}

	public void novo(ActionEvent actionEvent) {
		init();
	}

	private List<MarcaCarro> listarMarcasCarros() {
		listaMarcasCarros = Fachada.obterInstancia().listarMarcasCarros();
		return listaMarcasCarros;
	}

	public String salvar() throws Exception {

		marcaCarro.setDataUltimaAtualizacao(Calendar.getInstance());
		Fachada.obterInstancia().salvarMarcaCarro(marcaCarro);
		MsgPrimeFaces.exibirMensagemInfomativa("Marca salva com sucesso!");
		init();
		return "marca";
	}

	public void listar() {
		listaMarcasCarros = Fachada.obterInstancia().listarMarcasCarros();
	}

	public List<Fabricante> listarFabricanteCarro() {
		try {
			fabricanteCarros = Fachada.obterInstancia().listarFabricantes();
			return fabricanteCarros;
		} catch (Exception ex) {
			MsgPrimeFaces.exibirMensagemInfomativa(ex.getMessage());
		}
		return fabricanteCarros;
	}

	public void excluir() {
		if (marcaSelecionada == null) {
			MsgPrimeFaces
					.exibirMensagemInfomativa("Selecione um acess�rio para exclus�o!");
		} else {
			Fachada.obterInstancia().removerMarcaCarro(marcaSelecionada);
			MsgPrimeFaces
					.exibirMensagemInfomativa("Marca removida com sucesso!");
			consulta();
		}
	}

	public void consulta() {
		listar();
	}

	public String novo() {
		marcaCarro = new MarcaCarro();
		return "marca-prop";
	}

	public String alterar() throws Exception {
		if (marcaSelecionada == null) {
			MsgPrimeFaces
					.exibirMensagemInfomativa("Selecione um acess�rio para alterar!");
			return "marca";
		} else {
			marcaCarro = marcaSelecionada;
			marcaCarro.setFabricante(Fachada.obterInstancia().consultarFabricantePorId(marcaSelecionada.getFabricante().getCodigo()));
			return "marca-prop";
		}
	}

	public String cancelar() {
		marcaCarro = new MarcaCarro();
		marcaSelecionada = null;
		return "marca";
	}

	public String consultar() {
		listaMarcasCarros = Fachada.obterInstancia().consultarMarcasCarros(marcaCarro);
		marcaCarro = new MarcaCarro();
		return "marca";
	}
}
