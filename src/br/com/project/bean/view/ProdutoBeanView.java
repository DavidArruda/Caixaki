package br.com.project.bean.view;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.ProdutoController;
import br.com.project.model.classes.Produto;

@Controller
@Scope(value = "session")
@ManagedBean(name = "produtoBeanView")
public class ProdutoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();
	private Produto objetoSelecionado = new Produto();
	private String url = "/cadastro/cad_produto.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_produto.jsf?faces-redirect=true";

	@Resource
	private ProdutoController produtoController;

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_produto");
		super.setNomeRelatorioSaida("report_produto");
		super.setListDataBeanCollectionReport(produtoController.findList(getClassImplemt()));
		return super.getArquivoReport();
	}

	public void setProdutoController(ProdutoController ProdutoController) {
		this.produtoController = ProdutoController;
	}

	public ProdutoController getProdutoController() {
		return produtoController;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		//if (validarCampoObrigatorio(objetoSelecionado)) 
			list.clean();
			objetoSelecionado = produtoController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Produto();
			sucesso();

		//}
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	@RequestMapping({ "**/find_produto" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clean();
		objetoSelecionado = new Produto();
	}

	@Override
	protected Class<Produto> getClassImplemt() {
		return Produto.class;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return produtoController;
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getId() != null && objetoSelecionado.getId() > 0) {
			produtoController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Produto();
			sucesso();
		}
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Produto();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clean();
		return url;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	public CarregamentoLazyListForObject<Produto> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Produto> list) {
		this.list = list;
	}

	public Produto getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Produto objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}

}
