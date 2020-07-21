package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ApontamentoController;
import br.com.project.model.classes.Apontamento;
import br.com.project.model.classes.MedicaoApontamento;

@Controller
@Scope(value = "session")
@ManagedBean(name = "apontamentoBeanView")
public class ApontamentoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Apontamento objetoSelecionado = new Apontamento();
	
	private MedicaoApontamento medicaoApontamento = new MedicaoApontamento();

	private final String url = "/cadastro/cad_apontamento.jsf?faces-redirect=true";

	private final String urlFind = "/cadastro/find_apontamento.jsf?faces-redirect=true";

	private CarregamentoLazyListForObject<Apontamento> list = new CarregamentoLazyListForObject<>();

	@Autowired
	private ApontamentoController apontamentoController;

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_apontamento");
		super.setNomeRelatorioSaida("report_apontamento");
		super.setListDataBeanCollectionReport(apontamentoController.findList(getClassImplemt()));
		return super.getArquivoReport();
	}

	@Override
	public String salvar() throws Exception {
		objetoSelecionado = apontamentoController.merge(objetoSelecionado);
		return "";
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clean();
		objetoSelecionado = new Apontamento();
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
		objetoSelecionado = (Apontamento) apontamentoController.getSession().get(getClassImplemt(),
				objetoSelecionado.getId());
		apontamentoController.delete(objetoSelecionado);
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
		list.clean();
		objetoSelecionado = apontamentoController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Apontamento();
		sucesso();
	}


	@Override
	protected Class<Apontamento> getClassImplemt() {
		return Apontamento.class;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return apontamentoController;
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Apontamento();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
	
	//GETTERS E SETTERS
	public void setObjetoSelecionado(Apontamento objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public Apontamento getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	public CarregamentoLazyListForObject<Apontamento> getList() throws Exception {
		return list;
	}
	
	public MedicaoApontamento getMedicaoApontamento() {
		return medicaoApontamento;
	}
	
	public void setMedicaoApontamento(MedicaoApontamento medicaoApontamento) {
		this.medicaoApontamento = medicaoApontamento;
	}
}
