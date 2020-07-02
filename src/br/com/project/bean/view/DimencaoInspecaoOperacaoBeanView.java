package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.DimencaoInspecaoOperacaoController;
import br.com.project.model.classes.DimencaoInspecaoOperacao;

@Controller
@Scope(value = "session")
@ManagedBean(name = "dimencaoInspecaoOperacaoBeanView")
public class DimencaoInspecaoOperacaoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private DimencaoInspecaoOperacao objetoSelecionado = new DimencaoInspecaoOperacao();

	private final String url = "/cadastro/cad_dimencaoI_inspecao_operacao.jsf?faces-redirect=true";

	private final String urlFind = "/cadastro/find_Dimencao_inspecao_operacao.jsf?faces-redirect=true";

	private CarregamentoLazyListForObject<DimencaoInspecaoOperacao> list = new CarregamentoLazyListForObject<>();

	@Autowired
	private DimencaoInspecaoOperacaoController DimencaoInspecaoOperacaoController;
	
	//@Autowired
	//private OperacaoProdutoController operacaoProdutoController;
	
	//@PostConstruct
	//public void init() {
		
	//}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_dimencao_inspecao_operacao");
		super.setNomeRelatorioSaida("report_dimencao_inspecao_operacao");
		super.setListDataBeanCollectionReport(DimencaoInspecaoOperacaoController.findList(getClassImplemt()));
		return super.getArquivoReport();
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clean();
		objetoSelecionado = new DimencaoInspecaoOperacao();
	}

	@Override
	public String editar() throws Exception {
		list.clean();
		return url;
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (DimencaoInspecaoOperacao) DimencaoInspecaoOperacaoController.getSession().get(getClassImplemt(),
				objetoSelecionado.getId());
		DimencaoInspecaoOperacaoController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		novo();
		sucesso();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		objetoSelecionado = DimencaoInspecaoOperacaoController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new DimencaoInspecaoOperacao();
		sucesso();
	}

	public void setObjetoSelecionado(DimencaoInspecaoOperacao objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public DimencaoInspecaoOperacao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<DimencaoInspecaoOperacao> getList() throws Exception {
		return list;
	}

	@Override
	protected Class<DimencaoInspecaoOperacao> getClassImplemt() {
		return DimencaoInspecaoOperacao.class;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return DimencaoInspecaoOperacaoController;
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new DimencaoInspecaoOperacao();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

}
