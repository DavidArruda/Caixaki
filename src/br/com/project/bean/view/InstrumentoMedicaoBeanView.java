package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.InstrumentoMedicaoController;
import br.com.project.model.classes.InstrumentoMedicao;

@Controller
@Scope("session")
@ManagedBean(name = "instrumentoMedicaoBeanView")
public class InstrumentoMedicaoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<InstrumentoMedicao> list = new CarregamentoLazyListForObject<InstrumentoMedicao>();
	private InstrumentoMedicao objetoSelecionado = new InstrumentoMedicao();
	private String url = "/cadastro/cad_instrumento_medicao.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_instrumento_medicao.jsf?faces-redirect=true";

	@Autowired
	private InstrumentoMedicaoController instrumentoMedicaoController;

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_instrumento_medicao");
		super.setNomeRelatorioSaida("report_instrumento_medicao");
		super.setListDataBeanCollectionReport(instrumentoMedicaoController.findList(getClassImplemt()));
		return super.getArquivoReport();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	protected Class<InstrumentoMedicao> getClassImplemt() {
		return InstrumentoMedicao.class;
	}

	public void setObjetoSelecionado(InstrumentoMedicao objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public InstrumentoMedicao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<InstrumentoMedicao> getList() throws Exception {
		return list;
	}

	public void setInstrumentoMedicaoController(InstrumentoMedicaoController instrumentoMedicaoController) {
		this.instrumentoMedicaoController = instrumentoMedicaoController;
	}

	public InstrumentoMedicaoController getInstrumentoMedicaoController() {
		return instrumentoMedicaoController;
	}

	@Override
	protected InterfaceCrud<InstrumentoMedicao> getControler() {
		return instrumentoMedicaoController;
	}

	@Override
	@RequestMapping(value = { "**/find_instrumento_medicao" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clean();
		objetoSelecionado = new InstrumentoMedicao();
	}

	@Override
	public void saveNotReturn() throws Exception {
		if (objetoSelecionado.getDataCalibracao().after(objetoSelecionado.getValidadeCalibracao())) {
			addMessage("Data inválida.");
			return;

		} else {

			// if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clean();
			objetoSelecionado = instrumentoMedicaoController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new InstrumentoMedicao();
			sucesso();
			// }
		}
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getId() != null && objetoSelecionado.getId() > 0) {
			instrumentoMedicaoController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new InstrumentoMedicao();
			sucesso();
		}
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new InstrumentoMedicao();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clean();
		return url;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
