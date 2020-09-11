package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.StatusOsController;
import br.com.project.model.classes.StatusO;

@Controller
@Scope(value = "view")
@ManagedBean(name = "statusOsBeanView")
public class StatusOsBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private StatusO objetoSelecionado = new StatusO();

	private final String url = "/cadastro/cad_status_os.jsf?faces-redirect=true";

	private final String urlFind = "/cadastro/find_status_os.jsf?faces-redirect=true";

	private CarregamentoLazyListForObject<StatusO> list = new CarregamentoLazyListForObject<>();

	@Autowired
	private StatusOsController statusOsController;

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_status_os");
		super.setNomeRelatorioSaida("report_status_os");
		super.setListDataBeanCollectionReport(statusOsController.findList(getClassImplemt()));
		return super.getArquivoReport();
	}

	@Override
	public String salvar() throws Exception {
		objetoSelecionado = statusOsController.merge(objetoSelecionado);
		return "";
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clean();
		objetoSelecionado = new StatusO();
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
		objetoSelecionado = (StatusO) statusOsController.getSession().get(getClassImplemt(),
				objetoSelecionado.getId());
		statusOsController.delete(objetoSelecionado);
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
		objetoSelecionado = statusOsController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new StatusO();
		sucesso();
	}

	public void setObjetoSelecionado(StatusO objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public StatusO getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<StatusO> getList() throws Exception {
		return list;
	}

	@Override
	protected Class<StatusO> getClassImplemt() {
		return StatusO.class;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return statusOsController;
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new StatusO();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

}
