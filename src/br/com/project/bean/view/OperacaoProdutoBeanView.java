package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.OperacaoProdutoController;
import br.com.project.geral.controller.ProdutoController;
import br.com.project.model.classes.OperacaoProduto;
import br.com.project.model.classes.Produto;

@Controller
@Scope("session")
@ManagedBean(name = "operacaoProdutoBeanView")
public class OperacaoProdutoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<OperacaoProduto> list = new CarregamentoLazyListForObject<>();
	private OperacaoProduto objetoSelecionado = new OperacaoProduto();
	private String url = "/cadastro/cad_operacao_produto.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_operacao_produto.jsf?faces-redirect=true";

	@Autowired
	private OperacaoProdutoController operacaoProdutoController;

	@Autowired
	ProdutoController produtoController;

	public void setOperacaoProdutoController(OperacaoProdutoController operacaoProdutoController) {
		this.operacaoProdutoController = operacaoProdutoController;
	}

	public OperacaoProdutoController getOperacaoProdutoController() {
		return operacaoProdutoController;
	}

	public List<Produto> pesquisarProduto(String pn) throws Exception {
		return produtoController.pesquisarProduto(pn);
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_operacao_produto");
		super.setNomeRelatorioSaida("report_operacao_produto");
		super.setListDataBeanCollectionReport(operacaoProdutoController.findList(getClassImplemt()));
		return super.getArquivoReport();
	}

	@RequestMapping("**/findProduto")
	public void buscarProduto(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codigoProduto") Long codProduto) throws Exception {

		Produto Produto = (Produto) produtoController.findById(Produto.class, codProduto);

		if (Produto != null) {
			objetoSelecionado.setProduto(Produto);
			httpServletResponse.getWriter().write(Produto.getJson().toString());
		}
	}

	public void setObjetoSelecionado(OperacaoProduto objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	public OperacaoProduto getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<OperacaoProduto> getList() {
		return list;
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clean();
		objetoSelecionado = new OperacaoProduto();
	}

	@Override
	public void saveNotReturn() throws Exception {
		// if (validarCampoObrigatorio(objetoSelecionado)) {
		list.clean();
		objetoSelecionado = operacaoProdutoController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new OperacaoProduto();
		sucesso();
		// }
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getId() != null && objetoSelecionado.getId() > 0) {
			operacaoProdutoController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new OperacaoProduto();
			sucesso();
		}
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new OperacaoProduto();
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

	@Override
	protected Class<OperacaoProduto> getClassImplemt() {
		// TODO Auto-generated method stub
		return OperacaoProduto.class;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return operacaoProdutoController;
	}

}
