package br.com.project.bean.view;

import java.util.List;

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
import br.com.project.geral.controller.OrdemServicoController;
import br.com.project.geral.controller.ProdutoController;
import br.com.project.model.classes.OperacaoProduto;
import br.com.project.model.classes.OrdemServico;
import br.com.project.model.classes.Produto;
import br.com.project.model.classes.StatusO;

@Controller
@Scope("session")
@ManagedBean(name = "ordemServicoBeanView")
public class OrdemServicoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<OrdemServico> list = new CarregamentoLazyListForObject<OrdemServico>();
	private OrdemServico objetoSelecionado = new OrdemServico();
	private String url = "/cadastro/cad_ordem_servico.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_ordem_servico.jsf?faces-redirect=true";

	@Autowired
	private OrdemServicoController ordemServicoController;

	@Autowired
	private ProdutoController produtoController;

	public OrdemServicoController getOrdemServicoController() {
		return ordemServicoController;
	}

	public void setOrdemServicoController(OrdemServicoController ordemServicoController) {
		this.ordemServicoController = ordemServicoController;
	}

	public List<Produto> pesquisarProduto(String pn) throws Exception {
		return produtoController.pesquisarProduto(pn);
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		setTipoRelatorio(1);
		super.setNomeRelatorioJasper("report_ordem_servico_sql");
		super.setNomeRelatorioSaida("report_ordem_servico_sql");
		return super.getArquivoReport();
	}

	@Override
	protected Class<OrdemServico> getClassImplemt() {
		return OrdemServico.class;
	}

	public void setObjetoSelecionado(OrdemServico objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public OrdemServico getObjetoSelecionado() {
		return objetoSelecionado;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	public CarregamentoLazyListForObject<OrdemServico> getList() {
		return list;
	}

	@Override
	@RequestMapping(value = { "**/find_ordem_servico" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clean();
		objetoSelecionado = new OrdemServico();
	}

	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = ordemServicoController.merge(objetoSelecionado);

		// Para usar cascade todos objetos que estão no relacionamento devem estar
		// completo (todos atributos, exeto id do objeto filho).
		if (objetoSelecionado.getStatusOs().isEmpty()) {
			Long idProduto = ordemServicoController
					.consultaIdOpInicial(objetoSelecionado.getProduto().getId().toString());
			OperacaoProduto operacaoProduto = ordemServicoController.consultaOpInicial(idProduto.toString());
			StatusO statusOs = new StatusO();
			statusOs.setOperacaoProduto(operacaoProduto);
			statusOs.setOrdemServico(objetoSelecionado);
			objetoSelecionado.addStatusO(statusOs);
			objetoSelecionado = ordemServicoController.merge(objetoSelecionado);
		}

		list.add(objetoSelecionado);
		objetoSelecionado = new OrdemServico();
		sucesso();
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getId() != null && objetoSelecionado.getId() > 0) {
			ordemServicoController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new OrdemServico();
			sucesso();
		}
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new OrdemServico();
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
	protected InterfaceCrud<OrdemServico> getControler() {
		return ordemServicoController;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
